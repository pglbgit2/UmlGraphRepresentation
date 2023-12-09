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
        loadClass = new JMenuItem("Load Class");
        add(loadClass);
    }

    public JMenuItem getAddClass(){
        return this.addClass;
    }

    public JMenuItem getLoadClass(){
        return this.loadClass;
    }
}
