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
        setBackground(new Color(245, 245, 245)); // soft background
    }

    private int getLiftY(int floor) {
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
                    Thread.sleep(20); // smoother movement (faster refresh)
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

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int totalHeight = (controller.getLift().getMaxFloor() + 1) * FLOOR_HEIGHT;

        // draw shaft background with gradient
        GradientPaint shaftGradient = new GradientPaint(50, 0, new Color(220, 220, 220),
                50, totalHeight, new Color(200, 200, 200));
        g2.setPaint(shaftGradient);
        g2.fillRoundRect(50, 0, LIFT_WIDTH + 40, totalHeight, 20, 20);

        // draw lift (rounded with glossy gradient)
        GradientPaint liftGradient = new GradientPaint(60, liftY,
                new Color(100, 149, 237), // cornflower blue top
                60, liftY + LIFT_HEIGHT,
                new Color(65, 105, 225)); // royal blue bottom
        g2.setPaint(liftGradient);
        g2.fillRoundRect(70, liftY, LIFT_WIDTH, LIFT_HEIGHT, 15, 15);

        // outline for lift
        g2.setColor(new Color(30, 30, 30, 150));
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(70, liftY, LIFT_WIDTH, LIFT_HEIGHT, 15, 15);

        // draw floors
        g2.setColor(new Color(50, 50, 50, 180));
        for (int i = 0; i <= controller.getLift().getMaxFloor(); i++) {
            int y = getLiftY(i);
            g2.drawLine(50, y, 200, y);
            g2.drawString("Floor " + i, 10, y + 15);
        }
    }
}
