package com.tryout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by patrickackerman on 1/6/17.
 */
public class ListCompiledListener implements ActionListener {
    private DatabaseListener dbListener;
    private SchemaList schemaList;

    public void setSchemaList(SchemaList list){
        SchemaList schemaList = list;
    }

    public void setDatabaseListener(DatabaseListener listener){ this.dbListener = listener; };

    public void actionPerformed(ActionEvent e){ dbListener.completeEmitted(schemaList);};
}
