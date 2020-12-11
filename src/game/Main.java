package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.HashMap;

import server.Multiplayer;

public class Main extends Application {
    public static double WIDTH = 1600;
    public static double HEIGHT = 800;
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    GameCore game;
    SceneManager sceneManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BAMK Game");
        sceneManager = new SceneManager(primaryStage);
        sceneManager.setupCanvas();
        sceneManager.setupMenu();
//        sceneManager.startGame(new Dual(sceneManager.getGraphicsContext(), WIDTH, HEIGHT));

        primaryStage.setScene(sceneManager.getGameScene());
//        sceneManager.showCanvas();
        sceneManager.showStart();

        //Ready
        primaryStage.show();



        //Game
        game = new Dual(sceneManager.getGraphicsContext(), WIDTH, HEIGHT);


        //timeline and fps
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> {
            System.out.println("hello");
            game.update(SECOND_DELAY);
                });
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

        //Listeners
        keyListener();
        resizeListener();

        game.setDimensions(WIDTH, HEIGHT);
    }

    public void keyListener() {
        sceneManager.getGameScene().setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
                game.setCurrentlyActiveKeys(currentlyActiveKeys);
                System.out.println(currentlyActiveKeys);
            }
        });
        sceneManager.getGameScene().setOnKeyReleased(event -> {
            currentlyActiveKeys.remove(event.getCode().toString());
            game.setCurrentlyActiveKeys(currentlyActiveKeys);
        });


    }

    public void resizeListener() {
        sceneManager.getCanvas().widthProperty().addListener((obs, oldVal, newVal) -> {
            WIDTH = (double) newVal;
            System.out.println("Width: " + WIDTH + " Height: " + HEIGHT);
            game.setDimensions(WIDTH, HEIGHT);
        });
        sceneManager.getCanvas().heightProperty().addListener((obs, oldVal, newVal) -> {
            HEIGHT = (double) newVal;
            System.out.println("Width: " + WIDTH + " Height: " + HEIGHT);
            game.setDimensions(WIDTH, HEIGHT);
        });
        WIDTH = sceneManager.getCanvas().getWidth();
        HEIGHT = sceneManager.getCanvas().getHeight();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
