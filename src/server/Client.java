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

    public Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void stop() throws IOException{

    }

    public void connect() throws IOException {
//        try  {
            System.out.println("hello i am connecting");
            socket = new Socket(hostname, port);
            outputStream = socket.getOutputStream();
            output = new ObjectOutputStream(outputStream);
            inputStream = socket.getInputStream();
            input = new ObjectInputStream(inputStream);
            isConnected = true;
            connectionFailed = false;

//        } catch (UnknownHostException ex) {
//            System.out.println("Server not found: " + ex.getMessage());
//            connectionFailed = true;

//        } catch (IOException ex) {
//            System.out.println("I/O error: " + ex.getMessage());
//            connectionFailed = true;
//        }
    }
}

