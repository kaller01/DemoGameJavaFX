package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Player extends Entity {
    protected HashMap<String, String> keySchema = new HashMap<>();
    protected double acceleration = 1000;


    Player(GraphicsContext gc) {
        super(gc);
        x = 100;
        y = 100;
        vMax = 300;
        size = 30;

        keySchema.put("UP", "W");
        keySchema.put("DOWN", "S");
        keySchema.put("LEFT", "A");
        keySchema.put("RIGHT", "D");
    }

    @Override
    public void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, size, size);
    }

    public void move(HashMap<String, Boolean> currentlyActiveKeys) {
        if (currentlyActiveKeys.containsKey(keySchema.get("UP"))) {
            ay = -1*acceleration;
        } else if (currentlyActiveKeys.containsKey(keySchema.get("DOWN"))) {
            ay = 1*acceleration;
        } else {
            ay = 0;
        }

        if (currentlyActiveKeys.containsKey(keySchema.get("LEFT"))) {
            ax = -1*acceleration;
        } else if (currentlyActiveKeys.containsKey(keySchema.get("RIGHT"))) {
            ax = 1*acceleration;
        } else {
            ax = 0;
        }
    }
}
