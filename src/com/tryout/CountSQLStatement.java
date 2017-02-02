package com.tryout;

/**
 * Created by patrickackerman on 1/5/17.
 */
public class CountSQLStatement extends SQLStatement {
    public CountSQLStatement(String primaryKey, String schema, String table){
        sqlStatement = new StringBuffer("SELECT COUNT(\"");
        sqlStatement.append(primaryKey);
        sqlStatement.append("\") FROM \"");
        sqlStatement.append(schema);
        sqlStatement.append("\".");
        sqlStatement.append(table);
        sqlStatement.append(";");
        System.out.println(sqlStatement);
    }
}
