package com.example.GraphController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.plaf.FileChooserUI;

import com.example.GraphModel.GraphFileManager.ClassFileRetriever;
import com.example.GraphModel.GraphFileManager.NotGoodFormatException;
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
        MainWindowController.linkWithMenuComponent(GUIRCPM.getAddClass(), "addClass", this);
        MainWindowController.linkWithMenuComponent(GUIRCPM.getRemoveClass(), "removeClass", this);
        MainWindowController.linkWithMenuComponent(GUIRCPM.getLoadClass(), "loadClass", this);
        this.myFrame = Frame;
    }


    private void addClass(Classes newClasse) throws AlreadyExistingStringException{
        myPackageClass.addValueByName(newClasse);
        GUIClassPanel newGUIPackage = myGuiPackagePanel.addClasses(newClasse);
        myGuiPackagePanel.refreshClassSize();
        ClassPanelController cpc = new ClassPanelController(newGUIPackage, newClasse, this.myFrame);
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
                    this.addClass(newClasse);
                } catch (AlreadyExistingStringException e) {
                    JOptionPane.showMessageDialog(null, "Error: class with name "+e.getWanted()+" already exists");
                } catch (NoValidVisibilityException e) {
                    JOptionPane.showMessageDialog(null, "Error: this "+e.getAttr()+" is not accepted");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please insert values");
            }
        }



        if(arg0.getActionCommand().compareTo("loadClass") == 0){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(true);
            int result = fileChooser.showOpenDialog(this.myFrame);
            ClassFileRetriever classeRetriever = new ClassFileRetriever();
            if (result == JFileChooser.APPROVE_OPTION) {

                File[] selectedFiles = fileChooser.getSelectedFiles();
                for(File f : selectedFiles) {
                    try {
                        Classes newClass = classeRetriever.retrieveClass(f.getAbsolutePath());
                        this.addClass(newClass);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "An error occured");
                        e.printStackTrace();
                    } catch (NotGoodFormatException e) {
                        JOptionPane.showMessageDialog(null, "Not good format: expect "+e.getExpected()+" , actual:"+e.getActual());
                    } catch (NoValidVisibilityException e) {
                        JOptionPane.showMessageDialog(null, "this doesn't have a format that is managed by this program: "+e.getAttr());
                    } catch (AlreadyExistingStringException e) {
                        JOptionPane.showMessageDialog(null, "this already exists: "+e.getWanted());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this.myFrame, "No file selected.");
            }
        }
        
    }

    private ArrayList<String> askForNameAndArgsWithPopup() {
        GUIPopupGetNameArgs newPopup = new GUIPopupGetNameArgs(myFrame);
        PopupArgsController newController = new PopupArgsController(newPopup);
        newPopup.setVisible(true);
        return newController.getValue();
    }
    
}
