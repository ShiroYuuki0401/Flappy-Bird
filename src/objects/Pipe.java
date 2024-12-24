package objects;

import java.awt.*;

public class Pipe {

    // X-coordinate of the pipe
    public int x;
    // Y-coordinate of the pipe
    public int y;
    // Width of the pipe
    public final int width;
    // Height of the pipe
    public final int height;
    // Image of the pipe
    public final Image img;
    // Flag to check if the pipe has been passed by the bird
    public boolean passed = false;

    // Constructor to initialize the pipe object
    public Pipe(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
}
