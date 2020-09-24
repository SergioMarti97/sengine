package engine.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class represents a sound, or an audio clip
 * to be able to be reproduced within an application
 *
 This is copied from the 2D Java Game Engine from the channel Majoolwip
 * GitHub: https://github.com/Majoolwip
 * Youtube channel: https://www.youtube.com/channel/UCYdJWlQWeuhDZicBbxM0-mg
 * Youtube video where he explains the Renderer class: "2D Java Game Engine #10 : SoundClip"
 * https://www.youtube.com/watch?v=bNq1UxL2cmE&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&index=10
 *
 * @class: SoundClip
 * @autor: Sergio Martí Torregrosa
 * @date: 2020-07-06
 */
public class SoundClip {

    private Clip clip = null;

    private FloatControl gainControl;

    /**
     * Constructor
     *
     * @param path absolutely or relative path where is the original clip audio
     */
    public SoundClip(String path) {
        try {
            InputStream audioSrc = SoundClip.class.getResourceAsStream(path);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
            gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch ( UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows the clip to play once.
     */
    public void play() {
        if ( clip == null ) {
            return;
        }
        stop();
        clip.setFramePosition(0);
        while ( !clip.isRunning() ) {
            clip.start();
        }
    }

    /**
     * This method makes the sound clip stop.
     */
    public void stop() {
        if ( clip.isRunning() ) {
            clip.stop();
        }
    }

    /**
     * This method closes the sound clip. Previously,
     * calls the <method> stop </method> method of this same class
     * in case the clip is playing.
     */
    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    /**
     * This method allows the sound clip to repeat itself.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    /**
     * Change the volume at which the clip is played.
     */
    public void setVolume(float value) {
        gainControl.setValue(value);
    }

    /**
     * Whether the clip is playing or not.
     */
    public boolean isRunning() {
        return clip.isRunning();
    }

}
