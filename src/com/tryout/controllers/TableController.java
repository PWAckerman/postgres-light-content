package com.tryout.controllers;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import com.tryout.Table;
import com.tryout.view.TableFactory;
import com.tryout.view.TableScrollPane;
import com.tryout.view.TableTable;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by patrickackerman on 1/13/17.
 */
public class TableController implements TableModelListener {

    TableScrollPane tblView;
    MainGUIWindowController parent;
    TableFactory factory;
    DefaultTableModel model;
    Table localTable;

    public TableController(ResultSet res, Table tbl, MainGUIWindowController parentController){
        factory = new TableFactory();
        parent = parentController;
        localTable = tbl;
        try{
            factory.setModel(res);
            factory.getTable(this);
            model = factory.getTableModel();
            model.addTableModelListener(this);
            System.out.println(model.getTableModelListeners().toString());
            addTableToParent();
        } catch (SQLException e){
            System.err.println(e.getSQLState());
        }
    }

    public void addTableToParent(){
        JTable table = new TableTable(factory.getTableModel());
        parent.addTable(table, localTable.getTableName());
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        DefaultTableModel model = (DefaultTableModel)e.getSource();
        ArrayList<String> columnNames = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        IntStream.range(0, model.getColumnCount() - 1)
                .forEach((inx)->{
                    columnNames.add(model.getColumnName(inx));
                });
        IntStream.range(0, model.getColumnCount() - 1)
                .forEach((inx)->{
                    values.add((String)model.getValueAt(e.getFirstRow(), inx));
                });
        parent.updateTable(columnNames, values, localTable.getTableName());
    }
}
