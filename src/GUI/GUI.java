package src.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUI {
    private final JDialog mainDialog;
    private final JTextField monsterNameField;
    private final JTextField foodNameField;
    private final JTextField pickUpItemsField;
    private final JComboBox<AttackStyle> attackStyleJComboBox;

    private boolean started;

    private enum AttackStyle {

    }

    public GUI() {
        mainDialog = new JDialog();
        mainDialog.setTitle("Zeal Combat");
        mainDialog.setModal(true);
        mainDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        mainDialog.setLocationRelativeTo(null);
        mainDialog.setLocation(mainDialog.getX(),mainDialog.getY() - 150);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainDialog.getContentPane().add(mainPanel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(10,10,10,10));



        monsterNameField = new JTextField(20);
        inputPanel.add(monsterNameField);

        TextPrompt monsterTP = new TextPrompt("Monster Name (Case Sensitive)", monsterNameField);
        monsterTP.setForeground(Color.gray);
        monsterTP.changeAlpha(.75f);
        monsterTP.changeStyle(Font.ITALIC);



        foodNameField = new JTextField(20);
        inputPanel.add(foodNameField);

        TextPrompt foodTP = new TextPrompt("Food Name (Case Sensitive)", foodNameField);
        foodTP.setForeground(Color.gray);
        foodTP.changeAlpha(.75f);
        foodTP.changeStyle(Font.ITALIC);



        pickUpItemsField = new JTextField(20);
        inputPanel.add(pickUpItemsField);

        TextPrompt itemTP = new TextPrompt("Pick up (Ex: Raw beef,Bones,Cowhide)", pickUpItemsField);
        itemTP.setForeground(Color.gray);
        itemTP.changeAlpha(.75f);
        itemTP.changeStyle(Font.ITALIC);



        attackStyleJComboBox = new JComboBox<>(AttackStyle.values());
        inputPanel.add(attackStyleJComboBox);



        mainPanel.add(inputPanel);



        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            started = true;
            close();
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(startButton);



        mainDialog.pack();
    }

    public boolean isStarted() {
        return started;
    }

    public String getMonsterName(){
        return monsterNameField.getText();
    }

    public String getFoodName(){
        return foodNameField.getText();
    }

    public String getPickupItems(){
        return pickUpItemsField.getText();
    }

    public void open(){
        mainDialog.setVisible(true);
    }

    public void close(){
        mainDialog.setVisible(false);
        mainDialog.dispose();
    }
}
