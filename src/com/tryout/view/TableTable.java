package com.tryout.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by patrickackerman on 1/13/17.
 */
public class TableTable extends JTable {

    public TableTable(TableModel dm){
        super(dm);
        setBackground(new Color(0x103443));
        setForeground(new Color(0x90A4A8));
        setGridColor(new Color(0x144454));
        setFont(new Font("SansSerif", Font.BOLD, 15));
        setRowHeight(getRowHeight() + 15);
        setSelectionBackground(new Color(0x144454));
        setSelectionForeground(new Color(0x90A4A8));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setOpaque(true);
        renderer.setBackground(new Color(0x144454));
        renderer.setForeground(new Color(0x90A4A8));
        renderer.setFocusable(false);
        getTableHeader().setDefaultRenderer(renderer);
        getTableHeader().setReorderingAllowed(false);
        setBorder(BorderFactory.createEmptyBorder());
    }

}

