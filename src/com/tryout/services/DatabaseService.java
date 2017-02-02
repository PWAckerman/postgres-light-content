package com.tryout.services;

import com.tryout.SQLStatement;
import com.tryout.db.PostGresConnection;
import com.tryout.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by patrickackerman on 1/6/17.
 */
public class DatabaseService implements Service {
    PostGresConnection connection;
    ResultSetMetaData meta;
    ResultSet res;

    public DatabaseService(String url, String uname, String password){
        new PostGresConnection(url, uname, password);
    }

    public ResultSet retrieveDatabaseMetadata(){
        try (Connection conn = connection.connect()) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = stmt.executeQuery(
                    "SELECT * FROM information_schema.tables WHERE table_schema \n" +
                            "NOT LIKE 'information_schema' \n" +
                            "AND table_schema NOT LIKE 'public' \n" +
                            "AND table_schema NOT LIKE 'pg_catalog';");
            meta = res.getMetaData();
            System.out.println("we out.");

        } catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return res;
        }
    }

    public Table retrieveTable(String sch, String tab) throws SQLException, ClassNotFoundException {
        return new Table(sch, tab, connection.connect());
    }

    public void saveRow(List<String> columns, List<String> values, String tableName){
        HashMap<String, String> valueMap = new HashMap<>();
        IntStream.range(0, columns.size())
                .forEach((inx)->{
                    valueMap.put(columns.get(inx), values.get(inx));
                });
        SQLStatement statement = new SQLStatement(valueMap, tableName);
    }

}

//    ArrayList<Table> tables = new ArrayList<>();
//    HashMap<String, ArrayList<Table>> SchemaHash = new HashMap<>();
//            res.first();
//                    while(res.next()){
//                    tables.add(new Table(res.getString("table_schema"), res.getString("table_name"), conn));
//                    if(SchemaHash.containsKey(res.getString("table_schema"))){
//                    SchemaHash.get(res.getString("table_schema")).add(new Table(res.getString("table_schema"), res.getString("table_name"), conn));
//                    } else {
//                    SchemaHash.put(res.getString("table_schema"), new ArrayList<Table>());
//        SchemaHash.get(res.getString("table_schema")).add(new Table(res.getString("table_schema"), res.getString("table_name"), conn));
//        }
//        res.next();
//        }
