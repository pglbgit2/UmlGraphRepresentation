package com.example.GraphVisual;


import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;

import javax.swing.table.DefaultTableModel;

public class GUIClassElementsPanel extends JPanel {

    JTable myTable;
    ArrayList<JLabel> columnsNames;
    DefaultTableModel myModel;
    JPanel namePanel;
    JPanel tabPanel;
    int nbRows;
    ArrayList<String> columns;
    JScrollPane myPane;

    public GUIClassElementsPanel(String type, ArrayList<String> columns){
        super(new GridLayout(2, 1));
        this.myModel = new DefaultTableModel(columns.toArray(), 0);
        this.myTable = new JTable(myModel);
        this.nbRows = 0;
        this.columnsNames = new ArrayList<JLabel>();
        for(int i = 0; i < columns.size() ; i++){
            this.columnsNames.add(new JLabel(columns.get(i)));
        }
        this.columns = columns;
        this.namePanel = new JPanel(new GridLayout(1, this.columnsNames.size()));
        this.tabPanel = new JPanel(new GridLayout(1,1));
        for(JLabel lab : this.columnsNames){
            this.namePanel.add(lab);
        }
        this.tabPanel.add(this.myTable);
        this.add(namePanel);
        this.myPane = new JScrollPane(tabPanel); 
        this.add(myPane);
    }

    public void refresh(){
        this.tabPanel.remove(myTable);
        this.tabPanel.add(myTable);
    }

    public void addLine(){
        this.myModel.addRow(new Object[this.myModel.getColumnCount()]);
    }


    public JTable getTable(){
        return this.myTable;
    }

    public DefaultTableModel getTableModel(){
        return this.myModel;
    }
}