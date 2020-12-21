package server;

import game.EntityManager;
import game.Player;

import java.io.*;
import java.net.*;

public class Server extends P2P {
    int port = 3200;
    Socket socket;
    public ServerSocket serverSocket;
    OutputStream outputStream;
    InputStream inputStream;

    public Server(int port){
        this.port = port;
    }

    public void connect() throws IOException {

//        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            socket = serverSocket.accept();
            System.out.println("New client connected");

            inputStream = socket.getInputStream();
            input = new ObjectInputStream(inputStream);
            outputStream = socket.getOutputStream();
            output = new ObjectOutputStream(outputStream);
            isConnected = true;

//        } catch (IOException ex) {
//            System.out.println("Server exception: " + ex.getMessage());
//            ex.printStackTrace();
//        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}