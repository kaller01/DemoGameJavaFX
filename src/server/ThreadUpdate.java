package server;

import java.io.IOException;

public class ThreadUpdate extends Thread {
    private P2P connection;
    private boolean exit;

    public ThreadUpdate(P2P connection) {
        this.connection = connection;
    }

    public void run() {
        while (true) {
            System.out.println("THREAD RUNNING");
            if (connection.isConnected()) {
                connection.update();
            } else {
                System.out.println("Not connected");
            }
        }
    }
}
