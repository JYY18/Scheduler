package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;

// parent class for each tool
public abstract class Tool {
    protected JButton button;
    protected ScheduleApp scheduleApp;
    protected int dayn;


    // Constructor
    public Tool(ScheduleApp scheduleApp, JComponent parent) {
        this.scheduleApp = scheduleApp;
        createButton(parent);
        addToParent(parent);
    }

    // EFFECTS: creates new button
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds button to parent
    public void addToParent(JComponent parent) {
        parent.add(button);
    }


}
