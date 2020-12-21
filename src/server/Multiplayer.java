package server;

import java.io.IOException;

public enum Multiplayer {
    host(true), guest(false);
    P2P connection;
    P2PThread thread;

    public Boolean isHost;

    Multiplayer(Boolean isHost){
        this.isHost = isHost;
    }

    public void connect(String ip, int port) throws IOException {
        if(isHost) connection = new Server(port);
        else connection = new Client(ip,port);
        connection.connect();
    }

    public void start(){
        System.out.print("STARTING THREAD");
        thread = new P2PThread(connection);
        thread.start();
    }

    public P2P getConnection(){
        return connection;
    }

    public void stop(){
        try {
            connection.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean isConnected(){
        return getConnection().isConnected();
    }
}
