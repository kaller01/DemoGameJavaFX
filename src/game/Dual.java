package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Dual extends GameCore {

    Player player1;
    Player player2;

    public Dual(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        player2 = new Player(2);
        player1 = new Player(1);

        onResize();
    }

    public void update(double elapsedTime) {
        super.update(elapsedTime);
//        player1.move(currentlyActiveKeys);
//        player2.move(currentlyActiveKeys);

        entityManager.updateAll(elapsedTime);
        entityManager.drawAll();
        entityManager.movePlayers(currentlyActiveKeys);

    }

    public void onResize() {
        player1.setBorder(0, 0, WIDTH/2, HEIGHT);
        player2.setBorder(WIDTH/2, 0, WIDTH, HEIGHT);
        level.onResize(WIDTH,HEIGHT);
    }



}
