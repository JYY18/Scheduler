package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tool to open park selection window
public class ViewParksTool extends Tool implements ActionListener {
    private String[] parks = {"Stanley Park", "Queen Elizabeth Park", "Lynn Canyon"};
    JComponent parent;
    JComponent scheduleArea; // to be passed to attractionsWindow

    ScheduleApp scheduleApp;
    JButton parkTest;
    JButton parkTest2;


    JButton b3;
    JButton b4;
    JButton b5;

    JPanel parkSelection;


    // Constructor
    public ViewParksTool(ScheduleApp scheduleApp, JComponent parent, JComponent scheduleArea) {
        super(scheduleApp, parent);
        this.parent = parent;
        this.scheduleArea = scheduleArea;

        this.scheduleApp = scheduleApp;

        parkTest = new JButton("Stanley Park");
        parkTest2 = new JButton("Fleetwood Park");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");

        parkSelection = new JPanel(new GridLayout(5, 0, 0, 0));

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



    // EFFECTS: when view parks is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            //new AttractionsWindow(scheduleApp.getSchedule(), "Parks", parks, "park.jpg");

            parent.removeAll();
            parent.revalidate();
            parent.repaint();


            parkSelection.setBounds(0, 0, 740, 770);

            createParkSelection(parkSelection);

            ActionListener parksActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == parkTest) {
                        parent.removeAll();
                        parent.revalidate();
                        parent.repaint();
                        createBackPanelAndButton("ParkList");
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea,"Stanley Park", parent, "park.jpg");

                        // TODO

                    } else if (e.getSource() == parkTest2) {
                        System.out.println("F");
                    }
                }
            };

            parkTest.addActionListener(parksActionListener);
            parkTest2.addActionListener(parksActionListener);
        }
    }

    public void createParkSelection(JComponent parkSelection) {

        parkTest.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(parkTest);

        parkTest2.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(parkTest2);

        parkSelection.add(b3);
        parkSelection.add(b4);
        parkSelection.add(b5);


        parent.add(parkSelection);

        createBackPanelAndButton("Menu");


    }

    public void createBackPanelAndButton(String typeOfPanelToReturn) {
            JPanel buttonsPanel = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawLine(5,0,735,0);
                }
            };

            buttonsPanel.setBounds(0,770,740,100);
            //buttonsPanel.setBackground(Color.WHITE);
            buttonsPanel.setLayout(null);


            JButton back = new JButton("Back");
            back.setFont(new Font(null, Font.PLAIN, 18));
            back.setBounds(20,20,100,50);
            buttonsPanel.add(back);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    parent.removeAll();
                    parent.revalidate();
                    parent.repaint();
                    if (typeOfPanelToReturn == "Menu"){
                        scheduleApp.createMenu();
                    } else if (typeOfPanelToReturn == "ParkList") {
                        createParkSelection(parkSelection);
                    }
                }
            });

            parent.add(buttonsPanel);

    }

}
