package ui.tools;

import model.EventLog;
import persistence.JsonReader;
import ui.ScheduleApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// loads schedule
public class LoadTool extends Tool implements ActionListener {
    private static final String JSON_STORE = "./data/schedule.json";
    private JsonReader jsonReader;

    // Constructor
    public LoadTool(ScheduleApp scheduleApp, JComponent parent) {
        super(scheduleApp, parent);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: creates "load" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load");
        button.setFont(new Font(null, Font.PLAIN, 18));
        button.setBounds(145,30,100,50);
        button.setBackground(Color.LIGHT_GRAY);

        addToParent(parent);
        button.addActionListener(this);
    }

    // EFFECTS: reads saved file and loads schedule when button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            try {
                jsonReader.read();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.scheduleApp.loadSchedule();
            EventLog.getInstance().clear();
        }

    }
}
