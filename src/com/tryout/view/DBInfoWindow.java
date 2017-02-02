package com.tryout.view;


import com.tryout.controllers.DBInfoWindowController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Created by patrickackerman on 1/11/17.
 */
public class DBInfoWindow implements Observer{
    private DBInfoWindowController dbInfoWindowController;
    private JFrame window;
    private JPanel panel;
    private JTextField input1;
    private JTextField input2;
    private JTextField input3;
    private JPasswordField input4;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton button1;
    private String ip;
    private String name;
    private String username;
    private String password;

    public DBInfoWindow(DBInfoWindowController controller){
        dbInfoWindowController = controller;
        ip = "";
        name = "";
        username = "";
        password = "";
        panel = new JPanel();
        input1 = new JTextField(ip);
        input2 = new JTextField(name);
        input3 = new JTextField(username);
        input4 = new JPasswordField(password);
        label1 = new JLabel("Database IP");
        label2 = new JLabel("Database Name");
        label3 = new JLabel("Username");
        label4 = new JLabel("Password");
        button1 = new JButton("Connect");
        input1.addActionListener(dbInfoWindowController);
        input1.getDocument().addDocumentListener(dbInfoWindowController);
        input2.getDocument().addDocumentListener(dbInfoWindowController);
        input3.getDocument().addDocumentListener(dbInfoWindowController);
        input4.getDocument().addDocumentListener(dbInfoWindowController);
        input1.getDocument().putProperty("id", "ip");
        input2.getDocument().putProperty("id", "name");
        input3.getDocument().putProperty("id", "username");
        input4.getDocument().putProperty("id", "password");
        input4.setEchoChar('*');
        button1.addActionListener(dbInfoWindowController);
        input1.setColumns(40);
        input2.setColumns(40);
        input3.setColumns(40);
        input4.setColumns(40);
        label1.setForeground(new Color(0x90A4A8));
        label2.setForeground(new Color(0x90A4A8));
        label3.setForeground(new Color(0x90A4A8));
        label4.setForeground(new Color(0x90A4A8));
        label1.setLabelFor(input1);
        label2.setLabelFor(input2);
        label3.setLabelFor(input3);
        label4.setLabelFor(input4);
        panel.add(label1);
        panel.add(input1);
        panel.add(label2);
        panel.add(input2);
        panel.add(label3);
        panel.add(input3);
        panel.add(label4);
        panel.add(input4);
        panel.add(button1);
        panel.setBackground(new Color(68, 70, 73));
        window = new JFrame();
        window.setTitle("Setup Window");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
        window.setLayout(new GridLayout());
        window.setBackground(new Color(68, 70, 73));
        window.getContentPane().add(panel);
        window.setSize(500, 500);
        window.setResizable(false);
    }

    @Override
    public void update(Observable o, Object arg) {
            updateAllValues((HashMap<String,String>)arg);
    }

    public void closeWindow(){
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    protected void updateAllValues(HashMap<String, String> valueMap){
        Set keys = valueMap.keySet();
        SwingUtilities.invokeLater(()->{
            System.out.println("setting value");
            for(Object key : keys){
                String value = valueMap.get((String)key);
                System.out.println(key + ":" + value);
                switch((String)key){
                    case "ip":
                        input1.getDocument().putProperty("value",value);
                        dbInfoWindowController.toggleFlag();
                        ip = value;
                        dbInfoWindowController.toggleFlag();
                        break;
                    case "dbname":
                        input2.getDocument().putProperty("value",value);
                        dbInfoWindowController.toggleFlag();
                        name = value;
                        dbInfoWindowController.toggleFlag();
                        break;
                    case "username":
                        input3.getDocument().putProperty("value",value);
                        dbInfoWindowController.toggleFlag();
                        username = value;
                        dbInfoWindowController.toggleFlag();
                        break;
                    case "password":
                        input4.getDocument().putProperty("value",value);
                        dbInfoWindowController.toggleFlag();
                        password = value;
                        dbInfoWindowController.toggleFlag();
                        break;
                }
            }
        });
    }

}
