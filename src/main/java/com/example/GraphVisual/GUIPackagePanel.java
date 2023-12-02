package com.example.GraphVisual;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.example.GraphModel.UML_Model.Nameable;
import com.example.GraphModel.UML_Model.UniqPacketByName;

public class GUIPackagePanel extends JPanel implements Nameable {
    JPopupMenu rightClickPackageMenu;
    JLabel name;
    UniqPacketByName<GUIClassPanel> myClasses; 
    
    public GUIPackagePanel(String _name){
        this.name = new JLabel(_name);
        this.myClasses = new UniqPacketByName<GUIClassPanel>();
    }

    public String getName(){
        return this.name.getText();        
    }

    public void setName(String _name){
        this.name.setText(_name);
    }

}
