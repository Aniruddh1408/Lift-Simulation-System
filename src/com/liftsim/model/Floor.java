package com.liftsim.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents a single floor in the building, with visual components.
 * Includes buttons for Up and Down requests, and a floor label.
 */
public class Floor extends JPanel {
    private int floorNumber;
    private boolean upRequested;
    private boolean downRequested;

    private JButton upButton;
    private JButton downButton;
    private JLabel floorLabel;

    /**
     * Constructor for a Floor panel.
     * Adds label and buttons depending on floor number.
     *
     * @param floorNumber The number of the floor (e.g., 0 for Ground, -1 for B1)
     */
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.upRequested = false;
        this.downRequested = false;

        // Set layout and background
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        // Create and add floor label
        floorLabel = new JLabel(getFloorName());
        floorLabel.setPreferredSize(new Dimension(60, 30));
        add(floorLabel);

        // Only add UP button if not the topmost floor
        if (floorNumber < 5) {
            upButton = new JButton("Up");
            add(upButton);
        }

        // Only add DOWN button if not the bottommost floor
        if (floorNumber > -2) {
            downButton = new JButton("Down");
            add(downButton);
        }
    }

    // Utility to convert number to label like "B1", "Ground", "1st"
    private String getFloorName() {
        if (floorNumber == 0) return "Ground";
        if (floorNumber < 0) return "B" + Math.abs(floorNumber);
        return floorNumber + "F";
    }

    // Set action for Up button
    public void setUpAction(ActionListener listener) {
        if (upButton != null)
            upButton.addActionListener(listener);
    }

    // Set action for Down button
    public void setDownAction(ActionListener listener) {
        if (downButton != null)
            downButton.addActionListener(listener);
    }

    // Getters
    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isUpRequested() {
        return upRequested;
    }

    public boolean isDownRequested() {
        return downRequested;
    }

    // Setters (for controller to clear request after serving)
    public void requestUp() {
        upRequested = true;
        if (upButton != null) upButton.setBackground(Color.YELLOW); // Visual cue
    }

    public void requestDown() {
        downRequested = true;
        if (downButton != null) downButton.setBackground(Color.YELLOW);
    }

    public void clearUpRequest() {
        upRequested = false;
        if (upButton != null) upButton.setBackground(null);
    }

    public void clearDownRequest() {
        downRequested = false;
        if (downButton != null) downButton.setBackground(null);
    }
}
