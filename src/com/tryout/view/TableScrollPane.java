package com.tryout.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by patrickackerman on 1/7/17.
 */
public class TableScrollPane extends JScrollPane {
    public TableScrollPane(){
        super();
        setBackground(new Color(0x144454));
        setForeground(new Color(0x429DA1));
        getVerticalScrollBar().setBackground(new Color(0x144454));
        getVerticalScrollBar().setForeground(new Color(0x429DA1));
        getHorizontalScrollBar().setBackground(new Color(0x144454));
        getHorizontalScrollBar().setForeground(new Color(0x429DA1));
    }
}
