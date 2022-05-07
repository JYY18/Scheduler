package persistence;

import model.Attraction;
import model.Day;
import model.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    Schedule s;
    ArrayList<Day> days;

    @BeforeEach
    void runBefore() {
        s = new Schedule();
        days = new ArrayList<>();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySchedule() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySchedule.json");
        try {
            s = reader.read();
            assertEquals(1, s.getDays().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEventInOneDay(){
        JsonReader reader = new JsonReader("./data/testReaderEventInOneDay.json");
        try {
            s = reader.read();
            assertEquals(1, s.getDays().size());
            days = s.getDays();
            Day day1 = days.get(0);
            Attraction attraction1 = day1.getAttractionsAdded().get(0);

            checkDay(1, day1);

            checkAttraction(1,"Stanley Park", "12:00", "14:00",
                    "bring umbrella", attraction1);


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // numebr of attractions in a day

    @Test
    void testReaderEventsInMultipleDays() {
        JsonReader reader = new JsonReader("./data/testReaderEventsInMultipleDays.json");
        try {
            Schedule s = reader.read();
            assertEquals(3,s.getDays().size());

            // 2 attractions in day 1
            days = s.getDays();
            Day day1 = days.get(0);
            checkDay(2, day1);

            Attraction attraction1 = day1.getAttractionsAdded().get(0);
            Attraction attraction2 = day1.getAttractionsAdded().get(1);
            checkAttraction(1,"Lynn Canyon", "07:00", "10:00",
                    "bring camera", attraction1);
            checkAttraction(1, "Spanish Banks", "10:00", "12:00",
                    "bring sandals", attraction2);


            // 0 in day2
            Day day2 = days.get(1);
            checkDay(0, day2);

            // 1 in day3
            Day day3 = days.get(2);
            checkDay(1, day3);
            Attraction attraction3 = day3.getAttractionsAdded().get(0);
            checkAttraction(3, "Quarry Rock Hike", "09:00", "11:00",
                    "bring water", attraction3);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
