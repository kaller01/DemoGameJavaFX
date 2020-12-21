package game;

import javafx.scene.canvas.GraphicsContext;
import sounds.SoundEffects;

public class Dual extends GameCore {

    Player player1 = new Heavy(WIDTH*0.1, HEIGHT*0.1, Direction.RIGHT, KeySchema.WASD);
    Player player2 = new Speedo(WIDTH*0.9, HEIGHT*0.1, Direction.LEFT, KeySchema.ARROWS);

    public Dual(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
    }

    public void update(double elapsedTime) {
        super.update(elapsedTime);
        player1.move(currentlyActiveKeys);
        player2.move(currentlyActiveKeys);




    }

    public void onResize() {
        super.onResize();
        player1.setBorder(0, 0, WIDTH/2, HEIGHT);
        player2.setBorder(WIDTH/2, 0, WIDTH, HEIGHT);
    }



}
