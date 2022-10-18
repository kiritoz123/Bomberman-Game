package oop.bomberman.Sound;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

import static oop.bomberman.BombermanGame.bomberman;
import static oop.bomberman.BombermanGame.isPause;

public class SoundPlay extends JFrame {
    public static Clip title_screen;
    public static Clip bomb_explosion;
    public static Clip died;
    public static Clip put_bomb;
    public static boolean is_sound_died;
    public static boolean is_sound_title = false;


    public SoundPlay(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL url = this.getClass().getClassLoader().getResource(name);
            assert url != null;
            AudioInputStream audio_input = AudioSystem.getAudioInputStream(url);
            if (sound.equals("title")) {
                title_screen = AudioSystem.getClip();
                title_screen.open(audio_input);
                FloatControl gainControl = (FloatControl) title_screen.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f);
                title_screen.loop(10);
            }
            if (sound.equals("explosion")) {
                bomb_explosion = AudioSystem.getClip();
                bomb_explosion.open(audio_input);
                FloatControl gainControl = (FloatControl) bomb_explosion.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-5.0f);
                bomb_explosion.start();
            }
            if (sound.equals("died")) {
                died = AudioSystem.getClip();
                died.open(audio_input);
                FloatControl gainControl = (FloatControl) died.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-1.0f);
                died.start();
            }
            if (sound.equals("putBomb")) {
                put_bomb = AudioSystem.getClip();
                put_bomb.open(audio_input);
                FloatControl gainControl = (FloatControl) put_bomb.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(+6.0206f);
                put_bomb.start();
            }
            if (sound.equals("default")) {
                Clip clip = AudioSystem.getClip();
                clip.open(audio_input);
                clip.start();
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateSound() {
        if (!is_sound_title) {
            new SoundPlay("sound/title.wav", "title");
            is_sound_title = true;
        }


    }
}
