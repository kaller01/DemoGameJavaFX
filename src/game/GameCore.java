package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

abstract public class GameCore {
    GraphicsContext gc;
    double WIDTH, HEIGHT;
    Level level;

    protected HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    EntityManager entityManager = new EntityManager();

    public GameCore(GraphicsContext gc, double width, double height) {
        this.gc = gc;
        this.WIDTH = width;
        this.HEIGHT = height;
        Entity.setGraphicsContext(gc);
        Entity.setEntityManager(entityManager);
        Level.setGc(gc);
        level = Level.dual;

//        onResize();
    }

    public void setCurrentlyActiveKeys(HashMap<String, Boolean> keys) {
        this.currentlyActiveKeys = keys;
        // System.out.println(keys);

    }

    public void update(double elapsedTime) {
        level.draw();
        entityManager.updateAll(elapsedTime);
        entityManager.drawAll();
        // System.out.println(elapsedTime);
        // System.out.println(1*elapsedTime);
    }

    public void setDimensions(double width, double height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        onResize();
    }

    void onResize(){
        level.onResize(WIDTH,HEIGHT);
        entityManager.onResize(WIDTH, HEIGHT);

    }

}
