package game;

import javafx.scene.paint.Color;
import sounds.SoundEffects;

public class Base extends Entity {

    public Base(double x, double y, double size){

    }

    @Override
    void draw() {
        gc.setFill(Color.GREEN);
        gc.fillRect(x,y,size,size);
    }

    public void onCollision(){
        SoundEffects.play(SoundEffects.getGameover());
    }
}
