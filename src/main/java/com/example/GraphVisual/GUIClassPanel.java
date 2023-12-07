package com.example.GraphVisual;

import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.Nameable;

public class GUIClassPanel extends JPanel implements Nameable{
    JPopupMenu rightClickClassMenu;
    JPanel myClassPanel;
    JPanel upPanel;
    GUIClassElementsPanel attributesPanel;
    GUIClassElementsPanel constructorPanel;
    GUIClassElementsPanel methodsPanel;
    JLabel className;
    Classes myClass;
    GUIRightClickClassMenu myPopup;
    JPanel contentPanel;

    public GUIClassPanel(String _name, Classes myClass){
        
        this.upPanel = new JPanel();
        className = new JLabel(_name);
        this.upPanel.add(className);
        this.add(this.upPanel);
        this.contentPanel = new JPanel();

        ArrayList<String> attributesNames = new ArrayList<String>();
        attributesNames.add("Attribute ");
        attributesNames.add("HasGetter");
        attributesNames.add("HasSetter");
        this.attributesPanel = new GUIClassElementsPanel("Attribute", attributesNames);
        this.contentPanel.add(this.attributesPanel);

        ArrayList<String> constructorsNames = new ArrayList<String>();
        constructorsNames.add("Constructor");
        this.constructorPanel = new GUIClassElementsPanel("Constructor", constructorsNames);
        this.contentPanel.add(this.constructorPanel);

        ArrayList<String> methodsNames = new ArrayList<String>();
        methodsNames.add("Method");
        this.methodsPanel = new GUIClassElementsPanel("Method", methodsNames);
        this.contentPanel.add(this.methodsPanel);
        this.add(contentPanel);

        this.myPopup = new GUIRightClickClassMenu();
        this.setComponentPopupMenu(myPopup);
        this.setBackground(Color.WHITE); 
    }

    public void refreshSize(){
        Dimension d = this.contentPanel.getPreferredSize();
        this.attributesPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*0.9),(int) Math.round(d.getHeight()*0.25)));
        this.methodsPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*0.9),(int) Math.round(d.getHeight()*0.25)));
        this.constructorPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*0.9),(int) Math.round(d.getHeight()*0.25)));
    }
    

    public GUIRightClickClassMenu getPopup(){
        return this.myPopup;
    }

    public JTable getAttributeTable(){
        return this.attributesPanel.getTable();
    }

    public JTable getMethodTable(){
        return this.methodsPanel.getTable();
    }

    public JTable getConstructorTable(){
        return this.constructorPanel.getTable();
    }

    public DefaultTableModel getAttributeTableModel(){
        return this.attributesPanel.getTableModel();
    }

    public DefaultTableModel getMethodTableModel(){
        return this.methodsPanel.getTableModel();
    }

    public DefaultTableModel getConstructorTableModel(){
        return this.constructorPanel.getTableModel();
    }

    public String getName(){
        return this.className.getText();
    }

    public void setName(String _name){
        this.className.setText(_name);
    }

    public int incAttNb() {
        return this.attributesPanel.nbRows++;
    }
    
}
