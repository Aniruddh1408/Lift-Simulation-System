package com.liftsim.gui;

import com.liftsim.controller.LiftController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LiftGUI extends JFrame {
    private LiftController controller;
    private LiftAnimator animator;

    public LiftGUI(LiftController controller) {
        this.controller = controller;
        this.animator = new LiftAnimator(controller);

        setTitle("Lift Simulation System");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel to hold lift drawing
        add(animator, BorderLayout.CENTER);

        // Panel with floor buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(controller.getLift().getMaxFloor() + 1, 2));
        // 2 columns: Up + Down button

        for (int i = controller.getLift().getMaxFloor(); i >= 0; i--) {
            int floor = i;

            JButton upButton = new JButton("Up from " + floor);
            JButton downButton = new JButton("Down from " + floor);

            // Up button
            upButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.requestLift(floor, true);  // goingUp = true
                    animator.moveLift(floor);
                }
            });

            // Down button
            downButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.requestLift(floor, false); // goingUp = false
                    animator.moveLift(floor);
                }
            });

            buttonPanel.add(upButton);
            buttonPanel.add(downButton);
        }

        add(buttonPanel, BorderLayout.EAST);
    }

    public void showGUI() {
        setVisible(true);
    }
}
