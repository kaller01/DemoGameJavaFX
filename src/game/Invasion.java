package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Invasion extends GameCore {
    EntityManager entityManager = new EntityManager();
    Player player1;
    Player player2;

    public Invasion(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        Entity.setGraphicsContext(gc);
        Player.entityManager = entityManager;
        player1 = new Player(1);
        player2 = new Player(2);
        entityManager.addPlayer(player1);
        entityManager.addPlayer(player2);
        onResize();
    }

    public void update(double elapsedTime) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        player1.move(this.currentlyActiveKeys);
        player2.move(this.currentlyActiveKeys);

        entityManager.updateAll(elapsedTime);
        entityManager.drawAll();

    }

    public void onResize() {
        player1.setBorder(0, 0, WIDTH, HEIGHT);
        player2.setBorder(0, 0, WIDTH, HEIGHT);
    }
}
