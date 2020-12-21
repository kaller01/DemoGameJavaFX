package game;

import javafx.scene.canvas.GraphicsContext;
import server.Client;
import server.Multiplayer;

public class Guest extends GameCore {
    Multiplayer guest;

    public Guest(GraphicsContext gc, double width, double height, Multiplayer guest) {
        super(gc, width, height);
        this.guest = guest;
        guest.start();
        onResize();
    }

    @Override
    public void update(double elapsedTime) {
        level.draw();

        if(guest.isConnected()){
            guest.getConnection().setUpdate(currentlyActiveKeys);
            guest.getConnection().update();
            entityManager = (EntityManager) guest.getConnection().read();
            if(entityManager != null){
                entityManager.drawAll();
            }
        }




    }
}
