package com.tryout.view;

import com.tryout.Table;
import com.tryout.controllers.*;
import com.tryout.services.Service;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by patrickackerman on 1/7/17.
 */
public class MainGUIWindow extends JFrame {

    private SchemaScrollPane schemaScrollPane;
    private TableTabbedPane tableTabbedPane;
    private TableScrollPane tableScrollPane;
    private EventBus eventBus;
    private MainGUIWindowController control;
    private JSplitPane split;
    private ArrayList<TableController> childControllers = new ArrayList();


    public MainGUIWindow(MainGUIWindowController cont){
        super();
        this.control = cont;
        this.setTitle("Postgres Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new GridLayout());
        this.setBackground(new Color(68, 70, 73));
        this.setSize(1000, 700);
        SchemaTree emptyTree = new SchemaTree(new DefaultMutableTreeNode("DB"));
        schemaScrollPane = new SchemaScrollPane(emptyTree);
        schemaScrollPane.getVerticalScrollBar().setOpaque(false);
        tableTabbedPane = new TableTabbedPane();
//        tableScrollPane = new TableScrollPane();
//        tableTabbedPane.add(tableScrollPane);
        this.getContentPane().add(schemaScrollPane, BorderLayout.WEST);
        this.getContentPane().add(tableTabbedPane, BorderLayout.EAST);
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("Table.focusCellHighlightBorder", new Color(66, 157, 161));
    }

    public void inject(Service service){
        eventBus = (EventBus)service;
    }



    public void addComponent(SchemaTree tree){
        MainGUIWindow self = this;
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e){
                String nodeType = e.getPath().toString();
                if(nodeType.split(" ").length > 2){
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                    DataCarrier dc = new DataCarrier();
                    dc.put("schemaName", nodeType.split(", ")[1]);
                    dc.put("tableName", nodeType.split(", ")[2].replace("]",""));
                    Table table = (Table)eventBus.runEvent("tblEvent", dc);
                    table.load();
                    TableController tblController = new TableController(table.getTableContents(), table, control);
                    childControllers.add(tblController);
//                    try(TableFactory tableDisplay = new TableFactory()){
//                        table.load();
//                        tableDisplay.setModel(table.getTableContents());
////                        tableDisplay.getTableModel().addTableModelListener();
//                        JTable tabledTable = tableDisplay.getTable();
//                        tableScrollPane = new TableScrollPane();
//                        tableScrollPane.getVerticalScrollBar().setOpaque(false);
//                        tableScrollPane.getVerticalScrollBar().setBackground(new Color(0x103443));
//                        tableScrollPane.getVerticalScrollBar().setForeground(new Color(0x144454));
//                        tableScrollPane.getViewport().add(tabledTable);
//                        tabledTable.setFillsViewportHeight(true);
//                        tableTabbedPane.setTabPlacement(JTabbedPane.LEFT);
//                        tableTabbedPane.addTab(table.getTableName(),createImageIcon("../images/griglia-architetto-fran-01r.png","SHEET"),tableScrollPane);
//                        split.setRightComponent(tableTabbedPane);
//                    } catch(SQLException e2){
//                        System.out.println(e2.getSQLState());
//                    } catch (Exception e1) {
//
//                    }
                }
            }
        });
        this.getContentPane().removeAll();
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setBackground(new Color(68, 70, 73));
        split.setForeground(new Color(187,187,187));
        schemaScrollPane = new SchemaScrollPane(tree);
        schemaScrollPane.setMinimumSize(new Dimension(200,0));
        split.setLeftComponent(schemaScrollPane);
//        tableTabbedPane = new TableTabbedPane();

//        tableTabbedPane.add(tableScrollPane);
//        this.getContentPane().add(schemaScrollPane, BorderLayout.WEST);
        tableScrollPane = new TableScrollPane();
        split.setRightComponent(tableScrollPane);

        this.getContentPane().add(split, BorderLayout.CENTER);
    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void addTableTab(JTable table, String name){
        System.out.println("ADD TABLE: " + table.toString());
        tableScrollPane = new TableScrollPane();
        tableScrollPane.getViewport().add(table);
        table.setFillsViewportHeight(true);
        tableTabbedPane.addTab(name,createImageIcon("../images/griglia-architetto-fran-01r.png","SHEET"),tableScrollPane);
        tableTabbedPane.addMouseListener(control);
        split.setRightComponent(tableTabbedPane);
    }
}
