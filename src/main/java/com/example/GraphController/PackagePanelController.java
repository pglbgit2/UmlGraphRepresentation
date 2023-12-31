package com.example.GraphController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.example.GraphModel.GraphFileManager.ClassFileRetriever;
import com.example.GraphModel.GraphFileManager.NotGoodFormatException;
import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.Classes;
import com.example.GraphModel.UML_Model.NoValidVisibilityException;
import com.example.GraphModel.UML_Model.PackageClass;
import com.example.GraphVisual.GUIClassPanel;
import com.example.GraphVisual.GUIPackagePanel;
import com.example.GraphVisual.GUIPopupDestroyObject;
import com.example.GraphVisual.GUIPopupGetNameArgs;
import com.example.GraphVisual.GUIRightClickPackageMenu;

public class PackagePanelController implements ActionListener{

    GUIPackagePanel myGuiPackagePanel;
    PackageClass myPackageClass;
    JFrame myFrame;
    MainWindowController fatherPanel;
    ArrayList<ClassPanelController> myClassesController;

    public PackagePanelController(GUIPackagePanel myPackageView, PackageClass myPackageModel, JFrame Frame, MainWindowController fatherPanel){
        this.myGuiPackagePanel = myPackageView;
        this.myPackageClass = myPackageModel;
        GUIRightClickPackageMenu GUIRCPM =  new GUIRightClickPackageMenu();
        this.myGuiPackagePanel.setComponentPopupMenu(GUIRCPM);
        MainWindowController.linkWithMenuComponent(GUIRCPM.getAddClass(), "addClass", this);
        MainWindowController.linkWithMenuComponent(GUIRCPM.getLoadClass(), "loadClass", this);
        MainWindowController.linkWithMenuComponent(GUIRCPM.getDeletePackage(), "deletePackage", this);
        this.myFrame = Frame;
        this.fatherPanel = fatherPanel;
        this.myClassesController = new ArrayList<ClassPanelController>();
    }


    private void addClass(Classes newClasse) throws AlreadyExistingStringException{
        myPackageClass.addValueByName(newClasse);
        GUIClassPanel newGUIClass = myGuiPackagePanel.addClasses(newClasse);
        ClassPanelController cpc = new ClassPanelController(newGUIClass, newClasse, this.myFrame, this);
        this.myClassesController.add(cpc);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().compareTo("addClass") == 0){
            ClassInfo values = askForNameAndArgsWithPopup();
            if(values != null){
                try {
                    Classes newClasse = new Classes(values.getName(), values.getParent(),values.getArgs());
                    this.addClass(newClasse);
                    this.fatherPanel.myView.refreshSizeOnPackages();
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
        
        if(arg0.getActionCommand().compareTo("deletePackage") == 0){
            GUIPopupDestroyObject newPopup = new GUIPopupDestroyObject(myFrame);
            PopupDeleteController newController = new PopupDeleteController(newPopup);
            newPopup.setVisible(true);
            if(newController.getValue()){
                fatherPanel.deletePack(this);
            }
        
        }
        
    }

    private ClassInfo askForNameAndArgsWithPopup() {
        GUIPopupGetNameArgs newPopup = new GUIPopupGetNameArgs(myFrame);
        PopupArgsController newController = new PopupArgsController(newPopup);
        newPopup.setVisible(true);
        return newController.getValue();
    }


    public void deleteClass(ClassPanelController classPanelController) {
        Classes toDestroy = classPanelController.myClass;
        this.myPackageClass.deleteValueByName(toDestroy.getName());
        this.myGuiPackagePanel.deleteClass(toDestroy);
        this.myFrame.revalidate();
        this.myFrame.repaint();
        this.myClassesController.remove(classPanelController);
    }


    public void refreshModel() throws NoValidVisibilityException, AlreadyExistingStringException, NotGoodFormatException {
        for(ClassPanelController cpc : this.myClassesController){
            cpc.refreshModel();
        }
    }
    
}
