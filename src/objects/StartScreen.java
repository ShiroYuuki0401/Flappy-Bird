package objects;

// Import necessary packages
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartScreen extends JPanel implements ActionListener {

    // Declare instance variables for images, buttons, frame, and sound player
    private final Image backgroundImg;
    private final Image flappybirdImg;
    private final JButton startButton;
    private final JButton historyButton;
    private final JFrame frame;
    private final SoundPlayer soundPlayer;

    // Constructor to initialize the start screen
    public StartScreen(JFrame frame) {
        this.frame = frame; // Set the frame
        this.soundPlayer = new SoundPlayer(); // Initialize the sound player
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT)); // Set the preferred size of the panel
        setFocusable(true); // Make the panel focusable

        // Load images for the background and the Flappy Bird logo
        backgroundImg = GameImageLoader.loadImage("/resources/flappybirdlightbg.png");
        flappybirdImg = GameImageLoader.loadImage("/resources/flappybird.png");

        // Initialize the start button with text and font, and add action listener
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.addActionListener(this);

        // Initialize the history button with text and font, and add action listener
        historyButton = new JButton("History");
        historyButton.setFont(new Font("Arial", Font.PLAIN, 24));
        historyButton.addActionListener(this);

        // Set the layout of the panel to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Set grid x position
        gbc.gridy = 0; // Set grid y position
        add(startButton, gbc); // Add start button to the panel
        gbc.insets = new Insets(10, 0, 10, 0); // Set insets for spacing
        gbc.gridy = 1; // Set grid y position for the history button
        add(historyButton, gbc); // Add history button to the panel
    }

    // Override paintComponent to draw custom graphics
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
        // Draw the Flappy Bird logo
        g.drawImage(flappybirdImg, GameConfig.BOARD_WIDTH / 4, GameConfig.BOARD_HEIGHT / 7, 408 / 2, 288 / 2, null);
    }

    // Override actionPerformed to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        soundPlayer.playClickSound("/resources/click_effect.wav"); // Play click sound
        if (e.getSource() == startButton) { // If start button is clicked
            frame.getContentPane().removeAll(); // Remove all components from the frame
            FlappyBird flappyBird = new FlappyBird(); // Create a new FlappyBird instance
            flappyBird.initializeListeners(); // Initialize listeners for the game
            frame.add(flappyBird); // Add the game to the frame
            frame.pack(); // Pack the frame
            flappyBird.requestFocus(); // Request focus for the game
            frame.revalidate(); // Revalidate the frame
        } else if (e.getSource() == historyButton) { // If history button is clicked
            frame.getContentPane().removeAll(); // Remove all components from the frame
            HistoryScreen historyScreen = new HistoryScreen(frame); // Create a new HistoryScreen instance
            frame.add(historyScreen); // Add the history screen to the frame
            frame.pack(); // Pack the frame
            historyScreen.requestFocus(); // Request focus for the history screen
            frame.revalidate(); // Revalidate the frame
        }
    }
}
