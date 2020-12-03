package game;

import javafx.scene.canvas.GraphicsContext;

public class Invasion extends GameCore {

    public Invasion(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        Level.gc = gc;
        level = Level.dual;
        EntityManager entityManager = new EntityManager();
        Player player1;
        Player player2;


    }

    public void update(double elapsedTime) {
//        gc.setFill(Color.none);
//        gc.fillRect(0, 0, WIDTH, HEIGHT);

        level.draw();

    }

    public void onResize() {
        level.onResize(WIDTH, HEIGHT);
    }

}

