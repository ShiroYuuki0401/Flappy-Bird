# Flappy Bird Game

The Flappy Bird Game is a modern version of the classic mobile game, recreating the simple yet addictive experience with enhanced graphics and additional features. In Flappy Bird, the goal is to navigate a bird through a series of pipes without hitting them, earning points for each set of pipes successfully passed.

## Key Components

1. **Main Application ([`App.java`](src/App.java))**:
   - The entry point of the application.
   - Initializes the main game window and displays the start screen.

2. **Start Screen ([`StartScreen.java`](src/objects/StartScreen.java))**:
   - Displays the initial screen with options to start the game or view the history of scores.
   - Contains buttons to navigate to the game or history screen.

3. **Game Logic ([`FlappyBird.java`](src/objects/FlappyBird.java))**:
   - Implements the main game logic and rendering.
   - Handles user input, game physics, collision detection, and scoring.
   - Manages the game loop and timers for game updates and pipe placement.

4. **Game Configuration ([`GameConfig.java`](src/objects/GameConfig.java))**:
   - Contains constants for game settings such as board dimensions, pipe dimensions, bird dimensions, gravity, and velocity.

5. **Game Over Screen ([`GameOverScreen.java`](src/objects/GameOverScreen.java))**:
   - Displays the game over screen with the final score.
   - Provides options to restart the game or return to the start screen.

6. **History Screen ([`HistoryScreen.java`](src/objects/HistoryScreen.java))**:
   - Displays the history of scores and the best score.
   - Reads scores from a text file and displays them in a sorted order.

7. **Bird ([`Bird.java`](src/objects/Bird.java))**:
   - Represents the bird character in the game.
   - Contains properties for position and dimensions.

8. **Pipe ([`Pipe.java`](src/objects/Pipe.java))**:
   - Represents the pipes that the bird must avoid.
   - Contains properties for position, dimensions, and image.

9. **Image Loader ([`GameImageLoader.java`](src/objects/GameImageLoader.java))**:
   - Loads images from resources for use in the game.

10. **Sound Player ([`SoundPlayer.java`](src/objects/SoundPlayer.java))**:
    - Plays background music and sound effects during the game.

## Features

- **Start Screen**: Displays a start button to begin the game and a history button to view past scores.
- **Game Play**: 
  - The bird moves upward when the space key is pressed and falls due to gravity.
  - Pipes appear from the right side of the screen and move left.
  - The player must navigate the bird through the gaps between the pipes.
  - The game ends if the bird collides with a pipe or the ground.
- **Scoring**: 
  - The score increases as the bird successfully passes through pipes.
  - The score is saved to a history file upon game over.
- **Game Over Screen**: Displays the final score and provides options to restart the game or return to the start screen.
- **History Screen**: Displays a list of past scores and the best score.

## How to Play

1. **Start the Game**: Click the "Start Game" button on the start screen.
2. **Control the Bird**: Press the `SPACE` key to make the bird jump.
3. **Avoid Obstacles**: Navigate the bird through the gaps between the pipes.
4. **Pause/Resume**: Press the `P` key to pause or resume the game.
5. **Game Over**: If the bird collides with a pipe or the ground, the game ends, and the game over screen is displayed.
6. **View History**: Click the "History" button on the start screen to view past scores.

## Folder Structure

- **src**: Contains the source code files.
- **bin**: Contains the compiled class files.
- **lib**: Contains dependencies.
- **resources**: Contains game assets such as images and sound files.
- **.vscode**: Contains Visual Studio Code settings.

## Build and Run

To build and run the project, follow these steps:

1. **Build the Project**:
   ```sh
   javac -d bin src/**/*.java
