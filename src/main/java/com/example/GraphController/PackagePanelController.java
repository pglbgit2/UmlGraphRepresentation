package com.example.GraphController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;
import com.example.GraphModel.UML_Model.PackageClass;
import com.example.GraphVisual.GUIClassPanel;
import com.example.GraphVisual.GUIPackagePanel;
import com.example.GraphVisual.GUIPopupGetNameArgs;
import com.example.GraphVisual.GUIRightClickPackageMenu;

public class PackagePanelController implements ActionListener{

    GUIPackagePanel myGuiPackagePanel;
    PackageClass myPackageClass;
    JFrame myFrame;

    public PackagePanelController(GUIPackagePanel myPackageView, PackageClass myPackageModel, JFrame Frame){
        this.myGuiPackagePanel = myPackageView;
        this.myPackageClass = myPackageModel;
        GUIRightClickPackageMenu GUIRCPM =  new GUIRightClickPackageMenu();
        this.myGuiPackagePanel.setComponentPopupMenu(GUIRCPM);
        JMenuItem addClassItem = GUIRCPM.getAddClass();
        addClassItem.setActionCommand("addClass");
        addClassItem.addActionListener(this);
        this.myFrame = Frame;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().compareTo("addClass") == 0){
            ArrayList<String> values = askForNameAndArgsWithPopup();
            if(values != null){
            String[] args = new String[values.size()-1];
            int i = 0;
            for(String val : values){
                if(i != 0){
                    args[i-1] = val;
                }
                i++;
            }
                try {
                    Classes newClasse = new Classes(values.get(0), args);
                    myPackageClass.addValueByName(newClasse);
                    GUIClassPanel newGUIPackage = myGuiPackagePanel.addClasses(newClasse);
                    ClassPanelController cpc = new ClassPanelController(newGUIPackage, newClasse, this.myFrame);
                } catch (AlreadyExistingStringException e) {
                    JOptionPane.showMessageDialog(null, "Error: class with name "+e.getWanted()+" already exists");
                } catch (NoValidVisibilityException e) {
                    JOptionPane.showMessageDialog(null, "Error: this "+e.getAttr()+" is not accepted");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please insert values");

        }
    }

    private ArrayList<String> askForNameAndArgsWithPopup() {
        GUIPopupGetNameArgs newPopup = new GUIPopupGetNameArgs(myFrame);
        PopupArgsController newController = new PopupArgsController(newPopup);
        newPopup.setVisible(true);
        return newController.getValue();
    }
    
}
