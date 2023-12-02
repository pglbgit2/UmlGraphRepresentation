package com.example;

import com.example.GraphController.MainWindowController;
import com.example.GraphModel.UML_Model.UmlGraph;
import com.example.GraphVisual.GUIMainWindow;

public class Main {
    public static void main(String[] args) {
        UmlGraph myModel = new UmlGraph();
        GUIMainWindow myView = new GUIMainWindow();
        MainWindowController myController = new MainWindowController(myModel, myView);
        myView.launchGui();
    }
}