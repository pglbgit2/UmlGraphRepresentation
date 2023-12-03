package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.PackageClass;
import com.example.GraphModel.UML_Model.UmlGraph;
import com.example.GraphVisual.GUIMainWindow;
import com.example.GraphVisual.GUIPackagePanel;
import com.example.GraphVisual.GUIPopupGetName;

public class MainWindowController implements ActionListener {
    UmlGraph myModel;
    GUIMainWindow myView;
    ArrayList<GUIPackagePanel> myGUIPackages;
    public MainWindowController(UmlGraph someModel, GUIMainWindow someView){
        this.myModel = someModel;
        this.myView = someView;
        linkWithMenuComponent(this.myView.getMainPopup().getAddPackage(), "addPackage",this);
        linkWithMenuComponent(this.myView.getMainPopup().getRemovePackage(), "removePackage",this);
    }

    public static void linkWithMenuComponent(JMenuItem someItem, String actionCommand, ActionListener someActionListener){
        someItem.setActionCommand(actionCommand);
        someItem.addActionListener(someActionListener);
    }

    public static String askForNameWithPopup(JFrame theFrame){
        GUIPopupGetName newPopup = new GUIPopupGetName("Package Name", theFrame);
        PopupController newController = new PopupController(newPopup);
        newPopup.setVisible(true);
        return newController.getValue();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().compareTo("addPackage") == 0){
            String value = askForNameWithPopup(this.myView.getFrame());
            if(value != null && value.compareTo("") != 0){
                try {
                    PackageClass newPackage = new PackageClass(value);
                    myModel.addValueByName(newPackage);
                    GUIPackagePanel newGUIPackage = myView.addPackages(newPackage);
                    PackagePanelController ppc = new PackagePanelController(newGUIPackage, newPackage);
                } catch (AlreadyExistingStringException e) {
                    JOptionPane.showMessageDialog(null, "Error: package with name "+e.getWanted()+" already exists");
                }
            }
       }
    }
}
