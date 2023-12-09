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

    public UniqPacketByName<GUIClassPanel> getClasses(){
        return this.myClasses;
    }

    public void setName(String _name){
        this.name.setText(_name);
    }

    public void refreshClassSize(){
        Dimension fd = this.getPreferredSize();
        int n = this.getNbOfValue();
        double x = fd.getWidth();
        double y = fd.getHeight();
        for(GUIClassPanel classe: myClasses.getPackets()){
            Dimension d = new Dimension((int) Math.round(x*0.9), (int) Math.round(y*0.9/n));
            classe.setPreferredSize(d);
            classe.upPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*0.9),(int) Math.round(d.getHeight()*0.05)));
            classe.contentPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*0.9),(int) Math.round(d.getHeight()*0.85)));
            classe.refreshSize();
        }
    }


    public void deleteClass(Classes toDestroy){
        GUIClassPanel toRemove = this.myClasses.deleteValueByName(toDestroy.getName());
        this.remove(toRemove);
    }

    public GUIClassPanel addClasses(Classes newClasse) throws AlreadyExistingStringException {
       GUIClassPanel newGUIClass = new GUIClassPanel(newClasse.getName(), newClasse);
       this.myClasses.addValueByName(newGUIClass);
       this.add(newGUIClass);
       return newGUIClass;
    }

    public int getNbOfValue(){
        return this.myClasses.getSize();
    }

}
