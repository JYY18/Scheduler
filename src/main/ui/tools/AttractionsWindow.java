package ui.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttractionsWindow implements ActionListener {
    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private JComboBox daysCombo;
    private String fromTime;
    private String toTime;
    private String note;
    private int day;

    private JButton addButton;

    private JTextField textField;


    private JLabel textLabel = new JLabel();

    String attractionName;

    JComponent parent;

    String picFileName;


    // TODO need more fields
    public AttractionsWindow(String attractionName, JComponent parent, String imageName) {
        daysCombo = new JComboBox(days);
        addButton = new JButton("Add");

        this.attractionName = attractionName;

        this.parent = parent;

        this.picFileName = imageName;

        initializeGraphics(parent);

    }

    public void initializeGraphics(JComponent parent) {

        addLabels(parent);
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(Color.GRAY);
        actionPanel.setBounds(0, 470, 450, 300);
        parent.add(actionPanel);
        addAttractionButtons(actionPanel);
    }

    public void addLabels(JComponent parent) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBounds(0, 0, 740, 460);


        JLabel label = new JLabel(attractionName);
        label.setFont(new Font(null, Font.PLAIN, 27));

        titlePanel.add(label);

        JLabel picLabel = new JLabel(new ImageIcon("image\\" + picFileName));
        picLabel.setPreferredSize(new Dimension(650, 350));
        titlePanel.add(picLabel);


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

        int day = daysCombo.getSelectedIndex() + 1;
        this.day = day;
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

            String fromTime = textLabel.getText();
            this.fromTime = fromTime;

        } if (label == "To:") {
            textLabel.setBounds(20, 120, fromTimePreferredSize.width, fromTimePreferredSize.height);
            textField.setBounds(90,120,textFieldSize.width,29);

            String toTime = textLabel.getText();
            this.toTime = toTime;
        } if (label == "Note:") {
            textLabel.setBounds(20, 170, fromTimePreferredSize.width, fromTimePreferredSize.height);
            textField.setBounds(90,170,textFieldSize.width,29);

            String note = textLabel.getText();
            this.note = note;
        }

        actionPanel.add(textLabel);
        actionPanel.add(textField);
    }

    public void createAddButton(JComponent actionPanel) {
        addButton.setFont(new Font(null,Font.PLAIN, 18));
        addButton.setBounds(260,220, 70,35);
        actionPanel.add(addButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
