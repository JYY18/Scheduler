package ui;

import model.Day;
import model.Event;
import model.EventLog;
import model.Schedule;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Schedule application
public class ScheduleApp extends JFrame {

    JPanel panel;

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 900;

    private Schedule schedule;
    //private AttractionTypes types;
    private Scanner input;
    String toTime;
    String fromTime;
    String command;
    int dayNum;
    int intCommand;
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
        command = null;

        initializeFields();
        initializeGraphics();

        while (true) {
            // displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                break;
            }
            //processCommand();
        }
        System.out.println("\n Goodbye");

    }

    // MODIFIES: this
    // EFFECTS: initializes schedule
    // used code from: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git as reference
    public void initializeFields() {
        panel = new JPanel();

        schedule = new Schedule();

        schedule.createNewDay();
        schedule.createNewDay();
        schedule.createNewDay();
        schedule.createNewDay();
        schedule.createNewDay();


        //schedule.addDayToSchedule(day);
        //types = new AttractionTypes();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        toTime = "";
        fromTime = "";
        command = null;
        dayNum = 1;
        intCommand = 1;
        dayList = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: creates JFrame layout and buttons
    public void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(1, 1, 0, 0));
        createMenu();
        createScheduleArea();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds buttons
    public void createMenu() {
        JPanel menuArea = new JPanel();
        menuArea.setLayout(new GridLayout(7, 0, 0, 0));
        menuArea.setSize(new Dimension(0, 0));
        add(menuArea, BorderLayout.WEST);

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

    // MODIFIES: this
    // EFFECTS: creates and adds buttons for each day
    public void createScheduleArea() {

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setSize(new Dimension(0, 0));
        add(BorderLayout.EAST, panel);


//        new ScheduleTool(this, panel, 1);
//        new ScheduleTool(this, panel, 2);
//        new ScheduleTool(this, panel, 3);
//        new ScheduleTool(this, panel, 4);
//        new ScheduleTool(this, panel, 5);


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
