package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// saves schedule
public class SaveTool extends Tool implements ActionListener {

    // Constructor
    public SaveTool(ScheduleApp scheduleApp, JComponent parent) {
        super(scheduleApp, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates "Save" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        button.setFont(new Font(null, Font.PLAIN, 18));
        button.setBounds(25,30,100,50);
        button.setBackground(Color.LIGHT_GRAY);

        addToParent(parent);
        button.addActionListener(this);

    }

    // EFFECTS: saves schedule when button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.scheduleApp.saveSchedule();
        }

    }
}
