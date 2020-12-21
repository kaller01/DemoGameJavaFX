package game;

import javafx.scene.canvas.GraphicsContext;
import server.Client;
import server.Multiplayer;

import java.util.HashMap;

public class Guest extends GameCore {
    Multiplayer guest;

    public Guest(GraphicsContext gc, double width, double height, Multiplayer guest) {
        super(gc, width, height);
        this.guest = guest;
        onResize();
    }

    @Override
    void onResize() {
        if(entityManager != null){
            super.onResize();
        }
    }

    @Override
    public void update(double elapsedTime) {
        level.draw();

        if(guest.isConnected()){
            guest.getConnection().setUpdate(currentlyActiveKeys);
            entityManager = (EntityManager) guest.getConnection().read();
            if(entityManager != null){
                entityManager.drawAll();
            }
        }




    }
}