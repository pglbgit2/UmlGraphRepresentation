package com.example.GraphVisual;

import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

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
    GUIPackagePanel myFatherPanel;
    GUIRightClickClassMenu myPopup;

    public GUIClassPanel(String _name, Classes myClass, GUIPackagePanel myFatherPanel){
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

        this.myPopup = new GUIRightClickClassMenu();
        this.setComponentPopupMenu(myPopup);
        this.myFatherPanel = myFatherPanel;
        refreshSize();
    }

    public void refreshSize(){
        Dimension fd = myFatherPanel.getBounds().getSize();
        int n = myFatherPanel.getNbOfValue();
        double x = fd.getWidth();
        double y = fd.getHeight();
        this.setPreferredSize(new Dimension((int) Math.round(x*0.9/n), (int) Math.round(y*0.9/n)));
        Dimension d = this.getBounds().getSize();
        this.className.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*1),(int) Math.round(d.getHeight()*0.1)));
        this.attributesPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*1),(int) Math.round(d.getHeight()*0.3)));
        this.methodsPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*1),(int) Math.round(d.getHeight()*0.3)));
        this.constructorPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*1),(int) Math.round(d.getHeight()*0.3)));
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
