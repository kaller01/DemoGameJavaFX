package game;

import javafx.scene.paint.Color;

//
public class Heavy extends Player {

    Heavy(int number) {
        super(number);
        projectileCapacity = 6;
        passiveReloadTimer = 1;
    }

    @Override
    public void draw() {
        gc.setFill(Color.RED);
        gc.fillRect(x, y, size, size);
        gc.setFill(Color.BLACK);
        double projectilesSize = size/4;
        double margin;
        for (int i = 0; i < projectiles; i++) {
            if(i<3){
                gc.fillRect(x+((size-projectilesSize)/2)*i, getY()-size*0.75-projectilesSize/2, size/4, size/4);
            }
            else {
                gc.fillRect(x+((size-projectilesSize)/2)*(i-3), getY()+size*0.75-projectilesSize/2, size/4, size/4);
            }

        }


    }
}
