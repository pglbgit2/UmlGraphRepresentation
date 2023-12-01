package com.example.GraphVisual;

import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUIClassPanel extends JPanel{
    JPopupMenu rightClickClassMenu;
    JPanel myClassPanel;
    JTextField className;
    
    JPanel upPanel;
    GUIClassElementsPanel attributesPanel;
    GUIClassElementsPanel constructorPanel;
    GUIClassElementsPanel methodsPanel;

    public GUIClassPanel(){
        super(new GridLayout(4,1));
        this.upPanel = new JPanel(new GridLayout(1,2));
        this.className = new JTextField(20);
        JLabel classNameFillInformation = new JLabel("class name:");
        this.upPanel.add(classNameFillInformation);
        this.upPanel.add(this.className);
        this.add(this.upPanel);

        ArrayList<String> attributesNames = new ArrayList<String>();
        attributesNames.add("Attribute ");
        attributesNames.add("HasGetter");
        attributesNames.add("HasSetter");
        this.attributesPanel = new GUIClassElementsPanel("Attribute", attributesNames);
        this.add(this.attributesPanel);

        ArrayList<String> constructorsNames = new ArrayList<String>();
        constructorsNames.add("Constructor");
        this.constructorPanel = new GUIClassElementsPanel("Constructor", constructorsNames);
        this.add(this.constructorPanel);

        ArrayList<String> methodsNames = new ArrayList<String>();
        methodsNames.add("Method");
        this.methodsPanel = new GUIClassElementsPanel("Method", methodsNames);
        this.add(this.methodsPanel);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.LIGHT_GRAY); 
        
    }
    
}
