package sounds;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundEffects {
    public static Media hit;
    public static Media fire1;
    public static Media gameover;
    public static Media enemeydead;

    public static void init() {
        hit = load(hit,"hit.wav");
        fire1 = load(fire1, "fire1.mp3");
        gameover = load(gameover, "gameover.wav");
        enemeydead = load(enemeydead, "enemydead.wav");
    }

    public static Media load(Media media, String file){
       return new Media(new File(("src/sounds/"+file)).toURI().toString());
    }

    public static Media getEnemeydead() {
        return enemeydead;
    }

    public static Media getFire1() {
        return fire1;
    }

    public static Media getGameover() {
        return gameover;
    }

    public static Media getHit() {
        return hit;
    }

    public static void play(Media media) {
        new MediaPlayer(media).play();
    }

}
