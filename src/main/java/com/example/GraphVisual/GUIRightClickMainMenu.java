package com.example.GraphVisual;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUIRightClickMainMenu extends JPopupMenu {
    JMenuItem addPackage;
    JMenuItem removePackage;
    
    public GUIRightClickMainMenu() {
        addPackage = new JMenuItem("Add Package");
        add(addPackage);
        removePackage = new JMenuItem("Remove Package");
        add(removePackage);
    }

    public JMenuItem getAddPackage(){
        return this.addPackage;
    }

     public JMenuItem getRemovePackage(){
        return this.removePackage;
    }
}
