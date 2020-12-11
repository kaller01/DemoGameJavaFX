package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Dual extends GameCore {

    Player player1 = new Heavy(1);
    Player player2 = new Speedo(2);

    public Dual(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
    }

    public void update(double elapsedTime) {
        super.update(elapsedTime);
//        player1.move(currentlyActiveKeys);
//        player2.move(currentlyActiveKeys);
        entityManager.movePlayers(currentlyActiveKeys);



    }

    public void onResize() {
        super.onResize();
        player1.setBorder(0, 0, WIDTH/2, HEIGHT);
        player2.setBorder(WIDTH/2, 0, WIDTH, HEIGHT);
    }



}
