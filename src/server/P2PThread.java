package server;

import java.io.IOException;

public class P2PThread extends Thread {
    private P2P connection;
    private boolean exit;

    public P2PThread(P2P connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            connection.connect();
            while (true) {
                if (connection.isConnected()) {
                    connection.listen();
                    connection.update();
                } else {
                    System.out.println("Not connected");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            connection.connectionFailed = true;
        }
    }

    public void exit() {
        exit = true;
    }
}
