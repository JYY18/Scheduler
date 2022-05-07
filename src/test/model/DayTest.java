package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTest {
    Day testDay1;
    Day testDay2;
    Attraction attraction1;
    Attraction attraction2;
    Attraction attraction3;

    @BeforeEach
    void runBefore() {
        testDay1 = new Day(1);

        attraction1 = new Attraction(1, "ParkA", "!5:00", "16:00");
        attraction2 = new Attraction(1, "BeachA", "16:00", "18:00");
        attraction3 = new Attraction(1, "TrailA", "10:00", "11:00");
    }

    @Test
    void testConstructor() {
        assertEquals(1, testDay1.getDayNum());
    }

    @Test
    void testAddAttractionAndNote() {
        testDay1.addAttractionAndNote(attraction1, "Bring Umbrella");
        assertEquals(1, testDay1.getAttractionsAdded().size());
        assertEquals("Bring Umbrella", attraction1.getNote());
        assertEquals(attraction1, testDay1.getAttractionsAdded().get(0));

        testDay1.addAttractionAndNote(attraction2, "Bring Food");
        assertEquals(2, testDay1.getAttractionsAdded().size());
        assertEquals("Bring Food", attraction2.getNote());
        assertEquals(attraction2, testDay1.getAttractionsAdded().get(1));

        testDay1.addAttractionAndNote(attraction3, null);
        assertEquals(3, testDay1.getAttractionsAdded().size());
        assertEquals(null, attraction3.getNote());
        assertEquals(attraction3, testDay1.getAttractionsAdded().get(2));
    }

    @Test
    void testDeleteEvent() {
        testDay1.addAttractionAndNote(attraction1, "Bring Umbrella");
        testDay1.addAttractionAndNote(attraction2, "Bring Food");
        testDay1.addAttractionAndNote(attraction3, null);

        testDay1.deleteEvent(0);
        assertEquals(2, testDay1.getAttractionsAdded().size());
        assertEquals(attraction2, testDay1.getAttractionsAdded().get(0));
        assertEquals(attraction3, testDay1.getAttractionsAdded().get(1));

        testDay1.deleteEvent(1);
        assertEquals(1, testDay1.getAttractionsAdded().size());
        assertEquals(attraction2, testDay1.getAttractionsAdded().get(0));
    }
}
