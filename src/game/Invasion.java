package game;

import javafx.scene.canvas.GraphicsContext;

/**
 * Singleplayer gamemode where you shoot at enemies
 */
public class Invasion extends GameCore {
    Player player;
    double counter = 0;

    /**
     * @param gc
     * @param width of canvas
     * @param height of canvas
     */
    public Invasion(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        Level.gc = gc;
        level = Level.invasion;
        player = new Heavy(WIDTH*0.1,HEIGHT*0.1, Direction.RIGHT, KeySchema.WASD);
    }

    /**
     * Updates game
     * @param elapsedTime
     */
    public void update(double elapsedTime) {
        level.draw();
        player.move(currentlyActiveKeys);

        counter+= elapsedTime;
        if(counter>2) {
            counter = 0;
            new Enemy(WIDTH,Math.random()*HEIGHT,Direction.LEFT);
        }

        entityManager.updateAll(elapsedTime);
        entityManager.drawAll();
    }


    /**
     * When resized, do this
     */
    public void onResize() {
        level.onResize(WIDTH, HEIGHT);
        player.setBorder(0, 0, WIDTH, HEIGHT);
    }
}

