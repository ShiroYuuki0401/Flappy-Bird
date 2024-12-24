package objects;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class HistoryScreen extends JPanel implements ActionListener {

    // Background image for the history screen
    private final Image backgroundImg;
    // Button to go back to the previous screen
    private final JButton backButton;
    // Reference to the main game frame
    private final JFrame frame;
    // List to store the scores
    private final ArrayList<Double> scores;
    // Variable to store the best score
    private double bestScore;
    // Text area to display the scores
    private JTextArea historyTextArea;
    // Sound player for playing sound effects
    private SoundPlayer soundPlayer;

    // Constructor to initialize the history screen
    public HistoryScreen(JFrame frame) {
        this.frame = frame;
        this.scores = new ArrayList<>();
        soundPlayer = new SoundPlayer();
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);

        // Load the background image
        backgroundImg = GameImageLoader.loadImage("/resources/flappybirdlightbg.png");

        // Initialize the back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 24));
        backButton.addActionListener(this);

        // Set layout and add components to the panel
        setLayout(new BorderLayout());

        // Initialize the text area for displaying scores
        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        historyTextArea.setBackground(new Color(255, 255, 255, 200)); // White semi-transparent background
        historyTextArea.setForeground(Color.black); // Black text

        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        // Load and display the scores
        loadScores();
        displayScores();
    }

    // Method to load scores from a file
    private void loadScores() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/objects/history.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                double score = Double.parseDouble(line);
                scores.add(score);
                if (score > bestScore) {
                    bestScore = score;
                }
            }
            // Sort the scores in descending order
            Collections.sort(scores, Collections.reverseOrder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display the scores in the text area
    private void displayScores() {
        StringBuilder sb = new StringBuilder();
        sb.append("History and Best Score\n\n");
        sb.append("Best Score: ").append(bestScore).append("\n\n");
        for (int i = 0; i < scores.size(); i++) {
            sb.append((i + 1)).append(". Score: ").append(scores.get(i)).append("\n");
        }
        historyTextArea.setText(sb.toString());
    }

    // Method to paint the history screen components
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
    }

    // Method to handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        // Play click sound effect
        soundPlayer.playClickSound("/resources/click_effect.wav");
        if (e.getSource() == backButton) {
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
