package com.example.GraphVisual.UtilsFromStackOverFlow;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraggableHelper {

    private Point initialMouseLocation;

    private Point initialComponentLocation;

    public DraggableHelper(Component component) {

        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialMouseLocation = e.getLocationOnScreen();
                initialComponentLocation = component.getLocation();
            }
        });

        component.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point finalMouseLocation = e.getLocationOnScreen();
                int deltaX = finalMouseLocation.x - initialMouseLocation.x;
                int deltaY = finalMouseLocation.y - initialMouseLocation.y;
                Point newLocation = new Point(initialComponentLocation.x + deltaX, initialComponentLocation.y + deltaY);
                component.setLocation(newLocation);
            }
        });
    }

}