package com.tryout;

import com.tryout.model.DBInfoWindowModel;
import com.tryout.controllers.DBInfoWindowController;

/**
 * Created by patrickackerman on 1/11/17.
 */
public class DBInfoWindowInjector {
    public DBInfoWindowInjector(){
        DBInfoWindowModel model = new DBInfoWindowModel();
        DBInfoWindowController controller = new DBInfoWindowController(model);
    }
}
