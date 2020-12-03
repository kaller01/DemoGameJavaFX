package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Invasion extends GameCore {
    EntityManager entityManager = new EntityManager();
    Player player1;
    Player player2;

    public Invasion(GraphicsContext gc, double width, double height) {
        super(gc, width, height);


        onResize();
    }

    public void update(double elapsedTime) {

    }

    public void onResize() {

    }
}
