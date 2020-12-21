package game;

import javafx.scene.canvas.GraphicsContext;
import server.Multiplayer;
import server.Server;

import java.util.HashMap;

public class Host extends GameCore {
    Player player1;
    Player player2;
    Multiplayer host;
    private HashMap<String, Boolean> currentlyActiveKeys2 = new HashMap<>();

    public Host(GraphicsContext gc, double width, double height, Multiplayer host) {
        super(gc, width, height);
        this.host = host;
        player2 = new Speedo(WIDTH*0.9, HEIGHT*0.1, Direction.LEFT, KeySchema.WASD);
        player1 = new Heavy(WIDTH*0.1, HEIGHT*0.1, Direction.RIGHT, KeySchema.WASD);
        onResize();
    }

    public void update(double elapsedTime) {
        super.update(elapsedTime);
        if(host.isConnected()){
            player1.move(currentlyActiveKeys);
            player2.move(currentlyActiveKeys2);
            host.getConnection().setUpdate(entityManager);
            if(host.getConnection().read() != null){
                currentlyActiveKeys2 = (HashMap<String, Boolean>) host.getConnection().read();
            }
        }




    }

    public void onResize() {
        super.onResize();
        player1.setBorder(0, 0, WIDTH/2, HEIGHT);
        player2.setBorder(WIDTH/2, 0, WIDTH, HEIGHT);
    }
}
