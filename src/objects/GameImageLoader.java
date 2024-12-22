package objects;

import java.awt.*;
import javax.swing.*;

public class GameImageLoader {
    public static Image loadImage(String resourcePath) {
        try {
            return new ImageIcon(GameImageLoader.class.getResource(resourcePath)).getImage();
        } catch (NullPointerException e) {
            System.err.println("Resource not found: " + resourcePath);
            return null;
        }
    }    
}
