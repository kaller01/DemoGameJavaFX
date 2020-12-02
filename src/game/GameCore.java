package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

public class GameCore {
    GraphicsContext gc;
    double WIDTH, HEIGHT;
    private HashMap<String, Boolean> currentlyActiveKeys;
    Level level;


    public GameCore(GraphicsContext gc, double width, double height) {
        this.gc = gc;
        this.WIDTH = width;
        this.HEIGHT = height;
        level =new Level(gc, WIDTH, HEIGHT);
        level.draw(gc);
    }

    public void setCurrentlyActiveKeys(HashMap<String, Boolean> keys) {
        this.currentlyActiveKeys = keys;
        System.out.println(keys);

    }

    public void update(double elapsedTime) {
//        System.out.println(elapsedTime);
//        System.out.println(1*elapsedTime);
    }


}
