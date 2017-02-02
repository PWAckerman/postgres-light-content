package com.tryout.view;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by patrickackerman on 1/7/17.
 */
public class SchemaTree extends JTree {
    DefaultMutableTreeNode top;

    public SchemaTree(DefaultMutableTreeNode topNode){
        super(topNode);
        ImageIcon icon = createImageIcon("../images/griglia-architetto-fran-01r.png","SHEET");

        top = topNode;
        setBackground(new Color(60,63,65));
        setDropMode(DropMode.USE_SELECTION);
        setEnabled(true);
        setForeground(new Color(187,187,187));

        setRootVisible(true);
        setRowHeight(20);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        icon.setImage(getScaledImage(icon.getImage(), 15, 15));
        renderer.setTextNonSelectionColor(new Color(187,187,187));
        renderer.setTextSelectionColor(new Color(187,187,187));
        renderer.setBackgroundNonSelectionColor(new Color(60,63,65));
        renderer.setBackgroundSelectionColor(new Color(12, 34, 54));
        renderer.setBorderSelectionColor(new Color(12, 34, 54));
        renderer.setLeafIcon(icon);
        setCellRenderer(renderer);
    }

    public void appendSchema(String str){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(str);
        top.add(node);
    }

    public void appendTable(String tbl){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)top.getChildAt(top.getChildCount() - 1);

        DefaultMutableTreeNode tblNode = new DefaultMutableTreeNode(tbl);
        node.add(tblNode);
    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
