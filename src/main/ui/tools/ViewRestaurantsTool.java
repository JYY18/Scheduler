package ui.tools;

import ui.ScheduleApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Tool to open park selection window
public class ViewRestaurantsTool extends Tool implements ActionListener {
    JComponent parent;
    JComponent scheduleArea; // to be passed to attractionsWindow

    ScheduleApp scheduleApp;
    JButton chambarRestaurant;
    JButton hawksworth;


    JButton miku;
    JButton haiDiLaoHotpot;
    JButton gringo;

    JPanel restaurantSelection;

    JPanel buttonsPanel;

    JComboBox daysOfTheWeek;


    // Constructor
    public ViewRestaurantsTool(ScheduleApp scheduleApp, JComponent parent, JComponent scheduleArea, JComboBox daysOfTheWeek) {
        super(scheduleApp, parent);
        this.parent = parent;
        this.scheduleArea = scheduleArea;

        this.scheduleApp = scheduleApp;

        chambarRestaurant = new JButton("Chambar Restaurant");
        hawksworth = new JButton("Hawksworth");
        miku = new JButton("Miku");
        haiDiLaoHotpot = new JButton("HaiDiLao Hotpot");
        gringo = new JButton("Gringo");

        ArrayList<JButton> buttonList = new ArrayList();
        buttonList.add(chambarRestaurant);
        buttonList.add(hawksworth);
        buttonList.add(miku);
        buttonList.add(haiDiLaoHotpot);
        buttonList.add(gringo);

        for (JButton b : buttonList) {
            b.setBackground(new Color(238, 238, 238));
            b.setBorder(new LineBorder(Color.BLACK, 1));
        }
        restaurantSelection = new JPanel(new GridLayout(5, 0, 0, 0));

        buttonsPanel = new JPanel();
        this.daysOfTheWeek = daysOfTheWeek;

    }


    // MODIFIES: this
    // EFFECTS: creates "View Parks" button
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Restaurants");
        button.setFont(new Font(null, Font.PLAIN, 30));
        button.setBounds(100, 446, 540, 100);
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


            restaurantSelection.setBounds(0, 0, 740, 780);

            createParkSelection(restaurantSelection);

            ActionListener parksActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.removeAll();
                    parent.revalidate();
                    parent.repaint();
                    createBackPanelAndButton("ParkList");
                    returnToMenu();
                    if (e.getSource() == chambarRestaurant) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Chambar Restaurant", parent, "chambarRestaurant.jpg", "MapChambar.jpg", daysOfTheWeek);

                    } else if (e.getSource() == hawksworth) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Hawksworth", parent, "hawksworth.jpg", "MapHawksworth.jpg", daysOfTheWeek);

                    } else if (e.getSource() == miku) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Miku", parent, "miku.jpg", "MapMiku.jpg", daysOfTheWeek);

                    } else if (e.getSource() == haiDiLaoHotpot) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "HaiDiLao Hotpot", parent, "haiDiLaoHotpot.jpg", "MapHaiDiLao.jpg", daysOfTheWeek);

                    } else if (e.getSource() == gringo) {
                        new AttractionsWindow(scheduleApp.getSchedule(), scheduleArea, "Gringo", parent, "gringo.jpg", "MapGringo.jpg", daysOfTheWeek);
                    }
                }
            };

            chambarRestaurant.addActionListener(parksActionListener);
            hawksworth.addActionListener(parksActionListener);
            miku.addActionListener(parksActionListener);
            haiDiLaoHotpot.addActionListener(parksActionListener);
            gringo.addActionListener(parksActionListener);
        }
    }

    public void createParkSelection(JComponent parkSelection) {

        chambarRestaurant.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(chambarRestaurant);

        hawksworth.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(hawksworth);

        miku.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(miku);

        haiDiLaoHotpot.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(haiDiLaoHotpot);

        gringo.setFont(new Font(null, Font.PLAIN, 30));
        parkSelection.add(gringo);


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
        back.setBackground(Color.lightGray);
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
                    createParkSelection(restaurantSelection);
                }
            }
        });

        parent.add(buttonsPanel);
    }

    public void returnToMenu() {
        JButton menuButton = new JButton("Menu");
        menuButton.setFont(new Font(null, Font.PLAIN, 18));
        menuButton.setBounds(630, 22, 90, 45);
        menuButton.setBackground(Color.lightGray);
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
