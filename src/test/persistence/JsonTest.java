package persistence;

import model.Attraction;
import model.Day;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAttraction(int dayNumber, String name, String fromTime, String toTime,
                                   String note, Attraction attraction) {
        assertEquals(dayNumber, attraction.getAttractionDayNumber());
        assertEquals(name, attraction.getAttractionName());
        assertEquals(fromTime, attraction.getAttractionFromTime());
        assertEquals(toTime, attraction.getAttractionToTime());
        assertEquals(note, attraction.getNote());
    }

    protected void checkDay(int attractions, Day day) {
        assertEquals(attractions, day.getAttractionsAdded().size());
    }
}

