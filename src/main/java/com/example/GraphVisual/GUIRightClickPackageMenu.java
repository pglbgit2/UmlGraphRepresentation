package com.example.GraphVisual;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUIRightClickPackageMenu extends JPopupMenu{
    JMenuItem addClass;
    JMenuItem loadClass;
    JMenuItem removeClass;
    
    public GUIRightClickPackageMenu() {
        addClass = new JMenuItem("Add Class");
        add(addClass);
        removeClass = new JMenuItem("Remove Class");
        add(removeClass);
        loadClass = new JMenuItem("Load Class");
        add(loadClass);
    }
}
