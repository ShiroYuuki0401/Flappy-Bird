import javax.swing.*;
import objects.StartScreen;

public class App {
    public static void main(String[] args) throws Exception {
        // Define the width and height of the game board
        int boardWidth = 360;
        int boardHeight = 640;

        // Create the main game frame
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setResizable(false); // Prevent resizing the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the frame is closed

        // Create and add the start screen to the frame
        StartScreen startScreen = new StartScreen(frame);
        frame.add(startScreen);
        frame.pack();
        frame.setVisible(true); // Make the frame visible
    }
}