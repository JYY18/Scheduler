package ui;

import model.Day;
import model.Event;
import model.EventLog;
import model.Schedule;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
    JPanel daysPanel;

    JComboBox daysOfTheWeek;

    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private ArrayList<String> columnNames;

    private static final String JSON_STORE = "./data/schedule.json";

    Color scheduleAreaColour = new java.awt.Color(197,218,221);


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
        daysPanel = new JPanel();

        daysOfTheWeek = new JComboBox(days);

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

        menuArea.setBounds(40,40,740,870);
        menuArea.setLayout(null);
        menuArea.setBorder(new LineBorder(Color.BLACK, 1));

        add(menuArea);

        new ViewParksTool(this, menuArea, scheduleArea, daysOfTheWeek);
        new ViewBeachesTool(this, menuArea, scheduleArea, daysOfTheWeek);
        new ViewTrailsTool(this, menuArea, scheduleArea, daysOfTheWeek);
        new ViewRestaurantsTool(this, menuArea, scheduleArea, daysOfTheWeek);
        new ViewShoppingTool(this, menuArea, scheduleArea, daysOfTheWeek);
        new ViewOtherTool(this, menuArea, scheduleArea, daysOfTheWeek);



    }

    // MODIFIES: this
    // EFFECTS: creates and adds buttons for each day
    public void createScheduleArea() {

        JPanel schedulePanel = new JPanel();

        schedulePanel.setBounds(850,40,990,870);
        schedulePanel.setLayout(null);

        schedulePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //save/load

        new SaveTool(this,schedulePanel);
        new LoadTool(this, schedulePanel);

        // delete windows tool

        new DeleteEventTool(this, schedulePanel);




        JPanel columnNamesPanel = new JPanel();
        columnNamesPanel.setBackground(new java.awt.Color(201,228,223));
        columnNamesPanel.setBounds(25,95,940,40);

        columnNames.add("Attraction Name");
        columnNames.add("From");
        columnNames.add("To");
        columnNames.add("Note");

        addContentLine(columnNamesPanel, columnNames);

        schedulePanel.add(columnNamesPanel);

        scheduleArea.setBounds(25,145,940,700);
        scheduleArea.setBackground(new java.awt.Color(197,218,221));
        scheduleArea.setLayout(null);

        //dropdown list

        daysOfTheWeek.setBounds(750,25,200,45);
        daysOfTheWeek.setFont(new Font(null, Font.PLAIN, 18));
        schedulePanel.add(daysOfTheWeek);



        daysOfTheWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDay = (String) daysOfTheWeek.getSelectedItem();

                scheduleArea.removeAll();
                scheduleArea.revalidate();
                scheduleArea.repaint();

                daysPanel.removeAll();
                daysPanel.setBounds(0,0,940,700);
                daysPanel.setBackground(scheduleAreaColour);

                switch (selectedDay) {
                    case "Monday":
                        scheduleArea.add(daysPanel);
                        new ScheduleListWindow(schedule,0, daysPanel);
                        break;
                    case "Tuesday":
                        scheduleArea.add(daysPanel);
                        new ScheduleListWindow(schedule,1, daysPanel);
                        break;
                    case "Wednesday":
                        scheduleArea.add(daysPanel);
                        new ScheduleListWindow(schedule,2,daysPanel);
                        break;
                    case "Thursday":
                        scheduleArea.add(daysPanel);
                        new ScheduleListWindow(schedule,3,daysPanel);
                        break;
                    case "Friday":
                        scheduleArea.add(daysPanel);
                        new ScheduleListWindow(schedule,4,daysPanel);
                        break;
                    case "Saturday":
                        scheduleArea.add(daysPanel);
                        new ScheduleListWindow(schedule,5,daysPanel);
                        break;
                    case "Sunday":
                        scheduleArea.add(daysPanel);
                        new ScheduleListWindow(schedule,6,daysPanel);
                        break;
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
            menuArea.removeAll();
            menuArea.revalidate();
            menuArea.repaint();
            this.createMenu();
            System.out.println("Loaded schedule" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
