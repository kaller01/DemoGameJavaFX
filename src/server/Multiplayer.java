package server;

public enum Multiplayer {
    host(true), guest(false);
    P2P connection;
    P2PThread thread;

    public Boolean isHost;

    Multiplayer(Boolean isHost){
        this.isHost = isHost;
    }

    public void start(String ip, int port){
        if(isHost) connection = new Server(port);
        else connection = new Client(ip,port);
        thread = new P2PThread(connection);
        thread.start();
    }

    public P2P getConnection(){
        return connection;
    }

    public Boolean isConnected(){
        return getConnection().isConnected();
    }
}
