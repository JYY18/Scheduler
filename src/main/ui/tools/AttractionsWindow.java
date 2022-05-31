package ui.tools;

import model.Attraction;
import model.Day;
import model.Schedule;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AttractionsWindow implements ActionListener {
    private final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private final Schedule schedule;
    private final JComboBox daysCombo;

    private JTextField fromTimeField;
    private JTextField toTimeField;
    private JTextField noteField;

    JComponent scheduleArea;

    private final JButton addButton;


    String attractionName;

    JComponent parent;

    String picFileName;
    String mapName;

    JComboBox daysOfTheWeek;


    public AttractionsWindow(Schedule schedule, JComponent scheduleArea, String attractionName, JComponent parent, String imageName, String mapName, JComboBox daysOfTheWeek) {
        this.schedule = schedule;
        daysCombo = new JComboBox(days);
        addButton = new JButton("Add");
        addButton.setBackground(Color.lightGray);

        this.scheduleArea = scheduleArea;

        this.attractionName = attractionName;

        this.parent = parent;

        this.picFileName = imageName;
        this.mapName = mapName;

        this.daysOfTheWeek = daysOfTheWeek;

        initializeGraphics(parent);

    }

    public void initializeGraphics(JComponent parent) {
        JPanel mainLabelsPanel = new JPanel();
        mainLabelsPanel.setLayout(null);
        mainLabelsPanel.setBounds(0, 0, 740, 465);
        parent.add(mainLabelsPanel);

        addLabels(mainLabelsPanel);
        JPanel actionPanel = new JPanel();
        actionPanel.setBounds(20, 480, 390, 280);
        actionPanel.setBorder(new LineBorder(Color.BLACK, 2));

        JPanel mapPanel = new JPanel();
        mapPanel.setBounds(440, 480, 280, 280);
        JLabel mapLabel = new JLabel(new ImageIcon("image\\" + mapName));
        mapPanel.add(mapLabel);

        parent.add(mapPanel);
        parent.add(actionPanel);
        addAttractionButtons(actionPanel);
    }

    public void addLabels(JComponent parent) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(1, 5, 735, 45);

        JLabel label = new JLabel(attractionName);
        label.setFont(new Font(null, Font.PLAIN, 27));
        titlePanel.add(label);

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(20, 45, 700, 400);

        ImageIcon image = new ImageIcon("image\\" + picFileName);
        JLabel picLabel = new JLabel(image);
        picLabel.setMaximumSize(new Dimension(700, 400));
        picLabel.setPreferredSize(new Dimension(700, 400));
        imagePanel.add(picLabel);

        parent.add(imagePanel);

        parent.add(titlePanel);
        parent.setBorder(new LineBorder(Color.BLACK, 1));

    }


    public void addAttractionButtons(JComponent actionPanel) {
        actionPanel.setLayout(null);

        createDayComboBox(actionPanel);

        createTextsAndFields("From:", actionPanel);
        createTextsAndFields("To:", actionPanel);
        createTextsAndFields("Note:", actionPanel);

        createAddButton(actionPanel);
    }

    public void createDayComboBox(JComponent actionPanel) {
        daysCombo.setFont(new Font(null, Font.PLAIN, 22));
        daysCombo.setBounds(90, 20, 246, 32);
        actionPanel.add(daysCombo);

    }

    public void createTextsAndFields(String label, JComponent actionPanel) {
        Font fixedTextFont = new Font(null, Font.PLAIN, 22);
        Font inputTextFont = new Font(null, Font.PLAIN, 18);

        JLabel textLabel = new JLabel(label);


        JTextField textField = new JTextField(22);
        Dimension textFieldSize = textField.getPreferredSize();
        textField.setFont(inputTextFont);

        textLabel.setFont(fixedTextFont);
        Dimension fromTimePreferredSize = textLabel.getPreferredSize();

        if (label == "From:") {
            textLabel.setBounds(20, 70, fromTimePreferredSize.width, fromTimePreferredSize.height);
            textField.setBounds(90, 70, textFieldSize.width, 29);

            this.fromTimeField = textField;

        }
        if (label == "To:") {
            textLabel.setBounds(20, 120, fromTimePreferredSize.width, fromTimePreferredSize.height);
            textField.setBounds(90, 120, textFieldSize.width, 29);

            this.toTimeField = textField;

        }
        if (label == "Note:") {
            textLabel.setBounds(20, 170, fromTimePreferredSize.width, fromTimePreferredSize.height);
            textField.setBounds(90, 170, textFieldSize.width, 29);
            this.noteField = textField;
        }

        actionPanel.add(textLabel);
        actionPanel.add(textField);
    }

    public void createAddButton(JComponent actionPanel) {
        addButton.setFont(new Font(null, Font.PLAIN, 18));
        addButton.setBounds(260, 220, 70, 35);
        actionPanel.add(addButton);
        addButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String fromTime = fromTimeField.getText();
            String toTime = toTimeField.getText();
            String note = noteField.getText();

            int dayNum = daysCombo.getSelectedIndex();


            ArrayList<Day> localDays = schedule.getDays();
            Day day = localDays.get(dayNum);


            Attraction attraction = new Attraction(dayNum, attractionName, fromTime, toTime);

            day.addAttractionAndNote(attraction, note);

            JPanel subPanel = new JPanel();
            subPanel.setBounds(0, 0, 940, 700);
            subPanel.setBackground(new java.awt.Color(197, 218, 221));
            scheduleArea.add(subPanel);

            daysOfTheWeek.setEditable(true);


            if (dayNum == 0) {
                daysOfTheWeek.setSelectedItem("Monday");
            } else if (dayNum == 1) {
                daysOfTheWeek.setSelectedItem("Tuesday");
            } else if (dayNum == 2) {
                daysOfTheWeek.setSelectedItem("Wednesday");
            } else if (dayNum == 3) {
                daysOfTheWeek.setSelectedItem("Thursday");
            } else if (dayNum == 4) {
                daysOfTheWeek.setSelectedItem("Friday");
            } else if (dayNum == 5) {
                daysOfTheWeek.setSelectedItem("Saturday");
            } else if (dayNum == 6) {
                daysOfTheWeek.setSelectedItem("Sunday");
            }

            daysOfTheWeek.setEditable(false);
            daysOfTheWeek.revalidate();
            daysOfTheWeek.repaint();

            new ScheduleListWindow(schedule, dayNum, subPanel);
        }
    }
}
