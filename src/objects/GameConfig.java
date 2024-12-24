package objects;

public class GameConfig {

    // Width of the game board in pixels
    public static final int BOARD_WIDTH = 360;
    // Height of the game board in pixels
    public static final int BOARD_HEIGHT = 640;

    // Width of the pipe obstacle in pixels
    public static final int PIPE_WIDTH = 64;
    // Height of the pipe obstacle in pixels
    public static final int PIPE_HEIGHT = 512;
    // Width of the bird character in pixels
    public static final int BIRD_WIDTH = 34;
    // Height of the bird character in pixels
    public static final int BIRD_HEIGHT = 24;

    // Gravity force applied to the bird, affecting its fall speed
    public static final int GRAVITY = 2;
    // Horizontal velocity of the bird, affecting its movement to the left
    public static final int VELOCITY_X = -3;
}
