package com.tryout.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by patrickackerman on 1/7/17.
 */
public class TableTabbedPane extends JTabbedPane {
    public TableTabbedPane(){
        super();
        setBackground(new Color(54, 56, 59));
        setForeground(new Color(187,187,187));
//        setBorderSelectionColor(new Color(12, 34, 54));
        setBorder(BorderFactory.createEmptyBorder());
        setOpaque(false);
    }
}
