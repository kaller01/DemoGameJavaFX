package game;

import javafx.scene.paint.Color;
import sounds.SoundEffects;

public class Enemy extends Entity{

    public Enemy(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = 0;
        this.ay = 0;
        this.size = 40;
    }


    @Override
    public void onCollision(Entity entity){
        SoundEffects.play(SoundEffects.getEnemeydead());
        entityManager.removeEntity(this);

    }

    @Override
    void draw() {
        gc.setFill(Color.DARKGRAY);
        gc.fillOval(x,y,getSize(),getSize());
        double stroke = getSize()*0.07;
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(stroke);
        gc.strokeOval(x+stroke/2,y+stroke/2,getSize()-stroke,getSize()-stroke);
    }
}
