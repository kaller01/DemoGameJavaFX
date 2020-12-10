package server;

import game.EntityManager;
import java.net.*;
import java.io.*;

public class Client extends P2P {
    String hostname;
    int port;
    Socket socket;
    OutputStream outputStream;

    InputStream inputStream;
    ObjectInputStream input;

    public Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void connect() {
        try  {
            socket = new Socket(hostname, port);
            outputStream = socket.getOutputStream();
            output = new ObjectOutputStream(outputStream);
            inputStream = socket.getInputStream();
            input = new ObjectInputStream(inputStream);
            isConnected = true;

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public void listen(){
        try {
            if(input.readObject() instanceof EntityManager) lastObject = input.readObject();
        } catch (Exception e) {
            System.out.println("Class does not exist");
            System.out.println(e);
        }
    }

}

