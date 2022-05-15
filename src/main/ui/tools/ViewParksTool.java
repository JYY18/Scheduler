package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Tool to open park selection window
public class ViewParksTool extends Tool implements ActionListener {
    JComponent parent;
    JComponent scheduleArea; // to be passed to attractionsWindow

    ScheduleApp scheduleApp;
    JButton stanleyPark;
    JButton queenElizabethPark;


    JButton nitobeMemorialGarden;
    JButton vanierPark;
    JButton capilanoSuspensionBridgePark;

    JPanel parkSelection;

    JPanel buttonsPanel;

    JComboBox daysOfTheWeek;


    // Constructor
    public ViewParksTool(ScheduleApp scheduleApp, JComponent parent, JComponent scheduleArea, JComboBox daysOfTheWeek) {
        super(scheduleApp, parent);
        this.parent = parent;
        this.scheduleArea = scheduleArea;

        this.scheduleApp = scheduleApp;

        stanleyPark = new JButton("Stanley Park");
        queenElizabethPark = new JButton("Queen Elizabeth Park");
        nitobeMemorialGarden = new JButton("Nitobe Memorial Garden");
        vanierPark = new JButton("Vanier Garden");
        capilanoSuspensionBridgePark = new JButton("Capilano Suspension Bridge Park");

        ArrayList<JButton> buttonList = new ArrayList();
        buttonList.add(stanleyPark);
        buttonList.add(queenElizabethPark);
        buttonList.add(nitobeMemorialGarden);
        buttonList.add(vanierPark);
        buttonList.add(capilanoSuspensionBridgePark);

        for (JButton b: buttonList) {
            b.setBackground(new Color(238,238,238));
            b.setBorder(new LineBorder(Color.BLACK,1));
        }

        parkSelection = new JPanel(new GridLayout(5, 0, 0, 0));

        buttonsPanel = new JPanel();
        this.daysOfTheWeek = daysOfTheWeek;


    }


    // MODIFIES: this
    // EFFECTS: creates "View Parks" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Parks");
        button.setFont(new Font(null, Font.PLAIN, 30));
        button.setBounds(100,50,540,100);
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


            parkSelection.setBounds(0, 0, 740, 780);

            createParkSelection(parkSelection);

            ActionListener parksActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.removeAll();
                    parent.revalidate();
                    parent.repaint();
                    createBackPanelAndButton("ParkList");
                    returnToMenu();
                    if (e.getSource() == stanleyPark) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea,"Stanley Park", parent, "stanleyPark.jpg", "MapStanley.jpg",daysOfTheWeek);

                    } else if (e.getSource() == queenElizabethPark) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Queen Elizabeth Park", parent, "queenElizabethPark.jpg", "MapQueenElizabeth.jpg", daysOfTheWeek);

                    } else if (e.getSource() == nitobeMemorialGarden) {
                        new AttractionsWindow(scheduleApp.getSchedule(),scheduleArea, "Nitobe Memorial Garden", parent, "nitobeMemorialGarden.jpg", "MapNitobe.jpg",daysOfTheWeek);

                    } else if (e.getSource() == vanierPark) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Vanier Park", parent, "vanierPark.jpg", "MapVanier.jpg", daysOfTheWeek);

                    } else if (e.getSource() == capilanoSuspensionBridgePark) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Capilano Suspension Bridge Park", parent, "capilanoSuspensionBridgePark.jpg","MapCapilano.jpg", daysOfTheWeek);
                    }
                }
            };

            stanleyPark.addActionListener(parksActionListener);
            queenElizabethPark.addActionListener(parksActionListener);
            nitobeMemorialGarden.addActionListener(parksActionListener);
            vanierPark.addActionListener(parksActionListener);
            capilanoSuspensionBridgePark.addActionListener(parksActionListener);
        }
    }

    public void createParkSelection(JComponent parkSelection) {

        stanleyPark.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(stanleyPark);

        queenElizabethPark.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(queenElizabethPark);

        nitobeMemorialGarden.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(nitobeMemorialGarden);

        vanierPark.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(vanierPark);

        capilanoSuspensionBridgePark.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(capilanoSuspensionBridgePark);


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
                        createParkSelection(parkSelection);
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
