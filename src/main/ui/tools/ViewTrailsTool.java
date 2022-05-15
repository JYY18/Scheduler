package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Tool to open park selection window
public class ViewTrailsTool extends Tool implements ActionListener {
    JComponent parent;
    JComponent scheduleArea; // to be passed to attractionsWindow

    ScheduleApp scheduleApp;
    JButton grouseGrindTrail;
    JButton deepCoveQuarryRock;


    JButton saintMarksSUmmit;
    JButton salishTrail;
    JButton lynnPeakTrail;

    JPanel trailSelection;

    JPanel buttonsPanel;

    JComboBox daysOfTheWeek;


    // Constructor
    public ViewTrailsTool(ScheduleApp scheduleApp, JComponent parent, JComponent scheduleArea, JComboBox daysOfTheWeek) {
        super(scheduleApp, parent);
        this.parent = parent;
        this.scheduleArea = scheduleArea;

        this.scheduleApp = scheduleApp;

        grouseGrindTrail = new JButton("Grouse Grind Trail");
        deepCoveQuarryRock = new JButton("Deep Cove Quarry Rock");
        saintMarksSUmmit = new JButton("Saint Marks Summit");
        salishTrail = new JButton("Salish Trail");
        lynnPeakTrail = new JButton("Lynn Peak Trail");

        ArrayList<JButton> buttonList = new ArrayList();
        buttonList.add(grouseGrindTrail);
        buttonList.add(deepCoveQuarryRock);
        buttonList.add(saintMarksSUmmit);
        buttonList.add(salishTrail);
        buttonList.add(lynnPeakTrail);

        for (JButton b : buttonList) {
            b.setBackground(new Color(238, 238, 238));
            b.setBorder(new LineBorder(Color.BLACK, 1));
        }


        trailSelection = new JPanel(new GridLayout(5, 0, 0, 0));

        buttonsPanel = new JPanel();
        this.daysOfTheWeek = daysOfTheWeek;

    }


    // MODIFIES: this
    // EFFECTS: creates "View Parks" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Trails");
        button.setFont(new Font(null, Font.PLAIN, 30));
        button.setBounds(100, 314, 540, 100);
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


            trailSelection.setBounds(0, 0, 740, 780);

            createParkSelection(trailSelection);

            ActionListener parksActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.removeAll();
                    parent.revalidate();
                    parent.repaint();
                    createBackPanelAndButton("ParkList");
                    returnToMenu();

                    if (e.getSource() == grouseGrindTrail) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Grouse Grind Trail", parent, "grouseGrindTrail.jpg", "MapGrouse.jpg", daysOfTheWeek);

                    } else if (e.getSource() == deepCoveQuarryRock) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Deep Cove Quarry Rock", parent, "deepCoveQuarryRock.jpg", "MapDeep.jpg", daysOfTheWeek);

                    } else if (e.getSource() == saintMarksSUmmit) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Saint Marks Summit", parent, "saintMarksSummit.jpg", "MapSaint.jpg", daysOfTheWeek);

                    } else if (e.getSource() == salishTrail) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Salish Trail", parent, "salishTrail.jpg", "MapSalish.jpg", daysOfTheWeek);

                    } else if (e.getSource() == lynnPeakTrail) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Lynn Peak Trail", parent, "lynnPeakTrail.jpg", "MapLynn.jpg", daysOfTheWeek);
                    }
                }
            };

            grouseGrindTrail.addActionListener(parksActionListener);
            deepCoveQuarryRock.addActionListener(parksActionListener);
            saintMarksSUmmit.addActionListener(parksActionListener);
            salishTrail.addActionListener(parksActionListener);
            lynnPeakTrail.addActionListener(parksActionListener);
        }
    }

    public void createParkSelection(JComponent parkSelection) {

        grouseGrindTrail.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(grouseGrindTrail);

        deepCoveQuarryRock.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(deepCoveQuarryRock);

        saintMarksSUmmit.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(saintMarksSUmmit);

        salishTrail.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(salishTrail);

        lynnPeakTrail.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(lynnPeakTrail);


        parent.add(parkSelection);

        createBackPanelAndButton("Menu");
        returnToMenu();


    }

    public void createBackPanelAndButton(String typeOfPanelToReturn) {
        buttonsPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(5, 0, 735, 0);
            }
        };

        buttonsPanel.setBounds(0, 780, 740, 90);
        buttonsPanel.setLayout(null);
        buttonsPanel.setBorder(new LineBorder(Color.BLACK, 1));


        JButton back = new JButton("Back");
        back.setFont(new Font(null, Font.PLAIN, 18));
        back.setBounds(20, 22, 90, 45);
        buttonsPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                parent.removeAll();
                parent.revalidate();
                parent.repaint();
                if (typeOfPanelToReturn == "Menu") {
                    scheduleApp.createMenu();
                } else if (typeOfPanelToReturn == "ParkList") {
                    createParkSelection(trailSelection);
                }
            }
        });

        parent.add(buttonsPanel);
    }

    public void returnToMenu() {
        JButton menuButton = new JButton("Menu");
        menuButton.setFont(new Font(null, Font.PLAIN, 18));
        menuButton.setBounds(630, 22, 90, 45);
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
