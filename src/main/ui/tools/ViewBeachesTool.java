package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewBeachesTool extends Tool implements ActionListener {
    JComponent parent;
    JComponent scheduleArea; // to be passed to attractionsWindow

    ScheduleApp scheduleApp;
    JButton englishBayBeach;
    JButton kitsilanoBeach;


    JButton wreckBeach;
    JButton jerichoBeach;
    JButton spanishBanksBeach;

    JPanel beachSelection;
    JPanel buttonsPanel;

    JComboBox daysOfTheWeek;


    // Constructor
    public ViewBeachesTool(ScheduleApp scheduleApp, JComponent parent, JComponent scheduleArea, JComboBox daysOfTheWeek) {
        super(scheduleApp, parent);
        this.parent = parent;
        this.scheduleArea = scheduleArea;

        this.scheduleApp = scheduleApp;
        this.buttonsPanel = new JPanel();

        englishBayBeach = new JButton("English Bay Beach");
        kitsilanoBeach = new JButton("Kitsilano Beach");
        wreckBeach = new JButton("Wreck Beach");
        jerichoBeach = new JButton("Jericho Beach");
        spanishBanksBeach = new JButton("Spanish Banks Beach");

        ArrayList<JButton> buttonList = new ArrayList();
        buttonList.add(englishBayBeach);
        buttonList.add(kitsilanoBeach);
        buttonList.add(wreckBeach);
        buttonList.add(jerichoBeach);
        buttonList.add(spanishBanksBeach);

        for (JButton b: buttonList) {
            b.setBackground(new Color(238,238,238));
            b.setBorder(new LineBorder(Color.BLACK,1));
        }

        beachSelection = new JPanel(new GridLayout(5, 0, 0, 0));

        this.daysOfTheWeek = daysOfTheWeek;

    }


    // MODIFIES: this
    // EFFECTS: creates "View Parks" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Beaches");
        button.setFont(new Font(null, Font.PLAIN, 30));
        button.setBounds(150,182,540,100);
        button.setBackground(Color.LIGHT_GRAY);

        button.setFocusable(false);
        button.addActionListener(this);
        addToParent(parent);

    }

    // EFFECTS: when view parks is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {

            parent.removeAll();
            parent.revalidate();
            parent.repaint();


            beachSelection.setBounds(0, 0, 740, 780);

            createParkSelection(beachSelection);

            ActionListener parksActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.removeAll();
                    parent.revalidate();
                    parent.repaint();
                    createBackPanelAndButton("ParkList");
                    returnToMenu();
                    if (e.getSource() == englishBayBeach) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea,"English Bay Beach", parent, "englishBayBeach.jpg", "MapEnglish.jpg",daysOfTheWeek);

                    } else if (e.getSource() == kitsilanoBeach) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Kitsilano Beach", parent, "kitsilanoBeach.jpg", "MapKitsilano.jpg", daysOfTheWeek);

                    } else if (e.getSource() == wreckBeach) {
                        new AttractionsWindow(scheduleApp.getSchedule(),scheduleArea, "Wreck Beach", parent, "wreckBeach.jpg", "MapWreck.jpg",daysOfTheWeek);

                    } else if (e.getSource() == jerichoBeach) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Jericho Beach", parent, "jerichoBeach.jpg", "MapJericho.jpg", daysOfTheWeek);

                    } else if (e.getSource() == spanishBanksBeach) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Spanish Banks Beach", parent, "spanishBanksBeach.jpg","MapSpanish.jpg", daysOfTheWeek);
                    }
                }
            };

            englishBayBeach.addActionListener(parksActionListener);
            kitsilanoBeach.addActionListener(parksActionListener);
            wreckBeach.addActionListener(parksActionListener);
            jerichoBeach.addActionListener(parksActionListener);
            spanishBanksBeach.addActionListener(parksActionListener);
        }
    }

    public void createParkSelection(JComponent parkSelection) {

        englishBayBeach.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(englishBayBeach);

        kitsilanoBeach.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(kitsilanoBeach);

        wreckBeach.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(wreckBeach);

        jerichoBeach.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(jerichoBeach);

        spanishBanksBeach.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(spanishBanksBeach);


        parent.add(parkSelection);

        createBackPanelAndButton("Menu");
        returnToMenu();


    }

    public void createBackPanelAndButton(String typeOfPanelToReturn) {
        buttonsPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(5,0,735,0);
            }
        };

        buttonsPanel.setBounds(0,780,740,90);
        buttonsPanel.setLayout(null);
        buttonsPanel.setBorder(new LineBorder(Color.BLACK,1));


        JButton back = new JButton("Back");
        back.setFont(new Font(null, Font.PLAIN, 18));
        back.setBounds(20,22,90,45);
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
                    createParkSelection(beachSelection);
                }
            }
        });

        parent.add(buttonsPanel);
    }

    public void returnToMenu() {
        JButton menuButton = new JButton("Menu");
        menuButton.setFont(new Font(null, Font.PLAIN, 18));
        menuButton.setBounds(630,22,90,45);
        buttonsPanel.add(menuButton);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.removeAll();
                parent.revalidate();
                parent.repaint();
                scheduleApp.createMenu();
            }
        });
    }

}
