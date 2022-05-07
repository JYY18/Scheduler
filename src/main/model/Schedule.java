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
        dayNum = 1;
    }

    // REQUIRES: each day can only be added to schedule once
    // MODIFIES: this
    // EFFECTS: adds a day to schedule
    public void addDayToSchedule(Day day) {
        if (!days.contains(day)) {
            days.add(day);
        }
    }

    // EFFECTS: automatically creates and adds a new day with dayNum 1 greater than previous day
    public Day createNewDay() {
        int size = this.days.size();
        dayNum = size + 1;
        Day newDay = new Day(dayNum);
        this.addDayToSchedule(newDay);
        return newDay;
    }

    // REQUIRES: Day corresponding to dayNum must be added to schedule first
    // EFFECTS: view schedule of a day
//    public ArrayList viewSchedule(int dayNum, AttractionTypes type) {
//        Day viewDay = days.get(dayNum);
//        String attractionFromTime = "";
//        String attractionToTime = "";
//        String output = "";
//        String note = "";
//        ArrayList<String> outputStatements = new ArrayList<>();
//
//        ArrayList<Attraction> viewAttractions = viewDay.getAttractionsAdded(); // returns a list of Attractions
//        for (Attraction a : viewAttractions) {
//            String attractionWithType = type.getAttractionWithType(a);
//            note = a.getNote();
//            String attractionName = a.getAttractionName();
//            attractionFromTime = a.getAttractionFromTime();
//            attractionToTime = a.getAttractionToTime();
//            if (attractionWithType == null) {
//                output = attractionFromTime + " - " + attractionToTime + ": " + attractionName + "\nnote: " + note;
//                outputStatements.add(output);
//            } else {
//                output = attractionFromTime + " - " + attractionToTime + ": " + attractionWithType + " \nnote: " + note;
//                outputStatements.add(output);
//            }
//        }
//        Collections.sort(outputStatements);
//        return outputStatements;
//    }

    public ArrayList getDays() {
        return days;
    }

    public int getLastDayNumInList() {
        int size = days.size();
        int lastIndex = size - 1;
        return days.get(lastIndex).dayNum;
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
