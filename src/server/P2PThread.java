package server;

public class P2PThread extends Thread {
    private P2P connection;

    public P2PThread(P2P connection){
        this.connection = connection;
    }

    public void run(){
        connection.connect();
        while (true) {
            connection.listen();
        }
    }
}
