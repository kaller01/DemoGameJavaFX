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
        gc.fillRect(x, y, getSize(), getSize());
        gc.setFill(Color.BLACK);
        double projectilesSize = getSize()/4;
        for (int i = 0; i < projectiles; i++) {
            if(i<3){
                gc.fillRect(x+((getSize()-projectilesSize)/2)*i, getY()-getSize()*0.75-projectilesSize/2, getSize()/4, getSize()/4);
            }
            else {
                gc.fillRect(x+((getSize()-projectilesSize)/2)*(i-3), getY()+getSize()*0.75-projectilesSize/2, getSize()/4, getSize()/4);
            }

        }


    }
}
