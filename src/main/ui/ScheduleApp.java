package ui;

import model.Day;
import model.Event;
import model.EventLog;
import model.Schedule;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.LoadTool;
import ui.tools.SaveTool;
import ui.tools.ScheduleListWindow;
import ui.tools.ViewParksTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Schedule application
public class ScheduleApp extends JFrame {

    public static final int WIDTH = 1900;
    public static final int HEIGHT = 1000;

    private Schedule schedule;
    String toTime;
    String fromTime;
    int dayNum;
    ArrayList<Day> dayList;

    JPanel menuArea;
    JPanel scheduleArea;

    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private ArrayList<String> columnNames;

    private static final String JSON_STORE = "./data/schedule.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public ScheduleApp() {
        super("Schedule");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSchedule();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // used code from: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git as reference
    public void runSchedule() {
        initializeFields();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: initializes schedule
    // used code from: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git as reference
    public void initializeFields() {

        schedule = new Schedule();

        schedule.createNewDay(); //1
        schedule.createNewDay(); //2
        schedule.createNewDay(); //3
        schedule.createNewDay(); //4
        schedule.createNewDay();
        schedule.createNewDay();
        schedule.createNewDay(); //7


        toTime = "";
        fromTime = "";
        dayNum = 1;
        dayList = new ArrayList<>();

        menuArea = new JPanel();
        scheduleArea = new JPanel();

        columnNames = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: creates JFrame layout and buttons
    public void initializeGraphics() {
        //setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().setBackground(Color.LIGHT_GRAY);
        getContentPane().setLayout(null);

        createMenu();
        createScheduleArea();

        setVisible(true);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
            }
        });

    }

    // MODIFIES: this
    // EFFECTS: creates and adds buttons
    public void createMenu() {

        //JPanel menuArea = new JPanel();
        menuArea.setBounds(40,40,740,870);
        menuArea.setLayout(null);

        add(menuArea);

        new ViewParksTool(this, menuArea, scheduleArea);


    }

    // MODIFIES: this
    // EFFECTS: creates and adds buttons for each day
    public void createScheduleArea() {

        JPanel schedulePanel = new JPanel();

        schedulePanel.setBounds(850,40,990,870);
        schedulePanel.setLayout(null);

        schedulePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        scheduleArea.setBounds(25,145,940,700);
        scheduleArea.setBackground(new java.awt.Color(197,218,221));

        //save/load

        new SaveTool(this,schedulePanel);
        new LoadTool(this, schedulePanel);

        //dropdown list

        JComboBox daysOfTheWeek = new JComboBox(days);

        daysOfTheWeek.setBounds(750,30,200,45);
        daysOfTheWeek.setFont(new Font(null, Font.PLAIN, 18));
        schedulePanel.add(daysOfTheWeek);


        daysOfTheWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDay = (String) daysOfTheWeek.getSelectedItem();

                switch (selectedDay) {
                    case "Monday":
                        scheduleArea.removeAll();
                        new ScheduleListWindow(schedule,0,scheduleArea);
                        break;
                    case "Tuesday":
                        scheduleArea.removeAll();
                        scheduleArea.revalidate();
                        scheduleArea.repaint();
                        scheduleArea.add(new JLabel("Tuesday"));
                        break;
                    default:
                        System.out.println("Please select");
                }

            }
        });
        schedulePanel.add(scheduleArea);
        add(schedulePanel);
    }

    public void addContentLine(JComponent columnPanel, ArrayList<String> line) {
        columnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (String s: line) {
            JLabel label = new JLabel(s);
            label.setPreferredSize(new Dimension(230, 25)); // distance from prev column
            label.setFont(new Font(null, Font.PLAIN, 20));
            columnPanel.add(label);
        }
    }


    // EFFECTS: returns current schedule
    public Schedule getSchedule() {
        return schedule;
    }

    // EFFECTS: saves the schedule to file
    // used code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void saveSchedule() {
        try {
            jsonWriter.open();
            jsonWriter.write(schedule);
            jsonWriter.close();
            System.out.println("Saved schedule" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads schedule from file
    // used code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void loadSchedule() {
        try {
            schedule = jsonReader.read();
            System.out.println("Loaded schedule" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
