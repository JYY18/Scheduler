package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleTest {
    Schedule testSchedule;
    Day day1;
    Day day2;
    Day day3;

    //AttractionTypes attractionTypes;
    Attraction attractionPark;
    Attraction attractionTrail;
    Attraction attractionRestaurant;
    Attraction attractionBeach;
    Attraction myOwnAttraction;

    @BeforeEach
    void runBefore() {
        testSchedule = new Schedule();
        day1 = new Day(1);
        day2 = new Day(2);
        day3 = new Day(3);

        attractionPark = new Attraction(1, "ParkA", "07:00", "09:00");
        attractionTrail = new Attraction(1, "TrailA", "09:00", "12:00");
        attractionRestaurant = new Attraction(1, "RestaurantA", "13:00", "14:00");
        attractionBeach = new Attraction(1, "BeachA", "14:00", "16:00");
        myOwnAttraction = new Attraction(1, "Attraction By me ", "01:00", "02:00");

        day1.addAttractionAndNote(attractionPark, "Bring umbrella");
        day1.addAttractionAndNote(attractionBeach, "Bring food");
        day1.addAttractionAndNote(attractionRestaurant, "Bring money");
        day1.addAttractionAndNote(myOwnAttraction, "Emily's note");

        day2.addAttractionAndNote(attractionTrail, "Bring water");

        testSchedule.addDayToSchedule(day1);
        testSchedule.setDayNum(day1);
        testSchedule.addDayToSchedule(day2);
        testSchedule.setDayNum(day2);
    }

    @Test
    void testAddDayToSchedule() {
        testSchedule.addDayToSchedule(day1);
        assertEquals(2, testSchedule.getDays().size());
        assertEquals(day1, testSchedule.getDays().get(0));

        testSchedule.addDayToSchedule(day2);
        assertEquals(2, testSchedule.getDays().size());
        assertEquals(day2, testSchedule.getDays().get(1));

        testSchedule.addDayToSchedule(day3);
        assertEquals(3, testSchedule.getDays().size());
        assertEquals(day3, testSchedule.getDays().get(2));
    }

    @Test
    void testAddNewDay() {
        testSchedule.createNewDay();
        assertEquals(3, testSchedule.getDays().size());
        ArrayList<Day> dayList = testSchedule.getDays();
        int dayNum1 = dayList.get(0).getDayNum();
        int dayNum3 = dayList.get(2).getDayNum();
        assertEquals(1, dayNum1);
        assertEquals(3,dayNum3);

        testSchedule.createNewDay();
        assertEquals(4,testSchedule.getDays().size());
        int dayNum4 = dayList.get(3).getDayNum();
        assertEquals(4, dayNum4);
        assertEquals(1, dayNum1);
        assertEquals(3,dayNum3);

    }

    @Test
    void testViewSchedule() {
        ArrayList<String> schedule1 = new ArrayList<>();
        schedule1.add("01:00 - 02:00: Attraction By me \nnote: Emily's note");
        schedule1.add("07:00 - 09:00: Park: ParkA \nnote: Bring umbrella");
        schedule1.add("13:00 - 14:00: Restaurant: RestaurantA \nnote: Bring money");
        schedule1.add("14:00 - 16:00: Beach: BeachA \nnote: Bring food");

        ArrayList<String> schedule2 = new ArrayList<>();
        schedule2.add("09:00 - 12:00: Trail: TrailA \nnote: Bring water");
    }

    @Test
    void testCreateNewDay(){
        Day newDay = testSchedule.createNewDay();
        ArrayList<Day> newlycreatedDay = testSchedule.getDays();
        Day day = newlycreatedDay.get(2);
        assertEquals(day, newDay);
    }

}
