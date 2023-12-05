package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.example.GraphVisual.GUIPopupGetNameArgs;

public class PopupArgsController implements ActionListener{

    GUIPopupGetNameArgs myPopup;
    String name;
    String args;
    public PopupArgsController(GUIPopupGetNameArgs newPopup) {
        this.myPopup = newPopup;
        this.myPopup.getOkButton().addActionListener(this);
    }

    public ArrayList<String> getValue() {
        this.name = this.myPopup.getNameField().getText();
        if(name == null){
           return null;
        }
        this.args = this.myPopup.getArgsField().getText();
       ArrayList<String> nameArgs = new ArrayList<String>();
        nameArgs.add(name);
        if(args.contains(", ")){
            String[] argsTab = args.split(", ");     
           for(String arg : argsTab){
                nameArgs.add(arg);
           }
        }
        else{
            if(args != null && args.compareTo("") != 0){
                nameArgs.add(args);
            }
        }
        
       return nameArgs;
    }


    @Override
    public void actionPerformed(ActionEvent arg0) { 
        name = this.myPopup.getNameField().getText();
        args = this.myPopup.getArgsField().getText();
        this.myPopup.dispose();
    }
}