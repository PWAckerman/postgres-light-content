package com.tryout.view;

import com.tryout.controllers.TableController;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by patrickackerman on 1/7/17.
 */
public class TableFactory implements AutoCloseable{
    private Vector<String> columnHeaders = new Vector<String>();
    private Vector<Vector<String>> rows = new Vector<Vector<String>>();
    DefaultTableModel tableModel;
    TableController tblController;
    JTable table;

    public TableFactory() {

    }

    public Vector<String> getColumnHeaders() {
        return columnHeaders;
    }

    public void setColumnHeaders(Vector<String> columnHeaders) {
        this.columnHeaders = columnHeaders;
    }

    public Vector<Vector<String>> getRows() {
        return rows;
    }

    public void setRows(Vector<Vector<String>> rows) {
        this.rows = rows;
    }

    public void setModel (ResultSet resultS) throws SQLException{

        ResultSetMetaData resultsMeta = resultS.getMetaData();
        Vector<String> types = new Vector<>();
        System.out.println("META COLUMN COUNT:" + resultsMeta.getColumnCount());

            for(int i = 1; i <= resultsMeta.getColumnCount(); i++){
                System.out.println("columnName: " + resultsMeta.getColumnName(i));
                columnHeaders.add(resultsMeta.getColumnName(i));
                types.add(resultsMeta.getColumnTypeName(i));
                System.out.println(resultsMeta.getColumnType(i));
            }
            while(resultS.next()){
                Vector<String> row = new Vector<String>();
                for(int i = 1; i <= resultsMeta.getColumnCount(); i++) {
                    System.out.println(resultS.getString(i));
                    row.addElement(resultS.getString(i));
                }
                rows.add(row);
            }
    }

    public JTable getTable(TableController tbl){
        tblController = tbl;
        if(table == null){
            getTableModel();
//            tableModel.addTableModelListener(tblController);
            table = new JTable(tableModel);
            table.setBackground(new Color(0x103443));
            table.setForeground(new Color(0x90A4A8));
            table.setGridColor(new Color(0x144454));
            table.setFont(new Font("SansSerif", Font.BOLD, 15));
            table.setRowHeight(table.getRowHeight() + 15);
            table.setSelectionBackground(new Color(0x144454));
            table.setSelectionForeground(new Color(0x90A4A8));
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setOpaque(true);
            renderer.setBackground(new Color(0x144454));
            renderer.setForeground(new Color(0x90A4A8));
            renderer.setFocusable(false);
            table.getTableHeader().setDefaultRenderer(renderer);
            table.getTableHeader().setReorderingAllowed(false);
            table.setBorder(BorderFactory.createEmptyBorder());
            return table;
        } else {
            return table;
        }
    }

    public JTable getTable(){
        return table;
    }

    public DefaultTableModel getTableModel(){
        DefaultTableModel table;
        if(tableModel == null){
            System.out.println("ROWSIZE:" + rows.size());
            System.out.println("COLUMNSIZE:" + columnHeaders.size());
            table = new DefaultTableModel(rows, columnHeaders);
//            table.addTableModelListener(tblController);
            tableModel = table;

        } else {
            System.out.println("NOT NULL");
            table = tableModel;
        }
        return table;
    }

    @Override
    public void close() throws Exception {

    }
}
