package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an attraction with day number(which day its added into), name, from time, and to time
public class Attraction implements Writable {
    String name;
    String fromTime;
    String toTime;
    int dayNumber;
    String note;

    // constructor
    public Attraction(int dayNumber, String name, String fromTime, String toTime) {
        this.name = name;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.dayNumber = dayNumber;
        note = null;
    }

    public String getAttractionName() {
        return name;
    }

    public int getAttractionDayNumber() {
        return dayNumber;
    }

    public String getAttractionFromTime() {
        return fromTime;
    }

    public String getAttractionToTime() {
        return toTime;
    }

    // REQUIRES: new time intervals must not overlap with pre-existing intervals
    // MODIFIES: this
    // EFFECTS: change the times of an event
    public void changeTime(String newFromTime, String newToTime) {
        fromTime = newFromTime;
        toTime = newToTime;
    }

    // MODIFIES: this
    // EFFECTS: add a new note to an event
    public void addNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    // used code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", dayNumber);
        json.put("name", name);
        json.put("from time", fromTime);
        json.put("to time", toTime);
        json.put("Note", note);

        return json;
    }
}
