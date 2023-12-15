package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.Attr;

import com.example.GraphModel.GraphFileManager.NotGoodFormatException;
import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Attribute;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.Method;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;
import com.example.GraphModel.UML_Model.UniqPacketByName;
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
        try{
            if(arg0.getActionCommand().compareTo("addAttribute") == 0){
                addAttrLine();
                refreshModel();
            }
            if(arg0.getActionCommand().compareTo("addMethod") == 0){
                addMethodLine();
                refreshModel();
            }
            if(arg0.getActionCommand().compareTo("addConstructor") == 0){
                addConstructorLine();
                refreshModel();
            }
        }
        catch(NoValidVisibilityException e){
            JOptionPane.showMessageDialog(this.myFrame, "Problem with visibility");   
        }
        catch(NotGoodFormatException e){
            JOptionPane.showMessageDialog(this.myFrame, "Problem with format");   
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

    public void refreshModel() throws NoValidVisibilityException, NotGoodFormatException {
        ArrayList<String> existingAttributes = new ArrayList<String>();
        DefaultTableModel someAttributeModel = this.myGuiClassPanel.getAttributeTableModel();
        this.myClass.resetAttributes();
        for(int i = 0; i < this.myGuiClassPanel.getNbAttr(); i++){
            String attr = (String)someAttributeModel.getValueAt(i, 0);
            if(attr != null){
                try{
                    Attribute att = this.myClass.addAttribute(attr);
                    existingAttributes.add(att.getName());
                    System.out.println("att:"+att);
                } catch(AlreadyExistingStringException aese){
                    String[] splittedAttribute = attr.split(" ");
                    String name = splittedAttribute[splittedAttribute.length-1];
                    if(existingAttributes.contains(name)){
                        JOptionPane.showMessageDialog(this.myFrame, "You have two attributes with same name: "+name+", please change it");
                    }
                }
            }  
        }

        DefaultTableModel someMethodModel = this.myGuiClassPanel.getMethodTableModel();
        ArrayList<Method> theMethods = this.myClass.getMethods();
        this.myClass.resetMethods();
        for(int i = 0; i < this.myGuiClassPanel.getNbMethod(); i++){
             String methodLine = (String)someMethodModel.getValueAt(i, 0);
             if(methodLine != null && methodLine.compareTo("") != 0){
                try {
                    this.myClass.addMethod(methodLine);
                    System.out.println("meth:"+methodLine);
                } catch (AlreadyExistingStringException e) {
                    try {
                        Method m = Method.getMethodFromDefinition(methodLine);
                        for(Method m2 : theMethods){
                            if(m.compareTo(m2)){
                                JOptionPane.showMessageDialog(this.myFrame, "You have two methods with same args and same name");   
                            }
                        }
                    } catch (AlreadyExistingStringException e1) {
                        JOptionPane.showMessageDialog(this.myFrame, "You have an error with the arguments of your method"+methodLine);
                    }
                }
            }
        }
        DefaultTableModel someConstructorModel = this.myGuiClassPanel.getConstructorTableModel();
        for(int i = 0; i < this.myGuiClassPanel.getNbConstructor(); i++){
                String constructorLine = (String)someConstructorModel.getValueAt(i, 0);
                if(constructorLine != null && constructorLine.compareTo("") != 0){
                    if(constructorLine.contains("\\(") && constructorLine.contains("\\)")){
                        String argString = constructorLine.substring(constructorLine.indexOf("(") + 1, constructorLine.indexOf(")"));
                        String[] args = argString.split(", ");
                        try{
                            this.myClass.addConstructor(args);
                            System.out.println("constructor:"+constructorLine);
                        }
                        catch(AlreadyExistingStringException aese){
                            try{
                                Method m1 = new Method("public",null, this.myClass.getName(), args);
                                for(Method m2 : theMethods){
                                    if(m1.compareTo(m2)){
                                        JOptionPane.showMessageDialog(this.myFrame, "You have two constructor with same args and same name");   
                                    }
                                }
                            }
                            catch(AlreadyExistingStringException e){
                                        JOptionPane.showMessageDialog(this.myFrame, "You have a constructor with two arguments that have the same name");   
                            }
                        }
                    }
                }
            } 
    }
    
}
