package com.example.GraphVisual;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIPopupGetNameArgs extends JDialog{
    
    JLabel infoName;
    JTextField inputNameField;
    JLabel infoArgs;
    JTextField inputArgsField;
    JPanel popupPanel;
    JButton okButton;
    private JLabel infoParent;
    JTextField inputParentField;

    public GUIPopupGetNameArgs( JFrame _frame){
        super(_frame,"Name and Arguments",true);
        this.inputNameField = new JTextField(30);
        this.inputArgsField = new JTextField(30);
        this.inputParentField = new JTextField(30);
        this.popupPanel = new JPanel();
        this.infoName = new JLabel("Input name, please:");
        this.infoArgs = new JLabel("Input args or nothing, please:");
        this.infoParent = new JLabel("Input parent class name or nothing, please:");
        this.okButton = new JButton("OK");

        popupPanel.add(infoName);
        popupPanel.add(inputNameField);
        popupPanel.add(infoArgs);
        popupPanel.add(inputArgsField);
        popupPanel.add(infoParent);
        popupPanel.add(inputParentField);
        popupPanel.add(okButton);

        this.add(popupPanel);
        setSize(new Dimension(450, 300));
        setLocationRelativeTo(_frame);
    }

    public JButton getOkButton(){
        return this.okButton;
    }

    public JTextField getNameField(){
        return this.inputNameField;
    }
    public JTextField getArgsField(){
        return this.inputArgsField;
    }
    public JTextField getParentField(){
        return this.inputParentField;
    }

}
