package server;

import java.io.IOException;

public class ThreadListen extends Thread {
    private P2P connection;
    private boolean exit;

    public ThreadListen(P2P connection) {
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
}
