package game;

import javafx.scene.canvas.GraphicsContext;
import server.Client;
import server.Multiplayer;

public class Guest extends GameCore {
    private Client client;
    Multiplayer guest = Multiplayer.guest;

    public Guest(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        guest.start("192.168.2.213", 3200);
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
