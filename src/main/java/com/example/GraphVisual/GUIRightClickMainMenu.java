package com.example.GraphVisual;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUIRightClickMainMenu extends JPopupMenu {
    JMenuItem addPackage;
    JMenuItem removePackage;
    
    public GUIRightClickMainMenu() {
        addPackage = new JMenuItem("Add Package");
        add(addPackage);
    }

    public JMenuItem getAddPackage(){
        return this.addPackage;
    }

}
