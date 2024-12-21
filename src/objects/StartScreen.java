package objects;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartScreen extends JPanel implements ActionListener {
    private final Image backgroundImg;
    private final Image flappybirdImg;
    private final JButton startButton;
    private final JButton historyButton;
    private final JFrame frame;
    private final SoundPlayer soundPlayer;

    public StartScreen(JFrame frame) {
        this.frame = frame;
        this.soundPlayer = new SoundPlayer();
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);

        backgroundImg = GameImageLoader.loadImage("/resources/flappybirdlightbg.png");
        flappybirdImg = GameImageLoader.loadImage("/resources/flappybird.png");

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.addActionListener(this);

        historyButton = new JButton("History");
        historyButton.setFont(new Font("Arial", Font.PLAIN, 24));
        historyButton.addActionListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(startButton, gbc);
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 1;
        add(historyButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
        g.drawImage(flappybirdImg, GameConfig.BOARD_WIDTH/4, GameConfig.BOARD_HEIGHT/7, 408/2, 288/2, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        soundPlayer.playClickSound("/resources/click_effect.wav");
        if (e.getSource() == startButton) {
            frame.getContentPane().removeAll();
            FlappyBird flappyBird = new FlappyBird();
            flappyBird.initializeListeners();
            frame.add(flappyBird);
            frame.pack();
            flappyBird.requestFocus();
            frame.revalidate();
        } else if (e.getSource() == historyButton) {
            frame.getContentPane().removeAll();
            HistoryScreen historyScreen = new HistoryScreen(frame);
            frame.add(historyScreen);
            frame.pack();
            historyScreen.requestFocus();
            frame.revalidate();
        }
    }
}