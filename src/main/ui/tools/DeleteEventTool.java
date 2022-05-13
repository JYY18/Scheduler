package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// tool to delete an event
public class DeleteEventTool extends Tool implements ActionListener {

    // constructor
    public DeleteEventTool(ScheduleApp scheduleApp, JComponent parent) {
        super(scheduleApp, parent);
    }


    // MODIFIES: this
    // EFFECTS: creates new "Delete an event" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Delete event");
        button.setFont(new Font(null, Font.PLAIN, 18));
        button.setBounds(265,30,150,50);
        button.setBackground(Color.LIGHT_GRAY);

        addToParent(parent);
        button.addActionListener(this);

    }

    // EFFECTS: opens deleteEventWindow when button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            //this.scheduleApp.loadSchedule();
            new DeleteEventWindow(this.scheduleApp.getSchedule());
        }
    }

}