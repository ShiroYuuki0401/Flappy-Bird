package objects;

import java.awt.*;
import javax.swing.*;

public class GameImageLoader {

    // Method to load an image from a given resource path
    public static Image loadImage(String resourcePath) {
        try {
            // Attempt to load the image using the resource path
            return new ImageIcon(GameImageLoader.class.getResource(resourcePath)).getImage();
        } catch (NullPointerException e) {
            // Print an error message if the resource is not found
            System.err.println("Resource not found: " + resourcePath);
            // Return null if the image could not be loaded
            return null;
        }
    }
}
