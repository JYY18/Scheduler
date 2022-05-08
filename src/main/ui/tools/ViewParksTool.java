package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tool to open park selection window
public class ViewParksTool extends Tool implements ActionListener {
    private String[] parks = {"Stanley Park", "Queen Elizabeth Park", "Lynn Canyon"};


    // Constructor
    public ViewParksTool(ScheduleApp scheduleApp, JComponent parent) {
        super(scheduleApp, parent);
    }


    // MODIFIES: this
    // EFFECTS: creates "View Parks" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Parks");
        button.setFont(new Font(null, Font.PLAIN, 30));
        button.setBounds(150,50,540,100);
        button.setBackground(Color.LIGHT_GRAY);

        button.setFocusable(false);
        button.addActionListener(this);
        addToParent(parent);

    }

    // EFFECTS: creates new attraction window with its properties when button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            //new AttractionsWindow(scheduleApp.getSchedule(), "Parks", parks, "park.jpg");
        }
    }
}
