package objects;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private final Image backgroundImg;
    private final Image darkBackgroundImg;
    private final Image birdImg;
    private final Image topPipeImg;
    private final Image bottomPipeImg;

    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Timer gameLoop;
    private Timer placePipeTimer;
    private final SoundPlayer soundPlayer;

    private int velocityY = 0;
    private boolean gameOver = false;
    private boolean paused = false;
    private double score = 0;

    public FlappyBird() {
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);
    
        backgroundImg = GameImageLoader.loadImage("/resources/flappybirdlightbg.png");
        darkBackgroundImg = GameImageLoader.loadImage("/resources/flappybirddarkbg_1.jpg");
        birdImg = GameImageLoader.loadImage("/resources/flappybird.png");
        topPipeImg = GameImageLoader.loadImage("/resources/toppipe.png");
        bottomPipeImg = GameImageLoader.loadImage("/resources/bottompipe.png");
    
        soundPlayer = new SoundPlayer();
        soundPlayer.play("/resources/game_background.wav");
    
        initializeGame();
    }
    
    public void initializeListeners() {
        addKeyListener(this);
    }
    

    private void initializeGame() {
        bird = new Bird(GameConfig.BOARD_WIDTH / 8, GameConfig.BOARD_WIDTH / 2, GameConfig.BIRD_WIDTH, GameConfig.BIRD_HEIGHT);
        pipes = new ArrayList<>();

        if (placePipeTimer != null) {
            placePipeTimer.stop();
        }
        placePipeTimer = new Timer(1500, event -> placePipes());
        placePipeTimer.start();

        if (gameLoop != null) {
            gameLoop.stop();
        }
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    private void placePipes() {
        int randomPipeY = (int) (-GameConfig.PIPE_HEIGHT / 4 - Math.random() * (GameConfig.PIPE_HEIGHT / 2));
        int openingSpace = GameConfig.BOARD_HEIGHT / 4;

        pipes.add(new Pipe(GameConfig.BOARD_WIDTH, randomPipeY, GameConfig.PIPE_WIDTH, GameConfig.PIPE_HEIGHT, topPipeImg));
        pipes.add(new Pipe(GameConfig.BOARD_WIDTH, randomPipeY + GameConfig.PIPE_HEIGHT + openingSpace, GameConfig.PIPE_WIDTH, GameConfig.PIPE_HEIGHT, bottomPipeImg));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if ((int) score / 10 % 2 == 1) {
            g.drawImage(darkBackgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
        } else {
            g.drawImage(backgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
        }
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + (int) score, 10, 35);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
        if (paused) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Paused", GameConfig.BOARD_WIDTH / 2 - 80, GameConfig.BOARD_HEIGHT / 2);
        }
    }

    private void move() {
        velocityY += GameConfig.GRAVITY;
        bird.y += 0.75 * velocityY;
        bird.y = Math.max(bird.y, 0);

        for (Pipe pipe : pipes) {
            pipe.x += GameConfig.VELOCITY_X;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > GameConfig.BOARD_HEIGHT) {
            gameOver = true;
        }
    }

    private boolean collision(Bird bird, Pipe pipe) {
        return bird.x < pipe.x + pipe.width &&
               bird.x + bird.width > pipe.x &&
               bird.y < pipe.y + pipe.height &&
               bird.y + bird.height > pipe.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !paused) {
            move();
            repaint();
        } else if (gameOver) {
            placePipeTimer.stop();
            gameLoop.stop();
            soundPlayer.stop();
            soundPlayer.playClickSound("/resources/game-over.wav"); // Play game over sound
            saveScoreToHistory();
            showGameOverScreen();
        }
    }

    private void saveScoreToHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/objects/history.txt", true))) {
            writer.write(String.valueOf(score));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showGameOverScreen() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        GameOverScreen gameOverScreen = new GameOverScreen(frame, score);
        frame.add(gameOverScreen);
        frame.pack();
        gameOverScreen.requestFocus();
        frame.revalidate();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                restartGame();
            } else {
                velocityY = -20;
                soundPlayer.playClickSound("/resources/jump.wav");
            }
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            paused = !paused;
            if (paused) {
                placePipeTimer.stop();
                gameLoop.stop();
                soundPlayer.stop();
            } else {
                placePipeTimer.start();
                gameLoop.start();
                soundPlayer.play("/resources/game_background.wav");
            }
            repaint();
        }
    }

    private void restartGame() {
        initializeGame();
        gameOver = false;
        score = 0;
        velocityY = 0;
        soundPlayer.play("/resources/game_background.wav");
    }

    @Override
    public void keyTyped(KeyEvent e) {} 

    @Override
    public void keyReleased(KeyEvent e) {}
}