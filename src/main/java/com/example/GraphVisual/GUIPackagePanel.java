package com.example.GraphVisual;


import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.Nameable;
import com.example.GraphModel.UML_Model.UniqPacketByName;

public class GUIPackagePanel extends JPanel implements Nameable {
    JPopupMenu rightClickPackageMenu;
    JLabel name;
    UniqPacketByName<GUIClassPanel> myClasses;
    
    public GUIPackagePanel(String _name){
        this.name = new JLabel(_name);
        this.myClasses = new UniqPacketByName<GUIClassPanel>();
        this.add(name);
    }
    public String getName(){
        return this.name.getText();        
    }

    public void setName(String _name){
        this.name.setText(_name);
    }

    public void refreshClassSize(){
        Dimension fd = this.getBounds().getSize();
        int n = this.getNbOfValue();
        double x = fd.getWidth();
        double y = fd.getHeight();
        for(GUIClassPanel classe: myClasses.getPackets()){
            classe.setSize(new Dimension((int) Math.round(x*0.9/n), (int) Math.round(y*0.9/n)));
            Dimension d = classe.getBounds().getSize();
            classe.className.setSize(new Dimension((int) Math.round(d.getWidth()*1),(int) Math.round(d.getHeight()*0.05)));
            classe.attributesPanel.setSize(new Dimension((int) Math.round(d.getWidth()*1),(int) Math.round(d.getHeight()*0.3)));
            classe.methodsPanel.setSize(new Dimension((int) Math.round(d.getWidth()*1),(int) Math.round(d.getHeight()*0.3)));
            classe.constructorPanel.setSize(new Dimension((int) Math.round(d.getWidth()*1),(int) Math.round(d.getHeight()*0.3)));
        }
    }

    public GUIClassPanel addClasses(Classes newClasse) throws AlreadyExistingStringException {
       GUIClassPanel newGUIClass = new GUIClassPanel(newClasse.getName(), newClasse);
       this.myClasses.addValueByName(newGUIClass);
       this.add(newGUIClass);
       refreshClassSize();
       return newGUIClass;
    }

    public int getNbOfValue(){
        return this.myClasses.getSize();
    }

}
