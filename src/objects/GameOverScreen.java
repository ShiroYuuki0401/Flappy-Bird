package objects;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameOverScreen extends JPanel implements ActionListener {
    private final Image backgroundImg;
    private final JButton restartButton;
    private final JButton homeButton;
    private final JFrame frame;
    private final double score;
    private final SoundPlayer soundPlayer;

    public GameOverScreen(JFrame frame, double score) {
        this.frame = frame;
        this.score = score;
        this.soundPlayer = new SoundPlayer();
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);

        backgroundImg = GameImageLoader.loadImage("/resources/flappybirddarkbg.png");

        restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 24));
        restartButton.addActionListener(this);

        homeButton = new JButton("Home");
        homeButton.setFont(new Font("Arial", Font.PLAIN, 24));
        homeButton.addActionListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(restartButton, gbc);
        gbc.insets = new Insets(10, 0, 10, 0); 
        gbc.gridy = 2;
        add(homeButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        g.drawString("Game Over", 100, 100);
        g.drawString("Score: " + (int) score, 100, 150);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        soundPlayer.playClickSound("/resources/click_effect.wav");
        if (e.getSource() == restartButton) {
            frame.getContentPane().removeAll();
            FlappyBird flappyBird = new FlappyBird();
            flappyBird.initializeListeners();
            frame.add(flappyBird);
            frame.pack();
            flappyBird.requestFocus();
            frame.revalidate();
        } else if (e.getSource() == homeButton) {
            frame.getContentPane().removeAll();
            StartScreen startScreen = new StartScreen(frame);
            frame.add(startScreen);
            frame.pack();
            startScreen.requestFocus();
            frame.revalidate();
        }
    }
}