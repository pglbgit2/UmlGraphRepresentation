package com.example.GraphController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.example.GraphModel.GraphFileManager.ClassFileRetriever;
import com.example.GraphModel.GraphFileManager.PackageDirWriter;
import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.PackageClass;
import com.example.GraphModel.UML_Model.UmlGraph;
import com.example.GraphVisual.GUIMainWindow;
import com.example.GraphVisual.GUIPackagePanel;
import com.example.GraphVisual.GUIPopupGetName;

public class MainWindowController implements ActionListener {
    UmlGraph myModel;
    GUIMainWindow myView;

    public MainWindowController(UmlGraph someModel, GUIMainWindow someView){
        this.myModel = someModel;
        this.myView = someView;
        linkWithMenuComponent(this.myView.getMainPopup().getAddPackage(), "addPackage",this);
        linkWithMenuComponent(this.myView.getWriteGraph(), "writeGraph",this);
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
                    PackagePanelController ppc = new PackagePanelController(newGUIPackage, newPackage, this.myView.getFrame(), this);
                } catch (AlreadyExistingStringException e) {
                    JOptionPane.showMessageDialog(null, "Error: package with name "+e.getWanted()+" already exists");
                }
            }
       }
       if(arg0.getActionCommand().compareTo("writeGraph") == 0){
            if(this.myModel.getPackets().size() == 0){
                JOptionPane.showMessageDialog(this.myView.getFrame(), "No package to write.");
                return;
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            int result = fileChooser.showOpenDialog(this.myView.getFrame());
            boolean writeFile = false;
            if (result == JFileChooser.APPROVE_OPTION) {
                File someFile = fileChooser.getSelectedFile();
                try {
                    Files.createDirectory(Paths.get(someFile.getAbsolutePath()));
                    writeFile = true;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this.myView.getFrame(), "Problem occured");
                } 
                
                if(writeFile){
                    for(PackageClass onePack : this.myModel.getPackets()){
                        PackageDirWriter newPackageWriter = new PackageDirWriter(onePack);
                        try {
                            newPackageWriter.writePackage(fileChooser.getSelectedFile().getAbsolutePath());
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(this.myView.getFrame(), "Problem occured while writing package"+onePack.getName()+". What have you done ? RUN, YOU FOOL !");
                            return;
                        }
                        JOptionPane.showMessageDialog(this.myView.getFrame(), "Done !");
                    }
               
                }
                else{
                    JOptionPane.showMessageDialog(this.myView.getFrame(), "Cancel file writing");
        
                }
                
            } else {
                JOptionPane.showMessageDialog(this.myView.getFrame(), "No file selected.");
            }
        }
    }

    public void deletePack(PackagePanelController packagePanelController) {

        PackageClass toDestroy = packagePanelController.myPackageClass;
        this.myModel.deleteValueByName(toDestroy.getName());
        GUIPackagePanel toRemove = this.myView.deleteValueByName(toDestroy.getName());
        this.myView.getPackagesPanel().remove(toRemove);
        this.myView.getFrame().revalidate();
        this.myView.getFrame().repaint();
    }
}
