package com.tryout.view;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by patrickackerman on 1/7/17.
 */
public class SchemaScrollPane extends JScrollPane {

    public SchemaScrollPane(SchemaTree schemaTree){
        super(schemaTree);
        setViewportBorder(BorderFactory.createEmptyBorder());
        setBorder(BorderFactory.createEmptyBorder());
    }
}
