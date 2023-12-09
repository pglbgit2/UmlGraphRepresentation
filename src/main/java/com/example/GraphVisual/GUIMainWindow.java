package com.example.GraphVisual;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.example.GraphModel.UML_Model.AlreadyExistingStringException;
import com.example.GraphModel.UML_Model.PackageClass;
import com.example.GraphModel.UML_Model.UmlGraph;
import com.example.GraphModel.UML_Model.UniqPacketByName;

public class GUIMainWindow extends UniqPacketByName<GUIPackagePanel> implements Runnable {
    JFrame myFrame;
    GUIMenuBar myMenuBar;
    JPanel mainPanel;
    JPanel contentPanel;
    JPanel Packages;
    GUIRightClickMainMenu mainPopup;
    UmlGraph myModel;
    JPanel myMenuPanel;
    JLabel infoPackage;

    public GUIMainWindow(UmlGraph someModel){
        this.myFrame = new JFrame("UML PROJECT");
	    this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.myFrame.setSize(new Dimension(900, 900));
        this.mainPanel = new JPanel(new BorderLayout());

        this.myMenuBar = new GUIMenuBar();
        this.myMenuPanel = new JPanel();
        this.myMenuPanel.setBackground(Color.GREEN);
        this.myMenuPanel.add(myMenuBar);
        this.mainPanel.setPreferredSize(new Dimension(800,800));
        this.mainPanel.add(myMenuPanel, BorderLayout.NORTH);
        this.contentPanel = new JPanel(new BorderLayout());
        this.infoPackage = new JLabel("Packages");
        this.infoPackage.setPreferredSize(new Dimension(50, 50));
        this.contentPanel.add(infoPackage, BorderLayout.NORTH);
        this.Packages = new JPanel();
        this.contentPanel.add(Packages, BorderLayout.CENTER);
        this.mainPopup = new GUIRightClickMainMenu();
        mainPanel.setComponentPopupMenu(mainPopup);
        this.mainPanel.add(contentPanel, BorderLayout.CENTER);
        this.myFrame.add(this.mainPanel);   
        Dimension d = myFrame.getBounds().getSize();
        mainPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()),(int) Math.round(d.getHeight()*0.9)));
        this.myModel = someModel;    
    }

    public void refreshSizeOnPackages(){
        Dimension opti = getOptimalDimensionForNewPackage();
        for(GUIPackagePanel pack : this.getPackets()){
            pack.setPreferredSize(opti);
            pack.refreshClassSize();
        }
    }

    public void refreshSizeOnPanel(JPanel containerPanel, JPanel contentPanel, double varx, double vary){
        Dimension d = containerPanel.getBounds().getSize();
        contentPanel.setPreferredSize(new Dimension((int) Math.round(d.getWidth()*varx),(int) Math.round(d.getHeight()*vary)));
    }

    public Dimension getOptimalDimensionForNewPackage(){
        int n = this.getSize();
        if(n == 0){
            n = 1;
        }
        Dimension d = Packages.getBounds().getSize();
        double x = d.getWidth();
        double y = d.getHeight();
        return new Dimension((int) Math.round(x*0.9/n), (int) Math.round(y*0.9));
    }

    public JPanel getPackagesPanel(){
        return this.Packages;
    }

    public GUIPackagePanel addPackages(PackageClass somePackage){
        GUIPackagePanel newGuiPackagePanel = new GUIPackagePanel(somePackage.getName());
        
        try {
            this.addValueByName(newGuiPackagePanel);
        } catch (AlreadyExistingStringException e) {
            JOptionPane.showMessageDialog(null, "Error: impossible thing just happened");
            return null;
            //Not supposed to happen because test is already implemented in Controller
        }        
        this.Packages.add(newGuiPackagePanel);
        refreshSizeOnPackages();
        newGuiPackagePanel.setBackground(Color.LIGHT_GRAY); 
        return newGuiPackagePanel;
    }

    public GUIRightClickMainMenu getMainPopup(){
        return this.mainPopup;
    }

    public JFrame getFrame(){
        return this.myFrame;
    }


    public void launchGui(){
        javax.swing.SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {
	    myFrame.setVisible(true);
    }


}
