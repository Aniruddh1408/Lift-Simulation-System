package com.liftsim.model;

/**
 * The Lift class represents a basic elevator model.
 * It supports moving to floors above ground (positive),
 * ground floor (0), and basement floors (negative numbers).
 */
public class Lift {
    private int currentFloor;   // e.g. -2 for B2, 0 for Ground, 3 for 3rd floor
    private boolean movingUp;
    private boolean isMoving;
    private final int MIN_FLOOR = -1;
    private final int MAX_FLOOR = 13;
    // Constructor sets lift at ground floor (0)
    public Lift() {
        this.currentFloor = 0;   // Default starting point: Ground Floor
        this.movingUp = true;
        this.isMoving = false;
    }

    // Get the current floor the lift is on
    public int getCurrentFloor() {
        return currentFloor;
    }

    // Returns true if lift is currently moving upwards
    public boolean isMovingUp() {
        return movingUp;
    }

    // Returns true if lift is in motion
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Moves the lift to the specified target floor.
     * Supports both positive (above ground) and negative (basement) floors.
     *
     * @param targetFloor the destination floor to move to
     */
    public void moveToFloor(int targetFloor) {
        if (targetFloor < MIN_FLOOR || targetFloor > MAX_FLOOR) {
            System.out.println("Invalid floor: " + targetFloor);
            return;
        }
        isMoving = true;

        while (currentFloor != targetFloor) {
            if (currentFloor < targetFloor) {
                currentFloor++;     // Going up
                movingUp = true;
            } else {
                currentFloor--;     // Going down (includes basement floors)
                movingUp = false;
            }

            // Simulate floor-to-floor movement time
            try {
                Thread.sleep(500);  // 500ms per floor
            } catch (InterruptedException e) {
                e.printStackTrace();  // Handle interruption safely
            }
        }

        isMoving = false; // Stop when destination reached
    }
}
