package com.tryout.controllers;

import com.tryout.Table;
import com.tryout.model.TablesModel;
import com.tryout.services.DatabaseService;

import java.sql.SQLException;

/**
 * Created by patrickackerman on 1/8/17.
 */
public class TableEvent implements Event {

    DatabaseService dbService;

    public TableEvent(DatabaseService db){
        dbService = db;
    }
    public Object runEvent(DataCarrier dc){
        System.out.println("SCHEMANAME:" + dc.get("schemaName").toString());
        System.out.println("TABLENAME" + dc.get("tableName").toString());

        try{
            Table tbl = dbService.retrieveTable((String)dc.get("schemaName"),(String)dc.get("tableName"));
            return tbl;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }

    }
}
