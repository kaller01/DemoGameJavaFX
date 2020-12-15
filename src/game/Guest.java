package game;

import javafx.scene.canvas.GraphicsContext;
import server.Client;
import server.Multiplayer;

public class Guest extends GameCore {
    Multiplayer guest = Multiplayer.guest;

    public Guest(GraphicsContext gc, double width, double height, String ip, int port) {
        super(gc, width, height);
        guest.start(ip, port);
        onResize();
    }

    @Override
    public void update(double elapsedTime) {
        level.draw();

        if(guest.isConnected()){
            guest.getConnection().update(currentlyActiveKeys);
            entityManager = (EntityManager) guest.getConnection().read();
            if(entityManager != null){
                entityManager.drawAll();
            }
//            System.out.println(currentlyActiveKeys);
//            entityManager.drawAll();
        }




    }
}
