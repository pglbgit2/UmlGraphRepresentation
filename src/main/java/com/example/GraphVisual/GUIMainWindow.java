package com.example.GraphVisual;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIMainWindow implements Runnable {
    JFrame myFrame;
    GUIMenuBar myMenuBar;
    JPanel mainPanel;
    JPanel Packages;
    ArrayList<GUIPackagePanel> myPackages;
    JLabel defaultLabel;
    GUIRightClickPackageMenu packagePopup;

    public GUIMainWindow(){
        this.myFrame = new JFrame("UML PROJECT");
	    this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.mainPanel = new JPanel();
        this.myMenuBar = new GUIMenuBar();
        this.mainPanel.add(myMenuBar);
        this.Packages = new JPanel();
        this.mainPanel.add(Packages);
        this.packagePopup = new GUIRightClickPackageMenu();
        mainPanel.setComponentPopupMenu(packagePopup);
        Dimension d = myFrame.getBounds().getSize();
        Packages.setPreferredSize(new Dimension((int) Math.round(d.getWidth()),(int) Math.round(d.getHeight()*0.9)));
        this.myFrame.add(this.mainPanel);       
        this.myPackages = new ArrayList<GUIPackagePanel>();
    }


    public void launchGui(){
        javax.swing.SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {
        myFrame.pack();
	    myFrame.setVisible(true);
    }


}
