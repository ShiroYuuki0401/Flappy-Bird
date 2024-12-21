import javax.swing.*;
import objects.StartScreen;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartScreen startScreen = new StartScreen(frame);
        frame.add(startScreen);
        frame.pack();
        frame.setVisible(true);
    }
}