# Flappy-Bird
Project OOP
Welcome to the Flappy Bird game project. This project is a simple implementation of the Flappy Bird game using Java and Swing.

## Folder Structure

The workspace contains the following folders:

- `.vscode`: contains Visual Studio Code settings
  - `settings.json`: configuration for the Java project
- `bin`: the folder where compiled output files are generated
  - `App.class`: The main entry point of the application.
  - `objects/`: Contains compiled class files for game objects and utilities.
  - `resources/`: Contains game resources like images and sounds.
- `lib`: the folder to maintain dependencies
- `src`: the folder to maintain source files
  - `App.java`: The main entry point of the application.
  - `objects/`: Contains source files for game objects and utilities.
  - `resources/`: Contains game resources like images and sounds.
- `README.md`: This file.

## Getting Started

To get started with the project, follow these steps:

1. **Clone the repository**:
    ```sh
    git clone <repository-url>
    cd <repository-directory>
    ```

2. **Open the project in Visual Studio Code**:
    ```sh
    code .
    ```

3. **Build the project**:
    - Open the terminal in Visual Studio Code.
    - Run the following command to compile the project:
      ```sh
      javac -d bin src/**/*.java
      ```

4. **Run the project**:
    - In the terminal, run the following command to start the application:
      ```sh
      java -cp bin App
      ```

## How to Play

1. When you start the application, you will see the start screen with a "Start Game" button.
2. Click the "Start Game" button to begin playing.
3. Use the `SPACE` key to make the bird jump.
4. Avoid the pipes and try to get the highest score possible.
5. If the bird collides with a pipe or the ground, the game will be over, and you can restart by pressing the `SPACE` key again.

## Features

- **Jump Sound**: Plays a sound when the bird jumps.
- **Game Over Sound**: Plays a sound when the game is over.
- **Settings**: Adjust the volume of the game music.

## Dependency Management

The `JAVA PROJECTS` view in Visual Studio Code allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Customizing the Project

If you want to customize the folder structure or other settings, open [settings.json](http://_vscodecontentref_/0) and update the related settings there.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
