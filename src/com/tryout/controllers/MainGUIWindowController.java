package com.tryout.controllers;

import com.tryout.Table;
import com.tryout.model.TablesModel;
import com.tryout.services.DatabaseService;
import com.tryout.services.Service;
import com.tryout.services.ServiceRegistry;
import com.tryout.view.MainGUIWindow;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by patrickackerman on 1/8/17.
 */
public class MainGUIWindowController implements TableModelListener, MouseListener{
    private ServiceRegistry serviceRegistry;
    private ResultSet metadata;
    private MainGUIWindow window;
    private DatabaseService dbService;

    public MainGUIWindowController (ServiceRegistry sRegistry){
        TablesModel tbModel;
        serviceRegistry = sRegistry;
        TableEvent tbEvent;
        dbService = (DatabaseService)serviceRegistry.getService("DatabaseService");
        EventBus eventBus = (EventBus)serviceRegistry.getService("EventBus");
        window = new MainGUIWindow(this);
        try(ResultSet metadata = dbService.retrieveDatabaseMetadata()){
            tbModel = new TablesModel();
            tbModel.assemble(metadata, dbService);
            tbEvent = new TableEvent(dbService);
            eventBus.setEvent("tblEvent", tbEvent);
            window.addComponent(tbModel.getSchemaTree());
            window.inject(eventBus);
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
//            System.out.println("Got that metadata");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

//    public void retrieveObject(Object obj){
//        tblList.add((Table)obj);
//        System.out.println(tblList.size());
//    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("TABLEMODELEVENT");
        System.out.println(e.toString());
        System.out.println(e.getColumn());
        System.out.println(e.getFirstRow());
    }

    public void addTable(JTable tbl, String name){
        System.out.println("ADD TABLE: " + tbl.getModel().getRowCount());
        window.addTableTab(tbl, name);
    }

    public void updateTable(List<String> columns, List<String> values, String tableName){
        dbService.saveRow(columns, values, tableName);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println(e);
    }
}
