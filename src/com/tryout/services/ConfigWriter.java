package com.tryout.services;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * Created by patrickackerman on 1/11/17.
 */
public class ConfigWriter {
    public ConfigWriter(String dburl, String username, String password){

        String contents = String.format("dbUrl = %s\nusername = %s\npassword = %s\n", dburl, username, password);
        System.out.print(contents);
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("db.config"), StandardCharsets.UTF_8)){
            writer.write(contents);
            writer.close();
            System.out.println("written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
