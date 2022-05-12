package ui.tools;

import model.Attraction;
import model.Day;
import model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScheduleListWindow {
    private Schedule schedule;
    private int dayNum;
    JComponent scheduleArea;

    public ScheduleListWindow(Schedule schedule, int dayNum, JComponent scheduleArea) {
        this.schedule = schedule;
        this.dayNum = dayNum;
        this.scheduleArea = scheduleArea;

        initializeGraphics();

    }

    public void initializeGraphics() {
        //scheduleArea.removeAll();

        ArrayList<Day> days = schedule.getDays();

        Day day = days.get(dayNum);

        ArrayList<Attraction> attractions = day.getAttractionsAdded();

        for (Attraction attraction: attractions) {
            addToData(attraction, scheduleArea);
        }

        scheduleArea.revalidate();
        scheduleArea.repaint();

    }

    // EFFECTS: adds attraction information to be displayed
    public void addToData(Attraction attraction, JComponent contentPanel) {
        if (attraction.getAttractionDayNumber() == this.dayNum) {
            ArrayList<String> data = new ArrayList<>();
            data.add(attraction.getAttractionName());
            data.add(attraction.getAttractionFromTime());
            data.add(attraction.getAttractionToTime());
            data.add(attraction.getNote());
            contentPanel.add(addContentLine(data));
        }
    }

    // EFFECTS: adds each information in column form
    private JPanel addContentLine(ArrayList<String> line) {
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (String s : line) {
            JLabel label = new JLabel(s);
            label.setPreferredSize(new Dimension(228, 22)); // distance from prev column
            label.setFont(new Font(null, Font.PLAIN, 18));
            jp.add(label);
        }
        return jp;
    }

}
