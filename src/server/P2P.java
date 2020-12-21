package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

abstract public class P2P {
    Object lastObject;
    Object objectToSend = new Object();
    protected Boolean isConnected = false;
    protected Boolean connectionFailed = false;
    ObjectOutputStream output;
    ObjectInputStream input;

    public abstract void stop() throws IOException;
    public abstract void connect() throws IOException;
    public void listen(){
        try {
            lastObject = input.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void update() {
        try {
            output.reset();
            output.writeObject(objectToSend);
            System.out.print("Objecttosend: ");
            System.out.println(objectToSend);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setUpdate(Object obj){
        this.objectToSend = obj;
    }

    public Object read(){
        return lastObject;
    }

    public Boolean isConnected() {
        return isConnected;
    }

    public Boolean isConnectionFailed(){
        return connectionFailed;
    }
}
