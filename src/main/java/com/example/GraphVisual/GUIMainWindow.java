package com.example.GraphVisual;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.GraphModel.UML_Model.UniqPacketByName;

public class GUIMainWindow extends UniqPacketByName<GUIPackagePanel> implements Runnable {
    JFrame myFrame;
    GUIMenuBar myMenuBar;
    JPanel mainPanel;
    JPanel Packages;
    JLabel defaultLabel;
    GUIRightClickMainMenu mainPopup;

    public GUIMainWindow(){
        this.myFrame = new JFrame("UML PROJECT");
	    this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.mainPanel = new JPanel();
        this.myMenuBar = new GUIMenuBar();
        this.mainPanel.add(myMenuBar);
        this.Packages = new JPanel();
        this.mainPanel.add(Packages);
        this.mainPopup = new GUIRightClickMainMenu();
        mainPanel.setComponentPopupMenu(mainPopup);
        Dimension d = myFrame.getBounds().getSize();
        Packages.setPreferredSize(new Dimension((int) Math.round(d.getWidth()),(int) Math.round(d.getHeight()*0.9)));
        this.myFrame.add(this.mainPanel);       
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
        myFrame.pack();
	    myFrame.setVisible(true);
    }


}
