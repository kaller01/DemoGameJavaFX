package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static com.sun.javafx.iio.ios.IosImageLoader.GRAY;


public class Level {

    GraphicsContext gc;
    double WIDTH, HEIGHT;

    public Level(GraphicsContext gc,double width,double height){
        this.gc = gc;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public void draw(GraphicsContext gc) {
        this.gc.setFill(Color.GRAY);
        this.gc.fillRect(0,0, WIDTH,HEIGHT);

        gc.setFill(Color.AQUA);
        gc.setLineDashes(500,12, WIDTH, HEIGHT);
        gc.fillRect(0, 30, 50, 50);

        this.gc.stroke();


    }


}