package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    public static double WIDTH = 1600;
    public static double HEIGHT = 800;
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    GameCore game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BAMK Game");
        ResizableCanvas canvas = new ResizableCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Bind canvas size to stack pane size.
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
        primaryStage.setScene(new Scene(root));

        //Ready
        primaryStage.show();


        System.out.println(canvas.getWidth());

        //Game
        game = new Dual(gc, WIDTH, HEIGHT);


        //timeline and fps
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> game.update(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

        //Listeners
        keyListener(primaryStage);
        resizeListener(canvas);

        game.setDimensions(WIDTH, HEIGHT);

    }

    public void keyListener(Stage primaryStage) {
        primaryStage.getScene().setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
                game.setCurrentlyActiveKeys(currentlyActiveKeys);
            }
        });
        primaryStage.getScene().setOnKeyReleased(event -> {
            currentlyActiveKeys.remove(event.getCode().toString());
            game.setCurrentlyActiveKeys(currentlyActiveKeys);
        });


    }

    public void resizeListener(Canvas canvas) {
        canvas.widthProperty().addListener((obs, oldVal, newVal) -> {
            WIDTH = (double) newVal;
            System.out.println("Width: " + WIDTH + " Height: " + HEIGHT);
            game.setDimensions(WIDTH, HEIGHT);
        });
        canvas.heightProperty().addListener((obs, oldVal, newVal) -> {
            HEIGHT = (double) newVal;
            System.out.println("Width: " + WIDTH + " Height: " + HEIGHT);
            game.setDimensions(WIDTH, HEIGHT);
        });
        WIDTH = canvas.getWidth();
        HEIGHT = canvas.getHeight();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
