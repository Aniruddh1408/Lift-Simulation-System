package com.liftsim.gui;


import com.liftsim.controller.LiftController;

import javax.swing.*;
import java.awt.*;

public class LiftAnimator extends JPanel {
    private LiftController controller;
    private int liftY; // Y-position of lift (pixels)

    private final int FLOOR_HEIGHT = 80; // pixel height per floor
    private final int LIFT_WIDTH = 60;
    private final int LIFT_HEIGHT = 60;

    public LiftAnimator(LiftController controller) {
        this.controller = controller;
        this.liftY = getLiftY(controller.getLift().getCurrentFloor());
    }

    private int getLiftY(int floor) {
        // Floors are counted bottom=0, top=maxFloor
        int maxFloor = controller.getLift().getMaxFloor();
        return (maxFloor - floor) * FLOOR_HEIGHT;
    }

    public void moveLift(int targetFloor) {
        new Thread(() -> {
            int currentFloor = controller.getLift().getCurrentFloor();
            int currentY = getLiftY(currentFloor);
            int targetY = getLiftY(targetFloor);

            int step = (targetY < currentY) ? -5 : 5;

            while ((step < 0 && currentY > targetY) || (step > 0 && currentY < targetY)) {
                currentY += step;
                liftY = currentY;
                repaint();
                try {
                    Thread.sleep(50); // controls speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            liftY = targetY;
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw shaft background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(50, 0, LIFT_WIDTH + 20, (controller.getLift().getMaxFloor() + 1) * FLOOR_HEIGHT);

        // draw lift
        g.setColor(Color.BLUE);
        g.fillRect(60, liftY, LIFT_WIDTH, LIFT_HEIGHT);

        // draw floors
        g.setColor(Color.BLACK);
        for (int i = 0; i <= controller.getLift().getMaxFloor(); i++) {
            int y = getLiftY(i);
            g.drawLine(50, y, 150, y);
            g.drawString("Floor " + i, 10, y + 15);
        }
    }
}

