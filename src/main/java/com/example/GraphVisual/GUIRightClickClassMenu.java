package com.example.GraphVisual;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUIRightClickClassMenu extends JPopupMenu {
    JMenuItem addAttribute;
    JMenuItem addMethod;
    
    public GUIRightClickClassMenu() {
        addAttribute = new JMenuItem("Add Attribute");
        add(addAttribute);
        addMethod = new JMenuItem("Add Method");
        add(addMethod);
    }

    public JMenuItem getAddAttribute(){
        return this.addAttribute;
    }

    public JMenuItem getAddMethod(){
        return this.addMethod;
    }
}


