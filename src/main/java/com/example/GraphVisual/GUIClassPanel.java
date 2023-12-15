package com.example.GraphVisual;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.example.GraphModel.UML_Model.Attribute;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.Method;
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
        this.myClass = myClass;
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

    public GUIClassElementsPanel getAttributesPanel(){
         return this.attributesPanel;
    }

    public GUIClassElementsPanel getMethodsPanel(){
         return this.methodsPanel;
    }

    public GUIClassElementsPanel getConstructorPanel(){
         return this.constructorPanel;
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

    public int getNbAttr(){
        return this.attributesPanel.nbRows;
    }

    public int incAttNb() {
        return this.attributesPanel.nbRows++;
    }

    public int getNbMethod(){
        return this.methodsPanel.nbRows;
    }

    public int incMethodNb(){
        return this.methodsPanel.nbRows++;
    }

    public Classes refreshClass(){
        return null;
    }

    public void refreshContent() {
        this.attributesPanel.myModel = new DefaultTableModel(this.attributesPanel.columns.toArray(), 0);
        this.attributesPanel.myTable = new JTable(this.attributesPanel.myModel);
        for(Attribute att : this.myClass.getAttributes()){
            this.attributesPanel.addLine();
            this.attributesPanel.myModel.setValueAt(att.toString(), this.attributesPanel.myModel.getRowCount()-1,0);
        }
        this.methodsPanel.myModel = new DefaultTableModel(this.methodsPanel.columns.toArray(), 0);
        this.methodsPanel.myTable = new JTable(this.methodsPanel.myModel);
        this.constructorPanel.myModel = new DefaultTableModel(this.constructorPanel.columns.toArray(), 0);
        this.constructorPanel.myTable = new JTable(this.constructorPanel.myModel);
        for(Method method : this.myClass.getMethods()){
            if(method.getName().compareTo(this.myClass.getName()) == 0){
                this.constructorPanel.addLine();
                this.constructorPanel.myModel.setValueAt(method.getMethodForGUI(), this.constructorPanel.myModel.getRowCount()-1,0);
            }
            else{
                this.methodsPanel.addLine();
                this.methodsPanel.myModel.setValueAt(method.getMethodForGUI(), this.methodsPanel.myModel.getRowCount()-1,0);
            }
        }
        this.attributesPanel.refresh();
        this.methodsPanel.refresh();
        this.constructorPanel.refresh();

    }

    public int getNbConstructor(){
        return this.constructorPanel.nbRows;
    }

    public int incConsNb() {
        return this.constructorPanel.nbRows++;
    }
}
