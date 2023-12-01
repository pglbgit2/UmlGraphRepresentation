package com.example.GraphVisual;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class GUIPackagePanel extends JPanel {
    JPopupMenu rightClickPackageMenu;
    ArrayList<GUIClassPanel> myClasses;
    
    public GUIPackagePanel(){
        this.myClasses = new ArrayList<GUIClassPanel>();
    }
}
