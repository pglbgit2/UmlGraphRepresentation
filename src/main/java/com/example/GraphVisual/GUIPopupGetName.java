package com.example.GraphVisual;

import java.awt.Dimension;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIPopupGetName extends JDialog {
    JLabel inputInformation;
    JTextField inputField;
    JPanel popupPanel;
    JButton okButton;
    
    public GUIPopupGetName(String Wanted, JFrame _frame){
        super(_frame,Wanted,true);
        this.inputField = new JTextField(30);
        this.popupPanel = new JPanel();
        this.inputInformation = new JLabel("Input "+Wanted+", please:");
        this.okButton = new JButton("OK");

        popupPanel.add(inputInformation);
        popupPanel.add(inputField);
        popupPanel.add(okButton);

        this.add(popupPanel);
        setSize(new Dimension(450, 300));
        setLocationRelativeTo(_frame);
    }

    public JButton getOkButton(){
        return this.okButton;
    }


    public JTextField getValueField(){
        return this.inputField;
    }
}
