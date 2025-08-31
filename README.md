Lift Simulation System
Overview:

The Lift Simulation System is a Java-based project that simulates the working of elevators in a multi-floor building. The system demonstrates real-time lift movement, floor requests, and interactive GUI-based control. This project is ideal for learning object-oriented programming concepts, GUI design with Java Swing, and event-driven programming.

Features:
1. Multi-floor support: Simulates buildings with floors ranging from -1 (basement) to 13th floor.
2. Lift movement simulation: Handles multiple lifts, moving up or down according to requests.
3. GUI-based interface: Interactive graphical interface showing lift positions and floor buttons.
4. Floor call buttons: Each floor has buttons to request lifts.
5. Lift controller: Efficiently manages lift assignments based on floor requests.
6. Animation: Smooth movement of lifts between floors.
7. Extensible design: Easy to add more lifts, floors, or features.

Project Structure:
LiftSimulation/
│
├── src/
│   ├── com/liftsim/gui/           # GUI classes (Lift view, floor panels)
│   ├── com/liftsim/controller/    # LiftController class for managing lift logic
│   ├── com/liftsim/animator/      # Handles smooth lift animations
│   ├── com/liftsim/floor/         # Floor class, including buttons and requests
│   └── com/liftsim/lift/          # Lift class for lift behavior and state
├── README.md                       # Project documentation                        

Technologies Used:
1. Java SE 8+
2. Swing GUI framework
3. Object-Oriented Programming (OOP) principles
4. Event-driven programming

How It Works:
1. The LiftController monitors requests from floors.
2. Users can press up/down buttons on each floor to request a lift.
3. The controller assigns the nearest available lift to the requested floor.
4. The lift moves, animated in the GUI, to the requested floor.
5. Lifts can handle multiple requests efficiently using a queue system.

Author:
Aniruddh S.
GitHub: https://github.com/Aniruddh1408
