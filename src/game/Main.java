package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public static double WIDTH= 1600;
    public static double HEIGHT = 800;
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ResizableCanvas canvas = new ResizableCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);


        // Bind canvas size to stack pane size.
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
        primaryStage.setScene(new Scene(root));
        resizeListener(canvas);

        //Ready
        primaryStage.show();



        System.out.println(canvas.getWidth());

        //Game
        Invasion game = new Invasion(gc, WIDTH, HEIGHT);


        //timeline and fps
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> game.update(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();


    }

    public void resizeListener(Canvas canvas){
        canvas.widthProperty().addListener((obs, oldVal, newVal) -> {
            WIDTH = (double) newVal;
            System.out.println("Width: "+WIDTH + " Height: "+HEIGHT);
        });
        canvas.heightProperty().addListener((obs, oldVal, newVal) -> {
            HEIGHT = (double) newVal;
            System.out.println("Width: "+WIDTH + " Height: "+HEIGHT);
        });
        WIDTH = canvas.getWidth();
        HEIGHT = canvas.getHeight();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
