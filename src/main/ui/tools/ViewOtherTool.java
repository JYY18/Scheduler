package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Tool to open park selection window
public class ViewOtherTool extends Tool implements ActionListener {
    private String[] parks = {"Stanley Park", "Queen Elizabeth Park", "Nitobe Memorial Garden", "Vanier park", "Capilano Suspension Bridge Park"};
    JComponent parent;
    JComponent scheduleArea; // to be passed to attractionsWindow

    ScheduleApp scheduleApp;
    JButton gastownSteamClock;
    JButton flyoverCanada;


    JButton vancouverAquarium;
    JButton rogerArena;
    JButton vancouverArtGallery;

    JPanel parkSelection;

    JPanel buttonsPanel;

    JComboBox daysOfTheWeek;


    // Constructor
    public ViewOtherTool(ScheduleApp scheduleApp, JComponent parent, JComponent scheduleArea, JComboBox daysOfTheWeek) {
        super(scheduleApp, parent);
        this.parent = parent;
        this.scheduleArea = scheduleArea;

        this.scheduleApp = scheduleApp;

        gastownSteamClock = new JButton("Gastown Steam Clock");
        flyoverCanada = new JButton("Flyover Canada");
        vancouverAquarium = new JButton("Vancouver Aquarium");
        rogerArena = new JButton("Roger Arena");
        vancouverArtGallery = new JButton("Vancouver Art Gallery");

        ArrayList<JButton> buttonList = new ArrayList();
        buttonList.add(gastownSteamClock);
        buttonList.add(flyoverCanada);
        buttonList.add(vancouverAquarium);
        buttonList.add(rogerArena);
        buttonList.add(vancouverArtGallery);

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
        button = new JButton("View Other");
        button.setFont(new Font(null, Font.PLAIN, 30));
        button.setBounds(100,710,540,100);
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
                    if (e.getSource() == gastownSteamClock) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea,"Gastown Steam Clock", parent, "gastownSteamClock.jpg", "MapGas.jpg",daysOfTheWeek);

                    } else if (e.getSource() == flyoverCanada) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Flyover Canada", parent, "flyoverCanada.jpg", "MapFly.jpg", daysOfTheWeek);

                    } else if (e.getSource() == vancouverAquarium) {
                        new AttractionsWindow(scheduleApp.getSchedule(),scheduleArea, "Vancouver Aquarium", parent, "vancouverAquarium.jpg", "MapAquarium.jpg",daysOfTheWeek);

                    } else if (e.getSource() == rogerArena) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Roger Arena", parent, "rogerArena.jpg", "MapRoger.jpg", daysOfTheWeek);

                    } else if (e.getSource() == vancouverArtGallery) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Vancouver Art Gallery", parent, "vancouverArtGallery.jpg","MapArt.jpg", daysOfTheWeek);
                    }
                }
            };

            gastownSteamClock.addActionListener(parksActionListener);
            flyoverCanada.addActionListener(parksActionListener);
            vancouverAquarium.addActionListener(parksActionListener);
            rogerArena.addActionListener(parksActionListener);
            vancouverArtGallery.addActionListener(parksActionListener);
        }
    }

    public void createParkSelection(JComponent parkSelection) {

        gastownSteamClock.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(gastownSteamClock);

        flyoverCanada.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(flyoverCanada);

        vancouverAquarium.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(vancouverAquarium);

        rogerArena.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(rogerArena);

        vancouverArtGallery.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(vancouverArtGallery);


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
