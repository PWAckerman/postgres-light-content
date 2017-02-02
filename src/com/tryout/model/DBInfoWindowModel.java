package com.tryout.model;

import java.util.HashMap;
import java.util.Observable;

/**
 * Created by patrickackerman on 1/11/17.
 */
public class DBInfoWindowModel extends Observable {
    public String databaseIp;
    public String databaseName;
    public String databaseUserName;
    public String databasePassword;
    public String connectionString;
    public HashMap<String, String> valueMap = new HashMap<>();

    public String getDatabaseIp() {
        return databaseIp;
    }

    public void setDatabaseIp(String databaseIp) {
        this.databaseIp = databaseIp;
        valueMap.put("ip", databaseIp);
        setChanged();
        constructConnectionString();
        notifyObservers(valueMap);
        System.out.println("ip:model:updated");
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        valueMap.put("dbname", databaseName);
        setChanged();
        constructConnectionString();
        notifyObservers(valueMap);
        System.out.println("ip:dbname:updated");
    }

    public String getDatabaseUserName() {
        return databaseUserName;
    }

    public void setDatabaseUserName(String databaseUserName) {
        this.databaseUserName = databaseUserName;
        valueMap.put("username", databaseUserName);
        setChanged();
        notifyObservers(valueMap);
        System.out.println("ip:username:updated");
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
        valueMap.put("password", databasePassword);
        setChanged();
        notifyObservers(valueMap);
        System.out.println("ip:password:updated");
    }

    public void constructConnectionString(){
        connectionString = "jdbc:postgresql://" + databaseIp + ":5432/" + databaseName;
        System.out.println(connectionString);
        valueMap.put("connection", connectionString);
        notifyObservers(valueMap);
        System.out.println("ip:connectionstring:updated");
    }

    public String getConnectionString(){
        return connectionString;
    }
}
