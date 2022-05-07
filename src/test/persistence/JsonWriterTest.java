package persistence;

import model.Attraction;
import model.Day;
import model.Schedule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Schedule s = new Schedule();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySchedule (){
        try {
            Schedule s = new Schedule();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySchedule.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySchedule.json");
            s = reader.read();
            assertEquals(0, s.getDays().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralSchedule() {
        try {
            Schedule s = new Schedule();
            Day day1 = new Day(1);
            Day day2 = new Day(2);
            Day day3 = new Day(3);
            Attraction attraction1 = new Attraction(1, "Stanley Park",
                    "06:00", "07:00");
            Attraction attraction2 = new Attraction(1, "Spanish Banks",
                    "10:00", "12:00");
            Attraction attraction3 = new Attraction(3, "Quarry Rock Hike",
                    "09:00", "14:00");

            day1.addAttractionAndNote(attraction1, "bring water");
            day1.addAttractionAndNote(attraction2, "bring sandals");
            day3.addAttractionAndNote(attraction3, "bring food");

            s.addDayToSchedule(day1);
            s.addDayToSchedule(day2);
            s.addDayToSchedule(day3);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSchedule.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSchedule.json");
            s = reader.read();

            ArrayList days = s.getDays();
            checkDay(2, day1);

            checkAttraction(1, "Stanley Park", "06:00", "07:00",
                    "bring water", attraction1);
            checkAttraction(1, "Spanish Banks", "10:00", "12:00",
                    "bring sandals", attraction2);


            // 0 in day2
            checkDay(0, day2);

            // 1 in day3
            checkDay(1, day3);
            checkAttraction(3, "Quarry Rock Hike", "09:00", "14:00",
                    "bring food", attraction3);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
