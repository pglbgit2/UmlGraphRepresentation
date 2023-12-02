package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import com.example.GraphVisual.GUIPopupGetName;

public class PopupController implements ActionListener{

    GUIPopupGetName myPopup;
    String value;
    public PopupController(GUIPopupGetName somePopup){
        this.myPopup = somePopup;
        this.myPopup.getOkButton().addActionListener(this);
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) { 
        value = this.myPopup.getValueField().getText();
        this.myPopup.dispose();
    }
    
}
