package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tool to open park selection window
public class ViewParksTool extends Tool implements ActionListener {
    private String[] parks = {"Stanley Park", "Queen Elizabeth Park", "Nitobe Memorial Garden", "Vanier park", "Capilano Suspension Bridge Park"};
    JComponent parent;
    JComponent scheduleArea; // to be passed to attractionsWindow

    ScheduleApp scheduleApp;
    JButton stanleyPark;
    JButton queenElizabethPark;


    JButton nitobeMemorialGarden;
    JButton vanierPark;
    JButton capilanoSuspensionBridgePark;

    JPanel parkSelection;

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
        capilanoSuspensionBridgePark = new JButton("Capilino SuspensionBridge Park");

        parkSelection = new JPanel(new GridLayout(5, 0, 0, 0));

        this.daysOfTheWeek = daysOfTheWeek;

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

            parent.removeAll();
            parent.revalidate();
            parent.repaint();


            parkSelection.setBounds(0, 0, 740, 770);

            createParkSelection(parkSelection);

            ActionListener parksActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.removeAll();
                    parent.revalidate();
                    parent.repaint();
                    createBackPanelAndButton("ParkList");
                    if (e.getSource() == stanleyPark) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea,"Stanley Park", parent, "stanleyPark.jpg", daysOfTheWeek);

                    } else if (e.getSource() == queenElizabethPark) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Queen Elizabeth Park", parent, "queenElizabethPark.jpg", daysOfTheWeek);

                    } else if (e.getSource() == nitobeMemorialGarden) {
                        new AttractionsWindow(scheduleApp.getSchedule(),scheduleArea, "Nitobe Memorial Garden", parent, "nitobeMemorialGarden.jpg", daysOfTheWeek);
                    } else if (e.getSource() == vanierPark) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Vanier Park", parent, "vanierPark.jpg", daysOfTheWeek);
                    } else if (e.getSource() == capilanoSuspensionBridgePark) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Capilano Suspension Bridge Park", parent, "capilanoSuspensionBridgePark.jpg", daysOfTheWeek);
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

        parkSelection.add(nitobeMemorialGarden);
        parkSelection.add(vanierPark);
        parkSelection.add(capilanoSuspensionBridgePark);


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
