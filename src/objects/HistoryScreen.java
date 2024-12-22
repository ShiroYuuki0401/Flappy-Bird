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
    private final Image backgroundImg;
    private final JButton backButton;
    private final JFrame frame;
    private final ArrayList<Double> scores;
    private double bestScore;
    private JTextArea historyTextArea;
    private SoundPlayer soundPlayer;

    public HistoryScreen(JFrame frame) {
        this.frame = frame;
        this.scores = new ArrayList<>();
        soundPlayer = new SoundPlayer();
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);

        backgroundImg = GameImageLoader.loadImage("/resources/flappybirdlightbg.png");

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 24));
        backButton.addActionListener(this);

        setLayout(new BorderLayout());

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        historyTextArea.setBackground(new Color(255, 255, 255, 200)); // White semi-transparent background
        historyTextArea.setForeground(Color.black); // Black text

        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        loadScores();
        displayScores();
    }

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
            Collections.sort(scores, Collections.reverseOrder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayScores() {
        StringBuilder sb = new StringBuilder();
        sb.append("History and Best Score\n\n");
        sb.append("Best Score: ").append(bestScore).append("\n\n");
        for (int i = 0; i < scores.size(); i++) {
            sb.append((i + 1)).append(". Score: ").append(scores.get(i)).append("\n");
        }
        historyTextArea.setText(sb.toString());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        soundPlayer.playClickSound("/resources/click_effect.wav");
        if (e.getSource() == backButton) {
            frame.getContentPane().removeAll();
            StartScreen startScreen = new StartScreen(frame);
            frame.add(startScreen);
            frame.pack();
            startScreen.requestFocus();
            frame.revalidate();
        }
    }
}