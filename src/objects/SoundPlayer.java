package objects;

import java.io.IOException;
import javax.sound.sampled.*;

public class SoundPlayer {
    private Clip clip;

    public void play(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playClickSound(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            Clip clickClip = AudioSystem.getClip();
            clickClip.open(audioStream);
            clickClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}