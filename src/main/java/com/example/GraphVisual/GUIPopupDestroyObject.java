package com.example.GraphVisual;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPopupDestroyObject extends JDialog{
    JButton okButton;
    JButton cancelButton;
    JPanel popupPanel;
    JLabel info;

    public GUIPopupDestroyObject(JFrame _frame){
        super(_frame,"Delete",true);
        this.popupPanel = new JPanel();
        this.okButton = new JButton("OK");
        this.cancelButton = new JButton("Cancel");
        this.info = new JLabel("Press Cancel to cancel, OK to destroy");
        popupPanel.add(okButton);
        popupPanel.add(cancelButton);
        this.add(popupPanel);
        setSize(new Dimension(450, 300));
        setLocationRelativeTo(_frame);
    }

    public JButton getOkButton(){
        return this.okButton;
    }

    public JButton getCancelButton(){
        return this.cancelButton;
    }
}
