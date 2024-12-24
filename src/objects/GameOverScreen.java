package objects;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameOverScreen extends JPanel implements ActionListener {

    // Background image for the game over screen
    private final Image backgroundImg;
    // Button to restart the game
    private final JButton restartButton;
    // Button to go back to the home screen
    private final JButton homeButton;
    // Reference to the main game frame
    private final JFrame frame;
    // Player's score to be displayed
    private final double score;
    // Sound player for playing sound effects
    private final SoundPlayer soundPlayer;

    // Constructor to initialize the game over screen
    public GameOverScreen(JFrame frame, double score) {
        this.frame = frame;
        this.score = score;
        this.soundPlayer = new SoundPlayer();
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);

        // Load the background image
        backgroundImg = GameImageLoader.loadImage("/resources/flappybirddarkbg.png");

        // Initialize the restart button
        restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 24));
        restartButton.addActionListener(this);

        // Initialize the home button
        homeButton = new JButton("Home");
        homeButton.setFont(new Font("Arial", Font.PLAIN, 24));
        homeButton.addActionListener(this);

        // Set layout and add buttons to the panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(restartButton, gbc);
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 2;
        add(homeButton, gbc);
    }

    // Method to paint the game over screen components
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
        // Set the color and font for the text
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        // Draw the "Game Over" text and the player's score
        g.drawString("Game Over", 100, 100);
        g.drawString("Score: " + (int) score, 100, 150);
    }

    // Method to handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        // Play click sound effect
        soundPlayer.playClickSound("/resources/click_effect.wav");
        if (e.getSource() == restartButton) {
            // Restart the game
            frame.getContentPane().removeAll();
            FlappyBird flappyBird = new FlappyBird();
            flappyBird.initializeListeners();
            frame.add(flappyBird);
            frame.pack();
            flappyBird.requestFocus();
            frame.revalidate();
        } else if (e.getSource() == homeButton) {
            // Go back to the home screen
            frame.getContentPane().removeAll();
            StartScreen startScreen = new StartScreen(frame);
            frame.add(startScreen);
            frame.pack();
            startScreen.requestFocus();
            frame.revalidate();
        }
    }
}
