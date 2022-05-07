package persistence;

import model.Attraction;
import model.Day;
import model.Schedule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Day from JSON data stored in file
// used code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git as reference
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads schedule from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Schedule read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSchedule(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses schedule from JSON object and returns it
    private Schedule parseSchedule(JSONObject jsonObject) {
        Schedule s = new Schedule();
        addDays(s, jsonObject);
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses days from JSON object and adds them to schedule
    private void addDays(Schedule s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("schedule");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addDay(s, nextDay);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses day from JSON object and adds them to schedule
    private void addDay(Schedule s, JSONObject jsonObject) {
        int dayNum = jsonObject.getInt("day");
        Day day = new Day(dayNum);
        addEvents(day, jsonObject);
        s.addDayToSchedule(day);

    }

    // MODIFIES: d
    // EFFECTS: parses attractions from JSON object and adds them to day
    private void addEvents(Day d, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("events");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addEvent(d, nextThingy);
        }
    }

    // MODIFIES: d
    // EFFECTS: parses attraction from JSON object and adds it to day
    private void addEvent(Day d, JSONObject jsonObject) {
        int dayNum = jsonObject.getInt("day");
        String name = jsonObject.getString("name");
        String fromTime = jsonObject.getString("from time");
        String toTime = jsonObject.getString("to time");
        String note = jsonObject.getString("Note");

        Attraction attraction = new Attraction(dayNum, name, fromTime, toTime);
        d.addAttractionAndNote(attraction, note);

    }
}


