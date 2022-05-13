package ui.tools;

import model.Attraction;
import model.Day;
import model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AttractionsWindow implements ActionListener {
    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private Schedule schedule;
    private JComboBox daysCombo;
    private int dayNum;

    private JTextField fromTimeField;
    private JTextField toTimeField;
    private JTextField noteField;

    JComponent scheduleArea;

    private JButton addButton;

    private JTextField textField;


    private JLabel textLabel = new JLabel();

    String attractionName;

    JComponent parent;

    String picFileName;

    JComboBox daysOfTheWeek;


    public AttractionsWindow(Schedule schedule, JComponent scheduleArea, String attractionName, JComponent parent, String imageName, JComboBox daysOfTheWeek) {
        this.schedule = schedule;
        daysCombo = new JComboBox(days);
        addButton = new JButton("Add");

        this.scheduleArea = scheduleArea;

        this.attractionName = attractionName;

        this.parent = parent;

        this.picFileName = imageName;

        this.daysOfTheWeek = daysOfTheWeek;

        initializeGraphics(parent);

    }

    public void initializeGraphics(JComponent parent) {
        JPanel mainLabelsPanel = new JPanel();
        //mainLabelsPanel.setBackground(Color.WHITE);
        mainLabelsPanel.setLayout(null);
        mainLabelsPanel.setBounds(0,0, 740, 465);
        parent.add(mainLabelsPanel);

        addLabels(mainLabelsPanel);
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(Color.GRAY);
        actionPanel.setBounds(0, 470, 450, 300);
        parent.add(actionPanel);
        addAttractionButtons(actionPanel);
    }

    public void addLabels(JComponent parent) {
        JPanel titlePanel = new JPanel();
        //titlePanel.setBackground(Color.WHITE);
        titlePanel.setBounds(0, 5, 740, 45);

        JLabel label = new JLabel(attractionName);
        label.setFont(new Font(null, Font.PLAIN, 27));
        Dimension labelSize = label.getPreferredSize();
        titlePanel.add(label);


        JPanel test = new JPanel();
        //test.setBackground(Color.WHITE);
        test.setBounds(20,45,700,400);

        ImageIcon image = new ImageIcon("image\\" + picFileName);
        JLabel picLabel = new JLabel(image);
        picLabel.setMaximumSize(new Dimension(700,400));
        picLabel.setPreferredSize(new Dimension(700,400));
        test.add(picLabel);

        parent.add(test);

        parent.add(titlePanel);


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
        daysCombo.setFont(new Font(null,Font.PLAIN,22));
        daysCombo.setBounds(90,20,246,32);
        actionPanel.add(daysCombo);

    }

    public void createTextsAndFields(String label, JComponent actionPanel) {
        Font fixedTextFont = new Font(null, Font.PLAIN, 22);
        Font inputTextFont = new Font(null, Font.PLAIN, 18);

        JLabel textLabel = new JLabel(label);
        this.textLabel = textLabel;


        JTextField textField = new JTextField(22);
        this.textField = textField;
        Dimension textFieldSize = textField.getPreferredSize();
        textField.setFont(inputTextFont);

        textLabel.setFont(fixedTextFont);
        Dimension fromTimePreferredSize = textLabel.getPreferredSize();

        if (label == "From:") {
            textLabel.setBounds(20, 70, fromTimePreferredSize.width, fromTimePreferredSize.height);
            textField.setBounds(90,70,textFieldSize.width,29);

            this.fromTimeField = textField;

        } if (label == "To:") {
            textLabel.setBounds(20, 120, fromTimePreferredSize.width, fromTimePreferredSize.height);
            textField.setBounds(90,120,textFieldSize.width,29);

            this.toTimeField = textField;

        } if (label == "Note:") {
            textLabel.setBounds(20, 170, fromTimePreferredSize.width, fromTimePreferredSize.height);
            textField.setBounds(90,170,textFieldSize.width,29);
            this.noteField = textField;
        }

        actionPanel.add(textLabel);
        actionPanel.add(textField);
    }

    public void createAddButton(JComponent actionPanel) {
        addButton.setFont(new Font(null,Font.PLAIN, 18));
        addButton.setBounds(260,220, 70,35);
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
            this.dayNum = dayNum;

            Attraction attraction = new Attraction(dayNum, attractionName, fromTime, toTime);

            ArrayList<Day> localDays = schedule.getDays();
            Day day = localDays.get(dayNum);

            day.addAttractionAndNote(attraction, note);
            System.out.println(dayNum);

            JPanel subPanel = new JPanel();
            subPanel.setBounds(0,0,940,700);
            subPanel.setBackground(new java.awt.Color(197,218,221));
            scheduleArea.add(subPanel);

            daysOfTheWeek.setEditable(true);


            if (dayNum == 0) {
                daysOfTheWeek.setSelectedItem("Monday");
            } else if (dayNum == 1) {
                daysOfTheWeek.setSelectedItem("Tuesday");
            } else if (dayNum ==2) {
                daysOfTheWeek.setSelectedItem("Wednesday");
            } else if( dayNum == 3) {
                daysOfTheWeek.setSelectedItem("Thursday");
            } else if (dayNum ==4) {
                daysOfTheWeek.setSelectedItem("Friday");
            } else if( dayNum ==5) {
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
