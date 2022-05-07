package model;

import model.Attraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttractionTest {
    Attraction testAttraction1;
    Attraction testAttraction2;

    @BeforeEach
    void testBefore() {
        testAttraction1 = new Attraction(1, "Stanley Park", "15:00", "17:00");
        testAttraction2 = new Attraction(2, "English Bay", "07:00", "12:00");
    }

    @Test
    void testConstructor() {
        assertEquals(1, testAttraction1.getAttractionDayNumber());
        assertEquals("Stanley Park", testAttraction1.getAttractionName());
        assertEquals("15:00", testAttraction1.getAttractionFromTime());
        assertEquals("17:00", testAttraction1.getAttractionToTime());
    }

    @Test
    void testChangeTime() {
        testAttraction1.changeTime("10:00", "12:00");
        assertEquals("10:00", testAttraction1.getAttractionFromTime());
        assertEquals("12:00", testAttraction1.getAttractionToTime());
    }

    @Test
    void testAddNote() {
        testAttraction1.addNote("Bring umbrella");
        assertEquals("Bring umbrella", testAttraction1.getNote());
    }
}