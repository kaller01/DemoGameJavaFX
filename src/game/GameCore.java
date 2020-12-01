package game;

import javafx.scene.canvas.GraphicsContext;

public class GameCore {
    GraphicsContext gc;
    double WIDTH, HEIGHT;

    public GameCore(GraphicsContext gc, double width, double height){
        this.gc = gc;
        this.WIDTH = width;
        this.HEIGHT = height;
    }


    public void update(double elapsedTime){
//        System.out.println(elapsedTime);
//        System.out.println(1*elapsedTime);
    }


}
