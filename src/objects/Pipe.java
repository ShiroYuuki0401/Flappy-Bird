package objects;

import java.awt.*;

public class Pipe {
    public int x;
    public int y;
    public final int width;
    public final int height;
    public final Image img;
    public boolean passed = false;

    public Pipe(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
}
