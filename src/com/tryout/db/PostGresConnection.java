package com.tryout.db;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.*;
import java.net.URL;
import java.sql.*;

import static java.nio.file.Files.newBufferedReader;

/**
 * Created by patrickackerman on 1/3/17.
 */

public class PostGresConnection {
    static String dbUrl;
    static String username;
    static String password;

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setDbUrl(String dbUrl) {
        PostGresConnection.dbUrl = dbUrl;
    }

    public static void setUsername(String username) {
        PostGresConnection.username = username;
    }

    public static void setPassword(String password) {
        PostGresConnection.password = password;
    }

    public PostGresConnection(){
        URL url = getClass().getResource("db.config");
        Path file = Paths.get(url.getPath());
        HashMap<String, String> settings = new HashMap<>();
        try(BufferedReader reader = newBufferedReader(file)){
            reader
                    .lines()
                    .forEach(
                            (line)->{
                                String[] keys = line.split(" = ");
                                System.out.println(Arrays.toString(keys));
                                settings.put(keys[0], keys[1]);
                            });
            dbUrl = settings.get("dbUrl");
            username = settings.get("username");
            password = settings.get("password");
        } catch(Exception e){
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

    }

    public PostGresConnection(String url, String uname, String pword){
        dbUrl = url;
        username = uname;
        password = pword;
    }

    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(dbUrl, username, password);
        System.out.println("Driver connected to Postgres Database");
        return conn;
    }

}
