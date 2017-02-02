package com.tryout;

/**
 * Created by patrickackerman on 1/5/17.
 */
public class PKSQLStatement extends SQLStatement {
    public PKSQLStatement(String tableName){
        sqlStatement = new StringBuffer("SELECT\n" + "c.column_name, c.data_type\n" + "FROM\n" +
                "information_schema.table_constraints tc\n" +
                "JOIN information_schema.constraint_column_usage AS ccu USING (constraint_schema, constraint_name)\n" +
                "JOIN information_schema.columns AS c ON c.table_schema = tc.constraint_schema AND tc.table_name = c.table_name AND ccu.column_name = c.column_name\n" +
                "where constraint_type = 'PRIMARY KEY' and tc.table_name = ");
        sqlStatement.append("'");
        sqlStatement.append(tableName);
        sqlStatement.append("';");
        System.out.println(sqlStatement.toString());
    }
}
