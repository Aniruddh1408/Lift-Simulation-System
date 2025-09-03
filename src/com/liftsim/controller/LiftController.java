package com.liftsim.controller;

import com.liftsim.model.Lift;
import javax.swing.Timer;
import java.util.*;


public class LiftController {
    private static final int TOP_FLOOR = 7;
    private static final int BOTTOM_FLOOR = -1;

    // Reference to the lift being controlled
    private Lift lift;

    // Queues to track up and down requests separately
    private final TreeSet<Integer> upQueue = new TreeSet<>();
    private final TreeSet<Integer> downQueue = new TreeSet<>(Collections.reverseOrder());

    // Timer to simulate periodic checking and moving the lift
    private Timer timer;

    public LiftController(Lift lift) {
        this.lift = lift;

        // Timer fires every 2 seconds to simulate lift moving and checking for next request
        timer = new Timer(2000, e -> processNextRequest());
        timer.start();
    }

    // Called when user presses UP or DOWN button on a floor
    public void requestLift(int floor, boolean goingUp) {
        if (goingUp) {
            upQueue.add(floor);
        } else {
            downQueue.add(floor);
        }
    }

    // Called to process next pending request
    private void processNextRequest() {
        int currentFloor = lift.getCurrentFloor();

        // 1. If there are UP requests and lift below top
        if (!upQueue.isEmpty() && currentFloor < TOP_FLOOR) {
            Integer nextUp = upQueue.ceiling(currentFloor + 1); // next upward request
            if (nextUp != null) {
                moveLiftTo(nextUp);
                upQueue.remove(nextUp);
                return;
            }
        }

        // 2. If there are DOWN requests and lift above bottom
        if (!downQueue.isEmpty() && currentFloor > BOTTOM_FLOOR) {
            Integer nextDown = downQueue.floor(currentFloor - 1); // next downward request
            if (nextDown != null) {
                moveLiftTo(nextDown);
                downQueue.remove(nextDown);
                return;
            }
        }

        // 3. If no immediate request, check other direction
        if (!upQueue.isEmpty()) {
            Integer anyUp = upQueue.first();
            moveLiftTo(anyUp);
            upQueue.remove(anyUp);
        } else if (!downQueue.isEmpty()) {
            Integer anyDown = downQueue.first();
            moveLiftTo(anyDown);
            downQueue.remove(anyDown);
        }
    }

    // Move lift to a specific floor
    private void moveLiftTo(int floor) {
        lift.moveToFloor(floor);
        lift.setStatus("Moving to Floor " + floor);
    }

    // Optionally allow canceling or emergency stop in future
    public void stopController() {
        timer.stop();
    }

    public Lift getLift() {
        return lift;
    }
}
