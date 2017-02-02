package com.tryout;

import javax.swing.*;
import java.awt.*;
/**
 * Created by patrickackerman on 12/14/16.
 */
public class CustomGUI {

    JButton btnOK;
    JButton btnCancel;
    JLabel lblMessage;
    JPanel pnlHolder;
    JPanel pnlTwo;
    ButtonOKListener btnOKListener;
    ButtonCancelListener btnCancelListener;
    StringListener stringListener;
    GridBagLayout gridBagLayout;
    JFrame frame;
    JList list;
    DefaultListModel model;
    GridBagConstraints constraints = new GridBagConstraints();

    private static JButton createSimpleButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true),BorderFactory.createEmptyBorder(10,10,10,10)));
        return button;
    }

    public void addList(JList list){
        System.out.println("we adding");
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        pnlTwo.add(new JScrollPane(list), constraints);
        System.out.println("we added");
    }

    public JPanel manufacture(JFrame fram){
        frame = fram;
        lblMessage = new JLabel();
        pnlHolder = new JPanel();
        list = new JList();
        model = new DefaultListModel();
        System.out.println(fram.getWidth());
        System.out.println(fram.getHeight());
        System.out.println(fram);
        pnlHolder.setSize(fram.getWidth(),fram.getHeight());
        pnlTwo = new JPanel();
        gridBagLayout = new GridBagLayout();
        btnOKListener = new ButtonOKListener();
        btnCancelListener = new ButtonCancelListener();
        stringListener = new StringListener(){
            public void textEmitted(String text){
                lblMessage.setText(text);
            }
        };
        btnOKListener.setStringListener(stringListener);
        btnCancelListener.setStringListener(stringListener);
        btnOK = this.createSimpleButton("OK");
        btnOK.addActionListener(btnOKListener);
        btnCancel = this.createSimpleButton("Cancel");
        btnCancel.addActionListener(btnCancelListener);
        pnlTwo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true),BorderFactory.createEmptyBorder(10,10,10,10)));
        pnlHolder.setBackground(new Color(68, 70, 73));
        pnlHolder.setLayout(gridBagLayout);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.VERTICAL;
        Dimension dim = new Dimension(350,300);
        pnlTwo.setPreferredSize(dim);
        pnlHolder.add(pnlTwo, constraints);
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        model.add(0,"You");
        model.add(1,"Me");
        model.add(2,"Everyone We Know");
        list.setModel(model);
//        pnlHolder.add(btnOK, constraints);
//        pnlHolder.add(list, constraints);
        constraints.gridy = 2;
        pnlHolder.add(btnCancel, constraints);
        pnlHolder.add(lblMessage);
        return pnlHolder;
    }
}
