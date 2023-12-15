package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.example.GraphVisual.GUIPopupGetNameArgs;

public class PopupArgsController implements ActionListener{

    GUIPopupGetNameArgs myPopup;
    String name;
    String args;
    String parent;
    public PopupArgsController(GUIPopupGetNameArgs newPopup) {
        this.myPopup = newPopup;
        this.myPopup.getOkButton().addActionListener(this);
    }

    public ClassInfo getValue() {
        this.name = this.myPopup.getNameField().getText();
        if(name == null || name.compareTo("") == 0){
           return null;
        }
        this.args = this.myPopup.getArgsField().getText();
        String[] argsTab = new String[]{};
        if(args.compareTo("") != 0 && args.contains(", ")){
            argsTab = args.split(", ");
        }
       return new ClassInfo(name, parent, argsTab);
    }


    @Override
    public void actionPerformed(ActionEvent arg0) { 
        name = this.myPopup.getNameField().getText();
        args = this.myPopup.getArgsField().getText();
        parent = this.myPopup.getParentField().getText();
        this.myPopup.dispose();
    }
}