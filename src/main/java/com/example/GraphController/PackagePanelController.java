package com.example.GraphController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import com.example.GraphModel.UML_Model.PackageClass;
import com.example.GraphVisual.GUIPackagePanel;
import com.example.GraphVisual.GUIRightClickPackageMenu;

public class PackagePanelController implements ActionListener{

    GUIPackagePanel myGuiPackagePanel;
    PackageClass myPackageClass;

    public PackagePanelController(GUIPackagePanel myPackageView, PackageClass myPackageModel){
        this.myGuiPackagePanel = myPackageView;
        this.myPackageClass = myPackageModel;
        GUIRightClickPackageMenu GUIRCPM =  new GUIRightClickPackageMenu();
        this.myGuiPackagePanel.setComponentPopupMenu(GUIRCPM);
        JMenuItem addClassItem = GUIRCPM.getAddClass();
        addClassItem.setActionCommand("addClass");
        addClassItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        System.out.println("TEST");
    }
    
}
