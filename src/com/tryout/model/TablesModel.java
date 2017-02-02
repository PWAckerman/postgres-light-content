package com.tryout.model;

import com.tryout.Table;
import com.tryout.services.DatabaseService;
import com.tryout.view.SchemaTree;
import com.tryout.services.ServiceRegistry;

import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;

/**
 * Created by patrickackerman on 1/6/17.
 */
public class TablesModel {
    private HashMap<String, ArrayList<String>> SchemaHash = new HashMap<>();
    private SchemaTree schemaTree;
//    SchemaTreeModel schemaTreeModel = new SchemaTreeModel();

    public TablesModel(){

    }

    public HashMap<String,ArrayList<String>> assemble(ResultSet res, DatabaseService dbService){
        try{
            res.first();
            while(res.next()){
                if(SchemaHash.containsKey(res.getString("table_schema"))){
                    SchemaHash.get(res.getString("table_schema")).add(res.getString("table_name"));
                } else {
                    SchemaHash.put(res.getString("table_schema"), new ArrayList<>());
                    SchemaHash.get(res.getString("table_schema")).add(res.getString("table_name"));
                }
                res.next();
            }
            return SchemaHash;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SchemaTree getSchemaTree(){
        if(schemaTree == null){
            schemaTree = new SchemaTree(new DefaultMutableTreeNode("DB"));
            for(String key : SchemaHash.keySet()){
                schemaTree.appendSchema(key);
                for (String str : SchemaHash.get(key)){
                    schemaTree.appendTable(str);
                }
            }
        }
        return schemaTree;
    }
}
