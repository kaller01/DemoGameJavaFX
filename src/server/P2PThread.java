package server;

import java.io.IOException;

public class P2PThread extends Thread {
    private P2P connection;
    private boolean exit;

    public P2PThread(P2P connection) {
        this.connection = connection;
    }

    public void run() {
        while (true) {
            System.out.println("THREAD RUNNING");
            if (connection.isConnected()) {
                connection.listen();
            } else {
                System.out.println("Not connected");
            }
        }
    }

    public void exit() {
        exit = true;
    }
}
