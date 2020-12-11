package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneManager {
    GameCore game;
    Scene gameScene;
    Scene startScene;
    ResizableCanvas canvas;
    GraphicsContext gc;
    Stage stage;
    double minWidth = 1600;
    double minHeight = 800;

    public SceneManager(Stage stage){
        this.stage = stage;
    }


    public void setupCanvas() {
        canvas = new ResizableCanvas();
        //        Canvas canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Bind canvas size to stack pane size.
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
        root.setMinHeight(minHeight);
        root.setMinWidth(minWidth);
        gameScene = new Scene(root);
    }

    public void setupMenu(){
//        StackPane root = new StackPane();
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setMinWidth(minWidth);
        root.setMinHeight(minHeight);
        Button button = new Button("Start");
        Button button1 = new Button("Settings");
        Label bamk = new Label("BAMK");
        bamk.setTextFill(Color.WHITE);
        root.getChildren().addAll(bamk,button);
        startScene = new Scene(root);
        startScene.getStylesheets().add("/css/start.css");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showCanvas();
            }
        });
    }

    public void showCanvas(){
        stage.setScene(gameScene);
    }

    public void showStart(){
        stage.setScene(startScene);
    }

    public Scene getGameScene(){
        return gameScene;
    }

    public GraphicsContext getGraphicsContext(){
        return gc;
    }

    public Canvas getCanvas(){
        return canvas;
    }


}
