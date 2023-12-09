package com.example.GraphVisual;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUIRightClickPackageMenu extends JPopupMenu{
    JMenuItem addClass;
    JMenuItem loadClass;
    JMenuItem deletePackage;

    public GUIRightClickPackageMenu() {
        addClass = new JMenuItem("Add Class");
        add(addClass);
        loadClass = new JMenuItem("Load Class");
        add(loadClass);
        deletePackage = new JMenuItem("Delete Package");
        add(deletePackage);
    }

    public JMenuItem getAddClass(){
        return this.addClass;
    }

    public JMenuItem getDeletePackage(){
        return this.deletePackage;
    }

    public JMenuItem getLoadClass(){
        return this.loadClass;
    }
}
