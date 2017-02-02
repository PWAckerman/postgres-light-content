package com.tryout;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Created by patrickackerman on 1/5/17.
 */
public class Table {
    private SQLStatement sqlStatement;
    private Statement stmt;
    private String schema;
    private String tableName;
    private Connection connection;
    private ResultSet tableContents;
    private int recordCount;
    private String primaryKey;
    private String primaryKeyType;
    private int lowerLimit = 0;
    private int upperLimit = 500;
    private int interval = 500;
    private Boolean bigData = false;
    private Boolean empty = false;

    public int getRecordCount() {
        return recordCount;
    }

    public String getTableName() {
        return tableName;
    }


    public boolean equals(String str){
        if(tableName == str){
            return true;
        } else {
            return false;
        }

    }

    public Table(String sch, String tblName, Connection conn) throws SQLException,ClassNotFoundException{
        System.out.println("MAKE A TABLE");
        schema = sch;
        tableName = tblName;
        connection = conn;
            try {
                retrievePrimaryKey();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                retrieveRecordCount();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                if(recordCount > 1000){
                    bigData = true;
                    sqlStatement = new SQLStatement(schema, tableName, interval, lowerLimit);
                    System.out.println("SQLSTATEMENTBIG: " + sqlStatement.getStatement());
                } else {
                    sqlStatement = new SQLStatement(schema, tableName);
                    System.out.println("SQLSTATEMENTREG: " + sqlStatement.getStatement());
                }
                if(recordCount > 0){
                    tableContents = stmt.executeQuery(sqlStatement.getStatement());
                } else {
                    tableContents = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void load(){
//        if(tableContents == null){
////            try {
////                stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
////                if(recordCount > 1000){
////                    bigData = true;
////                    sqlStatement = new SQLStatement(schema, tableName, interval, lowerLimit);
////                    System.out.println("SQLSTATEMENTBIG: " + sqlStatement.getStatement());
////                } else {
////                    sqlStatement = new SQLStatement(schema, tableName);
////                    System.out.println("SQLSTATEMENTREG: " + sqlStatement.getStatement());
////                }
////                if(recordCount > 0){
////                    tableContents = stmt.executeQuery(sqlStatement.getStatement());
////                } else {
////                    tableContents = null;
////                }
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
//        } else {
//
//        }
    }

    private void retrievePrimaryKey() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet primaryKeyQuery = stmt.executeQuery(new PKSQLStatement(tableName).getStatement());
        if (!primaryKeyQuery.isBeforeFirst() ) {
            System.out.println("No data");
            primaryKey = null;
            primaryKeyType = null;
        } else {
            primaryKeyQuery.first();
            primaryKey = primaryKeyQuery.getString("column_name");
            primaryKeyType = primaryKeyQuery.getString("data_type");
        }
    }

    public ResultSet getTableContents(){
        return tableContents;
    }

    private void retrieveRecordCount() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        if(primaryKey == null){
            recordCount = 0;
        } else {
            ResultSet countQuery = stmt.executeQuery(new CountSQLStatement(primaryKey, schema, tableName).getStatement());
            if (!countQuery.isBeforeFirst() ) {
                System.out.println("No data.");
                recordCount = 0;
            } else {
                countQuery.first();
                recordCount = Integer.parseInt(countQuery.getString("count"));
            }
        }
    }

    public void getNextResultInterval() throws SQLException {
        if(isBigData()){
            if(upperLimit < recordCount - interval){
                lowerLimit = lowerLimit + interval;
                upperLimit = upperLimit + interval;
            } else {
                upperLimit = recordCount - 1;
                lowerLimit = recordCount - interval;
            }
            sqlStatement = new SQLStatement(schema, tableName, lowerLimit, upperLimit);
            ResultSet tableContents = stmt.executeQuery(sqlStatement.getStatement());
        } else {

        }
    }

    public void getPreviousResultInterval() throws SQLException {
        if(isBigData()){
            if(lowerLimit > interval){
                lowerLimit = lowerLimit - interval;
                upperLimit = upperLimit - interval;
            } else {
                lowerLimit = 0;
                upperLimit = interval;
            }
            sqlStatement = new SQLStatement(schema, tableName, lowerLimit, upperLimit);
            ResultSet tableContents = stmt.executeQuery(sqlStatement.getStatement());
        } else {

        }
    }


    public Boolean isBigData(){
        return bigData;
    }

}
