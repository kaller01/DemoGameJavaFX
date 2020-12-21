package game;

import javafx.scene.canvas.GraphicsContext;

public class Invasion extends GameCore {
    Player player;
    double counter = 0;

    public Invasion(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        Level.gc = gc;
        level = Level.invasion;
        player = new Heavy(WIDTH*0.1,HEIGHT*0.1, Direction.RIGHT, KeySchema.WASD);




    }

    public void update(double elapsedTime) {
//        gc.setFill(Color.none);
//        gc.fillRect(0, 0, WIDTH, HEIGHT);

        level.draw();


        player.move(currentlyActiveKeys);

        counter+= elapsedTime;
        if(counter>2) {
            counter = 0;
            new Enemy(WIDTH,Math.random()*HEIGHT,-50,0);
        }

        entityManager.updateAll(elapsedTime);
        entityManager.drawAll();
    }



    public void onResize() {
        level.onResize(WIDTH, HEIGHT);
        player.setBorder(0, 0, WIDTH, HEIGHT);
    }

}

