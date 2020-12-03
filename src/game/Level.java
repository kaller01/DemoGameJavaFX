package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


public enum Level {

    dual("duol");

    String mood;
    static GraphicsContext gc;
    double WIDTH, HEIGHT;


    /**
     *
     * @param Mood
     */
    Level(String Mood) {
        this.mood= mood;
    }


    /**
     * Switching Background
     */
    public void draw(){
        switch (mood){
            case "dual":
                drawDual();
                break;
        }
    }


    public void drawDual() {
        //Main background
        gc.setFill(Color.LIGHTGOLDENRODYELLOW);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        //The black line to the left
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * 0.009, HEIGHT);

        //The black line to the right
        gc.setFill(Color.BLACK);
        gc.fillRect(WIDTH * 0.991, 0, WIDTH * 0.009, HEIGHT);

        //The line in the middle
        gc.setFill(Color.BLACK);
        gc.fillRect(WIDTH * 0.5, 0, WIDTH * 0.009, HEIGHT);

        //The line at the bottom
        gc.setFill(Color.BLACK);
        gc.fillRect(0, HEIGHT * 0.991, WIDTH, HEIGHT * 0.9);


        this.gc.stroke();

    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public void onResize(double width, double height) {
        this.HEIGHT = height;
        this.WIDTH = width;
    }
}