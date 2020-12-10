package server;

import java.io.IOException;
import java.io.ObjectOutputStream;

abstract public class P2P {
    Object lastObject;
    protected Boolean isConnected = false;
    ObjectOutputStream output;

    public abstract void connect();
    public abstract void listen();
    public void update(Object obj) {
        try {
            System.out.println(obj);
            output.reset();
            output.writeObject(obj);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    public Object read(){
        return lastObject;
    }

    public Boolean isConnected() {
        return isConnected;
    }
}
