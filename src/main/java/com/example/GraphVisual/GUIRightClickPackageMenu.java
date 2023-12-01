package com.example.GraphVisual;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUIRightClickPackageMenu extends JPopupMenu {
    JMenuItem anItem;

    public GUIRightClickPackageMenu() {
        anItem = new JMenuItem("Click Me!");
        add(anItem);
    }
}
