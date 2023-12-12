package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.example.GraphModel.GraphFileManager.NotGoodFormatException;
import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Attribute;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.Method;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;
import com.example.GraphVisual.GUIClassPanel;
import com.example.GraphVisual.GUIPopupDestroyObject;
import com.example.GraphVisual.GUIRightClickClassMenu;

public class ClassPanelController implements ActionListener{
    GUIClassPanel myGuiClassPanel;
    Classes myClass;
    JFrame myFrame;
    PackagePanelController fatherPanel;
    public ClassPanelController(GUIClassPanel newGUIClass, Classes newClasse, JFrame someFrame, PackagePanelController fatherPanel) {
        this.myClass = newClasse;
        this.myGuiClassPanel = newGUIClass;
        this.myFrame = someFrame;
        GUIRightClickClassMenu somePopup = this.myGuiClassPanel.getPopup();
        MainWindowController.linkWithMenuComponent(somePopup.getAddAttribute(), "addAttribute", this);
        MainWindowController.linkWithMenuComponent(somePopup.getAddMethod(),"addMethod",this);
        MainWindowController.linkWithMenuComponent(somePopup.getAddConstructor(),"addConstructor",this);
        MainWindowController.linkWithMenuComponent(somePopup.getDeleteClass(),"deleteClass",this);
        this.myGuiClassPanel.refreshContent();
        this.fatherPanel = fatherPanel;
    }

    @Deprecated
    public void addAttr(String attr){
        DefaultTableModel dtm = this.myGuiClassPanel.getAttributeTableModel();
        dtm.addRow(new Object[dtm.getColumnCount()]);
        int n = this.myGuiClassPanel.incAttNb();
        dtm.setValueAt(attr, n, 0);
    }


    public void addAttrLine(){
        this.myGuiClassPanel.getAttributesPanel().addLine();
        this.myGuiClassPanel.incAttNb();
    }

    public void addMethodLine(){
        this.myGuiClassPanel.getMethodsPanel().addLine();
        this.myGuiClassPanel.incMethodNb();
    }

    public void addConstructorLine(){
        this.myGuiClassPanel.getConstructorPanel().addLine();
        this.myGuiClassPanel.incConsNb();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().compareTo("addAttribute") == 0){
            addAttrLine();
            try {
                refreshModel();
            } catch (NoValidVisibilityException | AlreadyExistingStringException | NotGoodFormatException e) {
              
            }
        }
        if(arg0.getActionCommand().compareTo("addMethod") == 0){
            addMethodLine();
            try {
                refreshModel();
            } catch (NoValidVisibilityException | AlreadyExistingStringException | NotGoodFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(arg0.getActionCommand().compareTo("addConstructor") == 0){
            addConstructorLine();
            try {
                refreshModel();
            } catch (NoValidVisibilityException | AlreadyExistingStringException | NotGoodFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(arg0.getActionCommand().compareTo("deleteClass") == 0){
            GUIPopupDestroyObject newPopup = new GUIPopupDestroyObject(myFrame);
            PopupDeleteController newController = new PopupDeleteController(newPopup);
            newPopup.setVisible(true);
            if(newController.getValue()){
                fatherPanel.deleteClass(this);
            }
        }
    }

    public void refreshModel() throws NoValidVisibilityException, AlreadyExistingStringException, NotGoodFormatException {
        DefaultTableModel someAttributeModel = this.myGuiClassPanel.getAttributeTableModel();
        for(int i = 0; i < someAttributeModel.getRowCount()+1 ; i++){
            Attribute newAttribute;
            String attr = (String)someAttributeModel.getValueAt(i, 0);
            if(attr != null){
                newAttribute = Attribute.getAttributeFromString(attr);
                if(!this.myClass.hasAttribute(newAttribute)){
                    this.myClass.addAttribute(newAttribute);
                    System.out.println("nouvel attribut:"+newAttribute.toString());
                }
            }  
        }
        DefaultTableModel someMethodModel = this.myGuiClassPanel.getMethodTableModel();
        for(int i = 0; i < someMethodModel.getRowCount()+1 ; i++){
            String meth = (String)someMethodModel.getValueAt(i, 0);
            if(meth != null && !this.myClass.hasMethod(meth)){
                this.myClass.addMethod(meth); 
            }
        } 
        DefaultTableModel someConstructorModel = this.myGuiClassPanel.getConstructorTableModel();
        for(int i = 0; i < someConstructorModel.getRowCount()+1 ; i++){
                String constructorLine = (String)someConstructorModel.getValueAt(i, 0);
                if(constructorLine != null){
                    String argString = constructorLine.substring(constructorLine.indexOf("(") + 1, constructorLine.indexOf(")"));
                    String[] args = argString.split(", ");
                    if(!this.myClass.hasConstructor(args)){
                        this.myClass.addConstructor(args);  
                    }  
                }
            } 
    }
    
}
