package game;

import java.util.HashMap;

public enum KeySchema {
    WASD("W","S","A","D","SPACE"), ARROWS("UP","DOWN","LEFT","RIGHT","NUMPAD0");

    protected HashMap<String, String> keySchema = new HashMap<>();

     KeySchema(String up, String down, String left, String right, String shoot){
                 keySchema.put("UP", up);
                 keySchema.put("DOWN", down);
                 keySchema.put("LEFT", left);
                 keySchema.put("RIGHT", right);
                 keySchema.put("SHOOT", shoot);
     }

    public HashMap<String, String> getKeySchema() {
        return keySchema;
    }
}
