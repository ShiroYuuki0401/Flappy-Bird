package objects;

import java.io.IOException;
import javax.sound.sampled.*;

public class SoundPlayer {

    // Clip object to play the sound
    private Clip clip;

    // Method to play a sound file in a loop
    public void play(String filePath) {
        try {
            // Load the audio file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            // Loop the clip continuously
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Method to play a click sound effect
    public void playClickSound(String filePath) {
        try {
            // Load the audio file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            Clip clickClip = AudioSystem.getClip();
            clickClip.open(audioStream);
            // Play the clip once
            clickClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Method to stop the currently playing sound
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
