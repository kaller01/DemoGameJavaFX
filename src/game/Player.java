package game;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class Player extends Entity {
    protected HashMap<String, String> keySchema = new HashMap<>();
    protected double acceleration = 1000;
    protected double projectileVx = 500;
    protected double recharge = 0.2;
    protected double cooldown = 0.2;
    protected double cooldownTimer = 0;
    protected double passiveReload = 1;
    protected double passiveReloadTimer = 1;
    protected int number;
    protected double hp = 5;
    protected double projectileCapacity = 6;
    protected double projectiles = projectileCapacity;

    public Player(int number) {
        super();
        vMax = 300;
        size = 30;

        //References the same memory. Should work because it isn't a copy
        this.number = number;
        entityManager.addPlayer(this);

        switch (number) {
            case 1:
                keySchema.put("UP", "W");
                keySchema.put("DOWN", "S");
                keySchema.put("LEFT", "A");
                keySchema.put("RIGHT", "D");
                keySchema.put("SHOOT", "SPACE");
                x = 100;
                y = 100;
                break;
            case 2:
                keySchema.put("UP", "UP");
                keySchema.put("DOWN", "DOWN");
                keySchema.put("LEFT", "LEFT");
                keySchema.put("RIGHT", "RIGHT");
                keySchema.put("SHOOT", "NUMPAD0");
                this.projectileVx = -this.projectileVx;
                x = 1000;
                y = 100;
                break;
        }


    }

    public int getNumber(){
        return number;
    }

    @Override
    public void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, getSize(), getSize());
        gc.setFill(Color.RED);
        gc.fillRect(getX(),getY(),1,1);
    }

    public void move(HashMap<String, Boolean> currentlyActiveKeys) {
        if (currentlyActiveKeys.containsKey(keySchema.get("UP"))) {
            ay = -1 * acceleration;
        } else if (currentlyActiveKeys.containsKey(keySchema.get("DOWN"))) {
            ay = 1 * acceleration;
        } else {
            ay = 0;
        }

        if (currentlyActiveKeys.containsKey(keySchema.get("LEFT"))) {
            ax = -1 * acceleration;
        } else if (currentlyActiveKeys.containsKey(keySchema.get("RIGHT"))) {
            ax = 1 * acceleration;
        } else {
            ax = 0;
        }

        if (currentlyActiveKeys.containsKey(keySchema.get("SHOOT"))) {
            shoot();
        }
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        //Friction
        this.vx *= 0.97;
        this.vy *= 0.97;

        this.updateTimers(deltaTime);
    }

    public void updateTimers(double deltaTime) {
        if (cooldownTimer >= 0) cooldownTimer -= deltaTime;
        if (cooldownTimer < 0) cooldownTimer = 0;

//        System.out.println("Proj: "+projectiles+"\nTimer: " + passiveReloadTimer +"\n");
        if(projectiles<projectileCapacity) passiveReloadTimer -= deltaTime;
        if(passiveReloadTimer<=0) {
            projectiles++;
            passiveReloadTimer = passiveReload;
        }
    }

    public boolean isLoaded() {
        return cooldownTimer <= 0 && projectiles>0;
    }


    protected void shoot() {
        if (isLoaded()) {
            double xComp;
            if(projectileVx>0) xComp = getSize()*1.5;
            else xComp = -getSize();
            new Projectile(x+xComp, y, projectileVx + vx, vy);
            cooldownTimer = cooldown;
            projectiles--;
        }
    }

    @Override
    public void setX(double x) {
        if (isInsideBorderX(x)) this.x = x;
        else this.vx = -this.vx;
    }

    @Override
    public void setY(double y) {
        if (isInsideBorderY(y)) this.y = y;
        else this.vy = -this.vy;
    }

    @Override
    public void onCollision(){
        hp--;
//        System.out.println(hp);
        if(hp<=0){
            entityManager.removeEntity(this);
        }

    }

}
