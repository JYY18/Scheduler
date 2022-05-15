package ui.tools;

import model.Attraction;
import model.Day;
import model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteEventWindow extends JFrame implements ActionListener {

    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private Schedule schedule;
    private JComboBox daysComboBox = new JComboBox(days);
    private JComboBox fromComboBox = new JComboBox();
    private JComboBox toComboBox = new JComboBox();
    private JLabel nameLabel = new JLabel();

    private Day day;

    // Constructor
    public DeleteEventWindow(Schedule schedule) {
        super("Delete An Event");

        this.schedule = schedule;
        daysComboBox.setPreferredSize(new Dimension(100, 28));
        fromComboBox.setPreferredSize(new Dimension(200, 28));
        toComboBox.setPreferredSize(new Dimension(200, 28));
        nameLabel.setPreferredSize(new Dimension(200, 28));
        initializeGraphics();

    }

    // MODIFIES: this
    // EFFECTS: initializes window graphics
    private void initializeGraphics() {
        // add(Box.createVerticalStrut(400));

        JPanel deletePanel = new JPanel();
        deletePanel.add(Box.createVerticalStrut(100));
        deletePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        deletePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        deletePanel.add(daysComboBox);

        deletePanel.add(new JLabel("  From:"));
        deletePanel.add(fromComboBox);

        deletePanel.add(new JLabel("  To:"));
        deletePanel.add(toComboBox);

        JButton deleteButton = new JButton("Delete");
        deletePanel.add(deleteButton);
        deleteButton.addActionListener(this);

        helperMethod();

        add(deletePanel);

        pack();

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up to time and from time when a day is selected
    private void helperMethod() {
        daysComboBox.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int dayIndex = daysComboBox.getSelectedIndex();
                        ArrayList<Day> listOfDay = schedule.getDays();
//
                        day = listOfDay.get(dayIndex);


                        ArrayList<String> names = new ArrayList<>();
                        ArrayList<String> ftimes = new ArrayList();
                        ArrayList<String> ttimes = new ArrayList();
                        if (day != null) {
                            ArrayList<Attraction> attractions = day.getAttractionsAdded();
                            for (Attraction a : attractions) {
                                names.add(a.getAttractionName());
                                ftimes.add(a.getAttractionFromTime());
                                ttimes.add(a.getAttractionToTime());
                            }
                        }

                        fromComboBox.setModel(new DefaultComboBoxModel(ftimes.toArray(new String[0])));
                        toComboBox.setModel(new DefaultComboBoxModel(ttimes.toArray(new String[0])));
                    }
                }
        );

    }

    // EFFECTS: deletes an event when "delete" button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.day != null) {
            int deleteIndex = fromComboBox.getSelectedIndex();
            int t = toComboBox.getSelectedIndex();
            System.out.println(deleteIndex);
            System.out.println(t);

            if (deleteIndex == t ) {
                Attraction a = day.getAttractionsAdded().get(deleteIndex);


                // comboBox remove
                toComboBox.removeItem(a.getAttractionToTime());
                fromComboBox.removeItem(a.getAttractionFromTime());

                // remove from Day
                day.deleteEvent(deleteIndex);
            }

        }
    }
}