package ui;

import model.Day;
import model.Event;
import model.EventLog;
import model.Schedule;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.ViewParksTool;

import javax.swing.*;
import java.awt.*;
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
    //private AttractionTypes types;
    String toTime;
    String fromTime;
    int dayNum;
    ArrayList<Day> dayList;

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

        schedule.createNewDay();
        schedule.createNewDay();
        schedule.createNewDay();
        schedule.createNewDay();
        schedule.createNewDay();

        toTime = "";
        fromTime = "";
        dayNum = 1;
        dayList = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: creates JFrame layout and buttons
    public void initializeGraphics() {
        setLayout(new BorderLayout());
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
    // EFFECTS: creates and adds buttons for each day
    public void createScheduleArea() {

        JPanel schedulePanel = new JPanel();

        schedulePanel.setBounds(850,40,990,870);


        schedulePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(schedulePanel);






//        panel = new JPanel();
//        panel.setLayout(new GridLayout(0, 1));
//        panel.setSize(new Dimension(0, 0));
//        add(BorderLayout.EAST, panel);


//        new ScheduleTool(this, panel, 1);
//        new ScheduleTool(this, panel, 2);
//        new ScheduleTool(this, panel, 3);
//        new ScheduleTool(this, panel, 4);
//        new ScheduleTool(this, panel, 5);


    }

    // MODIFIES: this
    // EFFECTS: creates and adds buttons
    public void createMenu() {

        JPanel menuArea = new JPanel();
        menuArea.setBounds(40,40,740,870);
        menuArea.setLayout(null);

        menuArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(menuArea);


        new ViewParksTool(this, menuArea);








//        JPanel menuArea = new JPanel();
//        menuArea.setLayout(new GridLayout(7, 0, 0, 0));
//        menuArea.setSize(new Dimension(0, 0));
//        add(menuArea, BorderLayout.WEST);

//        new ViewParksTool(this, menuArea);
//
//        new ViewTrailsTool(this, menuArea);
//
//        new ViewRestaurantsTool(this, menuArea);
//
//        new ViewBeachesTool(this, menuArea);
//
//        new DeleteEventTool(this, menuArea);
//
//        new SaveTool(this, menuArea);
//
//        new LoadTool(this, menuArea);

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
