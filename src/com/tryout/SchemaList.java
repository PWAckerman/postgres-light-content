package com.tryout;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

/**
 * Created by patrickackerman on 1/6/17.
 */
public class SchemaList {

    private DefaultListModel schemaListModel = new DefaultListModel();
    private JList schemaList = new JList();

    public SchemaList(Object[] list){

        System.out.println("constructoring.");
        for(int i = 0; i < list.length; i++){
            System.out.println(list[i].toString());
            schemaListModel.add(i, list[i].toString());
        }
        schemaList.setModel(getSchemaModel());
        System.out.println(schemaList.getModel().getSize());
        schemaList.setPreferredSize(new Dimension(500,500));
        schemaList.setBackground(new Color(0xffffff));
    }

    public JList getSchemaList(){
        return schemaList;
    }

    public DefaultListModel getSchemaModel(){
        return schemaListModel;
    }
}
