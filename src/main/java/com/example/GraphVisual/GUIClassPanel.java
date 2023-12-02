package com.example.GraphVisual;

import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import com.example.GraphModel.UML_Model.Nameable;

public class GUIClassPanel extends JPanel implements Nameable{
    JPopupMenu rightClickClassMenu;
    JPanel myClassPanel;
    JPanel upPanel;
    GUIClassElementsPanel attributesPanel;
    GUIClassElementsPanel constructorPanel;
    GUIClassElementsPanel methodsPanel;
    JLabel className;

    public GUIClassPanel(String _name){
        super(new GridLayout(4,1));
        this.upPanel = new JPanel();
        className = new JLabel(_name);
        this.upPanel.add(className);
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


    public String getName(){
        return this.className.getText();
    }

    public void setName(String _name){
        this.className.setText(_name);
    }
    
}
