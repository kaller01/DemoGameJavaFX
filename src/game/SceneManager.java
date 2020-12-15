package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneManager {
    GameCore game;
    Scene gameScene;
    Scene startScene;
    Scene selectPort;
    Scene selectHost;
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
                showSelectHost();
            }
        });
    }

    public void setupSelectPort(){
        VBox root = new VBox();
        HBox h = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setMinWidth(minWidth);
        root.setMinHeight(minHeight);
        h.setAlignment(Pos.CENTER);
        TextField textfield = new TextField("3200");
        textfield.setPrefWidth(350);
        Button button = new Button("Start");
        Button button1 = new Button("Settings");
        Label bamk = new Label("Port:");
        bamk.setTextFill(Color.WHITE);

        h.getChildren().addAll(bamk,textfield);
        root.getChildren().addAll(h,button);
        selectPort = new Scene(root);
        selectPort.getStylesheets().add("/css/start.css");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game = new Host(getGraphicsContext(), minWidth, minHeight, Integer.parseInt(textfield.getText()));
                showCanvas();
            }
        });
    }

    public void setupSelectHost(){
        VBox root = new VBox();
        HBox h = new HBox();
        HBox h2 = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setMinWidth(minWidth);
        root.setMinHeight(minHeight);
        h.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        TextField textfield = new TextField("3200");
        TextField textField2 = new TextField("90.231.235.28");
        textfield.setPrefWidth(800);
        textField2.setPrefWidth(800);
        Button button = new Button("Start");
        Button button1 = new Button("Settings");
        Label port = new Label("Port:");
        Label host = new Label("   IP:");
        port.setTextFill(Color.WHITE);
        host.setTextFill(Color.WHITE);

        h2.getChildren().addAll(host,textField2);
        h.getChildren().addAll(port,textfield);
        root.getChildren().addAll(h,h2,button);
        selectHost = new Scene(root);
        selectHost.getStylesheets().add("/css/start.css");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game = new Guest(getGraphicsContext(), minWidth, minHeight, textField2.getText(), Integer.parseInt(textfield.getText()));
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

    public void showSelectPort(){
        stage.setScene(selectPort);
    }

    public void showSelectHost(){
        stage.setScene(selectHost);
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

    public GameCore getGame() {
        return game;
    }
}
