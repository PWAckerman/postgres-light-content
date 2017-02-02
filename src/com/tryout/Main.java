package com.tryout;

import com.tryout.controllers.EventBus;
import com.tryout.controllers.MainGUIWindowController;
import com.tryout.db.PostGresConnection;
import com.tryout.services.DatabaseService;
import com.tryout.services.ServiceRegistry;
import com.tryout.view.MainGUIWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;



public class Main {
//    static Logger logger = LogManager.getLogManager().getLogger("com.tryout");
    private ServiceRegistry serviceRegistry;
    private DatabaseService databaseService;
    private EventBus eventBus;
    private CustomGUI custGUI;
    static private Main thread;
    static private DBInfoWindowInjector thread2;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//                thread = new Main();
                thread2 = new DBInfoWindowInjector();
            }
        );

    }


    public Main(String url, String uname, String pword) {
        serviceRegistry = new ServiceRegistry();
        databaseService = new DatabaseService(url, uname, pword);
        eventBus = new EventBus();
        serviceRegistry.registerService(databaseService);
        serviceRegistry.registerService(eventBus);
        UIManager.put("TabbedPane.contentAreaColor ",Color.GREEN);
        UIManager.put("TabbedPane.selected",Color.GREEN);
        UIManager.put("TabbedPane.background",Color.GREEN);
        UIManager.put("TabbedPane.shadow",Color.GREEN);
        MainGUIWindowController mainGUIWindowController = new MainGUIWindowController(serviceRegistry);
    }

    public void setList(SchemaList lst){
        System.out.println("we listing");
        custGUI.addList(lst.getSchemaList());
    }

}
