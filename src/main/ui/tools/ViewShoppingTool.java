package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Tool to open park selection window
public class ViewShoppingTool extends Tool implements ActionListener {
    private String[] parks = {"Stanley Park", "Queen Elizabeth Park", "Nitobe Memorial Garden", "Vanier park", "Capilano Suspension Bridge Park"};
    JComponent parent;
    JComponent scheduleArea; // to be passed to attractionsWindow

    ScheduleApp scheduleApp;
    JButton parkRoyalShoppingCentre;
    JButton granvilleIsland;


    JButton robsonStreet;
    JButton tsawwassenMills;
    JButton pacificCentre;

    JPanel shoppingSelection;

    JPanel buttonsPanel;

    JComboBox daysOfTheWeek;


    // Constructor
    public ViewShoppingTool(ScheduleApp scheduleApp, JComponent parent, JComponent scheduleArea, JComboBox daysOfTheWeek) {
        super(scheduleApp, parent);
        this.parent = parent;
        this.scheduleArea = scheduleArea;

        this.scheduleApp = scheduleApp;

        parkRoyalShoppingCentre = new JButton("Park Royal Shopping Centre");
        granvilleIsland = new JButton("Granville Island");
        robsonStreet = new JButton("Robson Street");
        tsawwassenMills = new JButton("Tsawwassen Mills");
        pacificCentre = new JButton("Pacific Centre");

        ArrayList<JButton> buttonList = new ArrayList();
        buttonList.add(parkRoyalShoppingCentre);
        buttonList.add(granvilleIsland);
        buttonList.add(robsonStreet);
        buttonList.add(tsawwassenMills);
        buttonList.add(pacificCentre);

        for (JButton b: buttonList) {
            b.setBackground(new Color(238,238,238));
            b.setBorder(new LineBorder(Color.BLACK,1));
        }

        shoppingSelection = new JPanel(new GridLayout(5, 0, 0, 0));

        buttonsPanel = new JPanel();
        this.daysOfTheWeek = daysOfTheWeek;

    }


    // MODIFIES: this
    // EFFECTS: creates "View Parks" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Shopping");
        button.setFont(new Font(null, Font.PLAIN, 30));
        button.setBounds(150,578,540,100);
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


            shoppingSelection.setBounds(0, 0, 740, 780);

            createParkSelection(shoppingSelection);

            ActionListener parksActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.removeAll();
                    parent.revalidate();
                    parent.repaint();
                    createBackPanelAndButton("ParkList");
                    returnToMenu();
                    if (e.getSource() == parkRoyalShoppingCentre) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea,"Park Royal Shopping Centre", parent, "parkRoyalShoppingCentre.jpg", "MapPark.jpg",daysOfTheWeek);

                    } else if (e.getSource() == granvilleIsland) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Granville Island", parent, "granvilleIsland.jpg", "MapGranville.jpg", daysOfTheWeek);

                    } else if (e.getSource() == robsonStreet) {
                        new AttractionsWindow(scheduleApp.getSchedule(),scheduleArea, "Robson Street", parent, "robsonStreet.jpg", "MapRobson.jpg",daysOfTheWeek);

                    } else if (e.getSource() == tsawwassenMills) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Tsawwassen Mills", parent, "tsawwassenMills.jpg", "MapTsawwassen.jpg", daysOfTheWeek);

                    } else if (e.getSource() == pacificCentre) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Pacific Centre", parent, "pacificCentre.jpg","MapPacific.jpg", daysOfTheWeek);
                    }
                }
            };

            parkRoyalShoppingCentre.addActionListener(parksActionListener);
            granvilleIsland.addActionListener(parksActionListener);
            robsonStreet.addActionListener(parksActionListener);
            tsawwassenMills.addActionListener(parksActionListener);
            pacificCentre.addActionListener(parksActionListener);
        }
    }

    public void createParkSelection(JComponent parkSelection) {

        parkRoyalShoppingCentre.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(parkRoyalShoppingCentre);

        granvilleIsland.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(granvilleIsland);

        robsonStreet.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(robsonStreet);

        tsawwassenMills.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(tsawwassenMills);

        pacificCentre.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(pacificCentre);


        parent.add(parkSelection);

        createBackPanelAndButton("Menu");
        returnToMenu();


    }

    public void createBackPanelAndButton(String typeOfPanelToReturn) {
        buttonsPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(5,0,735,0);
                buttonsPanel.setBorder(new LineBorder(Color.BLACK,1));

            }
        };

        buttonsPanel.setBounds(0,780,740,90);
        buttonsPanel.setLayout(null);


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
                    createParkSelection(shoppingSelection);
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
