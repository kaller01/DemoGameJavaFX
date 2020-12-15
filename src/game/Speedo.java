package game;

import javafx.scene.paint.Color;

public class Speedo extends Player {


    public Speedo(int number) {
        super(number);
        projectiles = projectileCapacity = 8;
        projectileReloadTimer = 0.7;
        hp= 4;
        vMax*=1.5;
        acceleration*=1.5;
    }

    public void draw() {
        double stroke = getSize()*0.07;
        gc.setFill(new Color(0,1,0,((hp+3)/8)));
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(stroke);
        gc.fillOval(x, y, getSize(), getSize());
        gc.strokeOval(x+stroke/2,y+stroke/2,getSize()-stroke,getSize()-stroke);
        gc.setFill(Color.WHITE);
        double projectilesSize = getSize()/4;

        for (int i = 0; i < projectiles; i++) {
            gc.fillOval(getX()+getSize()*0.75*Math.cos((i*(2*Math.PI)/10)+Math.PI*1.3)-projectilesSize/2, getY()+getSize()*0.75*Math.sin(i*(2*Math.PI)/10+Math.PI*1.3)-projectilesSize/2, getSize()/4, getSize()/4);
        }
    }
}
