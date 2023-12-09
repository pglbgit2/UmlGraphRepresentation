package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.example.GraphVisual.GUIPopupDestroyObject;

public class PopupDeleteController implements ActionListener{
     GUIPopupDestroyObject myPopup;
    Boolean toDestroy;
    public PopupDeleteController(GUIPopupDestroyObject somePopup){
        this.myPopup = somePopup;
        JButton Ok = this.myPopup.getOkButton();
        Ok.addActionListener(this);
        Ok.setActionCommand("Destroy");
        JButton Cancel = this.myPopup.getCancelButton();
        Cancel.addActionListener(this);
        toDestroy = false;
    }

    public Boolean getValue(){
        return this.toDestroy;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) { 
        if(arg0.getActionCommand().compareTo("Destroy") == 0){
            toDestroy = true;
        }
        this.myPopup.dispose();
    }
}
