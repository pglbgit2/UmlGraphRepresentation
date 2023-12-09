package com.example.GraphVisual;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUIMenuBar extends JMenuBar{
    JMenuItem file;
    JMenuItem writePackage;

    public GUIMenuBar(){
        file = new JMenu("File");
        writePackage = new JMenuItem("Write Graph");
        file.add(writePackage);
        this.add(file);
    }
}
