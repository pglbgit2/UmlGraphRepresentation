package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.GraphModel.UML_Model.Attribute;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphVisual.GUIClassPanel;
import com.example.GraphVisual.GUIRightClickClassMenu;

public class ClassPanelController implements ActionListener{
    GUIClassPanel myGuiClassPanel;
    Classes myClass;
    JFrame myFrame;
    public ClassPanelController(GUIClassPanel newGUIClass, Classes newClasse, JFrame someFrame) {
        this.myClass = newClasse;
        this.myGuiClassPanel = newGUIClass;
        this.myFrame = someFrame;
        GUIRightClickClassMenu somePopup = this.myGuiClassPanel.getPopup();
        MainWindowController.linkWithMenuComponent(somePopup.getAddAttribute(), "addAttribute", this);
        MainWindowController.linkWithMenuComponent(somePopup.getAddMethod(),"addMethod",this);
        for(Attribute attr : newClasse.getAttributes()){
            addAttr(attr.toString());
        }
    }

    public void addAttr(String attr){
        DefaultTableModel dtm = this.myGuiClassPanel.getAttributeTableModel();
        dtm.addRow(new Object[dtm.getColumnCount()]);
        int n = this.myGuiClassPanel.incAttNb();
        dtm.setValueAt(attr, n, 0);
    }


    public void addAttrLine(){
        DefaultTableModel dtm = this.myGuiClassPanel.getAttributeTableModel();
        dtm.addRow(new Object[dtm.getColumnCount()]);
        this.myGuiClassPanel.incAttNb();
    }

    public void addMethodLine(){
        DefaultTableModel dtm = this.myGuiClassPanel.getMethodTableModel();
        dtm.addRow(new Object[dtm.getColumnCount()]);
        this.myGuiClassPanel.incMethodNb();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().compareTo("addAttribute") == 0){
            addAttrLine();
        }
        if(arg0.getActionCommand().compareTo("addMethod") == 0){
            addMethodLine();
        }
    }
    
}
