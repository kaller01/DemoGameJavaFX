package game;

import javafx.scene.canvas.GraphicsContext;
import server.Multiplayer;
import server.Server;

import java.util.HashMap;

public class Host extends GameCore {
    Player player1;
    Player player2;
    Multiplayer host = Multiplayer.host;
    private HashMap<String, Boolean> currentlyActiveKeys2 = new HashMap<>();

    public Host(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        host.start("192.168.2.213", 3200);
        player2 = new Player(2);
        player1 = new Heavy(1);
        onResize();
    }

    public void update(double elapsedTime) {
        super.update(elapsedTime);
        player1.move(currentlyActiveKeys);
        player2.move(currentlyActiveKeys2);

        if(host.isConnected()){
            host.getConnection().update(entityManager);
            if(host.getConnection().read() != null){
                currentlyActiveKeys2 = (HashMap<String, Boolean>) host.getConnection().read();
            }
//            System.out.println(currentlyActiveKeys2);
        }




    }

    public void onResize() {
        super.onResize();
        player1.setBorder(0, 0, WIDTH/2, HEIGHT);
        player2.setBorder(WIDTH/2, 0, WIDTH, HEIGHT);
    }
}
