package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a day with day number, contains the attractions
public class Day implements Writable {
    ArrayList<Attraction> addedAttractions;
    ArrayList<String> timeAndEvents;
    int dayNum;

    // constructor
    public Day(int dayNum) {
        addedAttractions = new ArrayList<>();
        timeAndEvents = new ArrayList<>();
        this.dayNum = dayNum;
    }

    // MODIFIES: this
    // EFFECTS: adds an attraction and a note
    //          note that each attraction is unique
    public void addAttractionAndNote(Attraction attraction, String note) {
        addedAttractions.add(attraction);
        attraction.addNote(note);
        String d = attraction.getAttractionName() + " added to day number " + dayNum + " from "
                + attraction.getAttractionFromTime() + " to " + attraction.getAttractionToTime() + "\n";
        EventLog.getInstance().logEvent(new Event(d));

    }


    // MODIFIES: this
    // EFFECTS: delete an event from list of added attractions
    public void deleteEvent(int index) {
        Attraction attraction = addedAttractions.get(index);

        addedAttractions.remove(index);

        String d = attraction.getAttractionName() + " removed from day number " + dayNum + " from "
                + attraction.getAttractionFromTime() + " to " + attraction.getAttractionToTime() + "\n";
        EventLog.getInstance().logEvent(new Event(d));

    }

    public ArrayList<Attraction> getAttractionsAdded() {
        return addedAttractions;

    }


    public int getDayNum() {
        return dayNum;
    }

    // used code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", dayNum);
        json.put("events", eventsToJson());

        return json;
    }

    // EFFECTS: returns attractions in this schedule as a JSON array
    // used code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Attraction a : addedAttractions) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;

    }

}