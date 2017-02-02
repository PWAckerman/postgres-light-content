package com.tryout.controllers;

import com.tryout.services.ConfigWriter;
import com.tryout.view.DBInfoWindow;
import com.tryout.model.DBInfoWindowModel;
import com.tryout.Main;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by patrickackerman on 1/11/17.
 */

public class DBInfoWindowController implements ActionListener, PropertyChangeListener, DocumentListener{
    private DBInfoWindow dbInfoWindow;
    private DBInfoWindowModel dbInfoModel;
    private boolean flag;

    public DBInfoWindowController(DBInfoWindowModel theModel){
        dbInfoModel = theModel;
        dbInfoWindow = new DBInfoWindow(this);
        dbInfoModel.addObserver(dbInfoWindow);
        flag = false;
    }

    public boolean getFlag(){
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
//        flag = false;
        new Main(dbInfoModel.getConnectionString(), dbInfoModel.getDatabaseUserName(), dbInfoModel.getDatabasePassword());
        new ConfigWriter(dbInfoModel.getConnectionString(), dbInfoModel.getDatabaseUserName(), dbInfoModel.getDatabasePassword());
        dbInfoWindow.closeWindow();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getNewValue());
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        System.out.println("insert");
        if(!flag){
            try {
                String id = (String)e.getDocument().getProperty("id");
                String val = e.getDocument().getText(0,e.getDocument().getLength());
                filterIdTask(id, val);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        } else if(flag){

        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("remove");
        if(!flag){
            try {
                String id = (String)e.getDocument().getProperty("id");
                String val = e.getDocument().getText(0,e.getDocument().getLength());
                filterIdTask(id, val);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        } else if(flag){

        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("changed");
        try {
            String id = (String)e.getDocument().getProperty("id");
            String val = e.getDocument().getText(0,e.getDocument().getLength());
            filterIdTask(id, val);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    private void setModelIp(String ip){
        dbInfoModel.setDatabaseIp(ip);
    }

    private void setModelDbName(String name){
        dbInfoModel.setDatabaseName(name);
    }

    private void setModelDbUserName(String userName){
        dbInfoModel.setDatabaseUserName(userName);
    }

    private void setModelDbPassword(String password){
        dbInfoModel.setDatabasePassword(password);
    }

    public void toggleFlag(){
        flag = !flag;
    }


    private void filterIdTask(String str, String val){
        switch(str){
            case "ip":
                System.out.println("ip");
                setModelIp(val);
                break;
            case "name":
                System.out.println("name");
                setModelDbName(val);
                break;
            case "username":
                System.out.println("username");
                setModelDbUserName(val);
                break;
            case "password":
                System.out.println("password");
                setModelDbPassword(val);
                break;
        }
    }
}

