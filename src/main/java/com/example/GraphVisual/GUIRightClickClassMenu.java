package com.example.GraphVisual;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUIRightClickClassMenu extends JPopupMenu {
    JMenuItem addAttribute;
    JMenuItem addMethod;
    JMenuItem addConstructor;
    JMenuItem deleteClass;
    
    public GUIRightClickClassMenu() {
        addAttribute = new JMenuItem("Add Attribute");
        add(addAttribute);
        addMethod = new JMenuItem("Add Method");
        add(addMethod);
        addConstructor = new JMenuItem("Add Constructor");
        add(addConstructor);
        deleteClass = new JMenuItem("Delete Class");
        add(deleteClass);
    }

    public JMenuItem getAddAttribute(){
        return this.addAttribute;
    }

    public JMenuItem getAddMethod(){
        return this.addMethod;
    }

    public JMenuItem getAddConstructor(){
        return this.addConstructor;
    }

    public JMenuItem getDeleteClass(){
        return this.deleteClass;
    }
}


