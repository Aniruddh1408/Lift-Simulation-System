package com.liftsim;

import com.liftsim.controller.LiftController;
import com.liftsim.gui.LiftGUI;
import com.liftsim.model.Lift;

import javax.swing.*;

public class LiftSystem {
    public static void main(String[] args) {
        // Run GUI in Swing's Event Dispatch Thread (best practice for Swing apps)
        SwingUtilities.invokeLater(() -> {
            Lift lift = new Lift();                      // Create lift model
            LiftController controller = new LiftController(lift); // Controller
            LiftGUI gui = new LiftGUI(controller);       // GUI
            gui.showGUI();                               // Display
        });
    }
}
