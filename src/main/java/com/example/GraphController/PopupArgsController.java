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
       ArrayList<String> nameArgs = new ArrayList<String>();
        nameArgs.add(name);
        String[] argsTab = args.split(", ");
        if(argsTab == null){
            nameArgs.add(args);
        }
        else{
           for(String arg : argsTab){
                nameArgs.add(arg);
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