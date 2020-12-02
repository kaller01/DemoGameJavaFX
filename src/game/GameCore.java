package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

abstract public class GameCore {
    GraphicsContext gc;
    double WIDTH, HEIGHT;
    Level level;

    protected HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();

    public GameCore(GraphicsContext gc, double width, double height) {
        this.gc = gc;
        this.WIDTH = width;
        this.HEIGHT = height;
        level = new Level(gc, WIDTH, HEIGHT);
        level.draw(gc);
    }

    public void setCurrentlyActiveKeys(HashMap<String, Boolean> keys) {
        this.currentlyActiveKeys = keys;
        // System.out.println(keys);

    }

    public void update(double elapsedTime) {
        // System.out.println(elapsedTime);
        // System.out.println(1*elapsedTime);
    }

    public void setDimensions(double width, double height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        onResize();
    }

    abstract void onResize();

}
