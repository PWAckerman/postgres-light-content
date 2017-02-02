package com.tryout;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by patrickackerman on 1/5/17.
 */
public class SQLStatement {
    StringBuffer sqlStatement;
    public SQLStatement(){
        sqlStatement = new StringBuffer("SELECT * FROM \"");
        sqlStatement.append("information_schema.table_constraints");
        sqlStatement.append("\".");
        sqlStatement.append(";");
        System.out.println(sqlStatement);
    }

    public SQLStatement(String sch, String tableName){
        sqlStatement = new StringBuffer("SELECT * FROM \"");
        sqlStatement.append(sch);
        sqlStatement.append("\".");
        sqlStatement.append(tableName);
        sqlStatement.append(";");
        System.out.println(sqlStatement);
    }

    public SQLStatement(String sch, String tableName, int interval, int startLimit){
        sqlStatement = new StringBuffer("SELECT * FROM \"");
        sqlStatement.append(sch);
        sqlStatement.append("\".");
        sqlStatement.append(tableName);
        sqlStatement.append(" LIMIT ");
        sqlStatement.append(interval);
        sqlStatement.append(" OFFSET ");
        sqlStatement.append(startLimit);
        sqlStatement.append(";");
        System.out.println(sqlStatement);
    }

    public SQLStatement(HashMap<String, String> rowValues, String tableName){
        sqlStatement = new StringBuffer("UPDATE ");
        sqlStatement.append(tableName);
        sqlStatement.append(" SET ");
        System.out.println(tableName);
        Object[] valueSet = rowValues.entrySet().toArray();
        IntStream.range(0, valueSet.length - 1).forEach((inx)->{
            if(inx < valueSet.length - 1){
                final String[] split = valueSet[inx].toString().split("=");
                System.out.println(valueSet[inx].toString());
                System.out.println(split.length);
                System.out.println(split[0]);
                System.out.println(split[1]);
                sqlStatement.append(split[0]);
                sqlStatement.append(" = ");
                sqlStatement.append("\"");
                sqlStatement.append(split[1]);
                sqlStatement.append("\"");
                if(inx < valueSet.length - 2){
                    sqlStatement.append(", ");
                }
            } else {
                final String[] split = "=".split(valueSet[inx].toString());
                sqlStatement.append(" WHERE ");
                sqlStatement.append(split[0]);
                sqlStatement.append(" = ");
                sqlStatement.append(split[1]);
            }
        });
        System.out.println(sqlStatement.toString());
    }

    public String getStatement(){
        System.out.println(sqlStatement.toString());
        return sqlStatement.toString();
    }
}
