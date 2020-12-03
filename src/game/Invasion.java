package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Invasion extends GameCore {
    Player player = new Player(gc);

    public Invasion(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        Level.gc= gc;
        level = Level.dual;
        onResize();
    }

    public void update(double elapsedTime) {
//        gc.setFill(Color.none);
//        gc.fillRect(0, 0, WIDTH, HEIGHT);


        level.draw();

        player.draw();
        player.move(this.currentlyActiveKeys);
        player.update(elapsedTime);
    }

    public void onResize() {
        player.setBorder(0, 0, WIDTH, HEIGHT);
        level.onResize(WIDTH, HEIGHT);
    }

}
