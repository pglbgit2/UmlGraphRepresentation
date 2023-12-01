package com.example.GraphVisual;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUIMenuBar extends JMenuBar{
    JMenuItem file;
    JMenuItem loadPackage;
    JMenuItem writePackage;

    public GUIMenuBar(){
        file = new JMenu("File");
        loadPackage = new JMenuItem("Load Package");
        writePackage = new JMenuItem("Write Package");
        file.add(loadPackage);
        file.add(writePackage);
        this.add(file);
    }
}
