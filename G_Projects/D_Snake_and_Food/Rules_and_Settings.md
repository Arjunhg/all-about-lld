# Snake and Food Game - Rules and Walkthrough

## 🎮 Game Rules

### Initial Setup
- **Grid Configuration**: The game operates on an N × N grid system
- **Snake Starting Position**: The snake begins at a designated initial position with minimal length
- **Player Controls**: Movement is controlled through directional inputs (Up, Down, Left, Right)

### Core Game Mechanics
- **Movement System**: The snake advances one grid cell per step in the selected direction
- **Growth Mechanism**: When the snake consumes a food item, it increases in length by one segment
- **Food Spawning**: After consumption, new food appears randomly on available grid positions
- **Continuous Gameplay**: The game loop continues until a termination condition is met

### Game Over Conditions
- **Boundary Collision**: Game terminates when the snake hits any grid boundary
- **Self-Collision**: Game ends if the snake's head contacts any part of its own body

---

## 📋 System Requirements Overview

### Grid System
- **Configurable Dimensions**: Supports customizable width × height grid layouts
- **Coordinate System**: Each cell has defined x,y coordinates for precise positioning

### Snake Entity
- **Single Player Control**: One snake entity per game session
- **Dynamic Growth**: Snake length increases with each food consumption
- **Directional Movement**: Responds to player input for direction changes

### Food Management
- **Random Placement**: Food items spawn at available grid positions
- **Consumption Detection**: System detects when snake head occupies same cell as food
- **Automatic Respawning**: New food generates immediately after consumption

### Collision Detection
- **Wall Detection**: Monitors snake position relative to grid boundaries
- **Self-Collision Check**: Tracks snake head position against body segment coordinates
- **Real-time Validation**: Collision checks occur with each movement update

### Scoring System
- **Food-Based Scoring**: Points awarded for each food item consumed
- **Progress Tracking**: Score increments throughout gameplay session
- **Game Statistics**: Maintains record of current score and game duration

### Movement & State Management
- **Turn-Based Updates**: Game state updates with each player input
- **Position Tracking**: Maintains current coordinates of all snake segments
- **Direction Validation**: Prevents invalid moves (e.g., immediate reversals)
