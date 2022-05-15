package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents the overall schedule, contains all the different days
public class Schedule implements Writable {
    ArrayList<Day> days;
    ArrayList<String> outputStatements;
    int dayNum;

    // constructor
    public Schedule() {
        days = new ArrayList<>();
        outputStatements = new ArrayList<>();
        dayNum = 0;
    }

    // REQUIRES: each day can only be added to schedule once
    // MODIFIES: this
    // EFFECTS: adds a day to schedule
    public void addDayToSchedule(Day day) {
        if (!days.contains(day)) {
            days.add(day);
        }
    }

    public void setDayNum(Day day) {
        this.dayNum = day.getDayNum();
    }

    // EFFECTS: automatically creates and adds a new day with dayNum 1 greater than previous day
    public Day createNewDay() {
        dayNum = dayNum + 1;
        Day newDay = new Day(dayNum);
        this.addDayToSchedule(newDay);
        return newDay;
    }


    public ArrayList getDays() {
        return days;
    }


    // used code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("schedule", daysToJson());
        return json;
    }

    // EFFECTS: returns days in this schedule as a JSON array
    // used code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Day t : days) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
