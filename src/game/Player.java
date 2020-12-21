package game;

import javafx.scene.paint.Color;
import sounds.SoundEffects;

import java.util.HashMap;

public class Player extends Entity {
    protected HashMap<String, String> keySchema;
    protected double acceleration = 600;
    protected double projectileV = 500;
    protected double cooldown = 0.2;
    protected double cooldownTimer = 0;
    protected double projectileReloadTime = 1;
    protected double projectileReloadTimer = 1;
    protected int number;
    protected double hp = 5;
    protected double projectileCapacity = 6;
    protected double projectiles = projectileCapacity;
    protected double projectileDamage = 1;
    protected Direction direction;
    protected Boolean hold = false;

    public Player(Double x, Double y, Direction direction, KeySchema keySchema) {
        super();
        this.x = x;
        this.y = y;
        this.keySchema = keySchema.getKeySchema();
        this.direction = direction;
        vMax = 200;
        size = 30;
    }

    @Override
    public void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, getSize(), getSize());
        gc.setFill(Color.RED);
        gc.fillRect(getX(), getY(), 1, 1);
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

        prepareShoot(currentlyActiveKeys.containsKey(keySchema.get("SHOOT")));
    }


    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        //Friction
        if (ax == 0) this.vx *= 0.95;
        if (ay == 0) this.vy *= 0.95;

        this.updateTimers(deltaTime);
    }

    private void updateTimers(double deltaTime) {
        if (cooldownTimer >= 0) cooldownTimer -= deltaTime;
        if (cooldownTimer < 0) cooldownTimer = 0;

//        System.out.println("Proj: " + projectiles + "\nTimer: " + projectileReloadTimer + "\n");
        if (projectiles < projectileCapacity) projectileReloadTimer -= deltaTime;
        if (projectileReloadTimer <= 0) {
            projectiles++;
            projectileReloadTimer = projectileReloadTime;
        }
    }

    protected boolean isLoaded() {
        return cooldownTimer <= 0 && projectiles > 0;
    }

    protected void prepareShoot(Boolean key) {
        if (key) {
            //Makes sure you can't hold in the button
            hold = true;
        } else if (hold) {
            shoot();
            hold = false;
        }
    }


    protected void shoot() {
        if (isLoaded()) {
            SoundEffects.play(SoundEffects.getFire1());
            new Projectile(getX() + (getSize()*1.5*direction.getX()), getY() + (getSize()*1.5*direction.getY()), (projectileV*direction.getX()) + vx, vy + (projectileV*direction.getY()), projectileDamage);
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
    public void onCollision(Entity entity) {
        if (entity instanceof Projectile) {
            hp -= ((Projectile) entity).getDamage();
            if (hp <= 0) {
                entityManager.removeEntity(this);
                SoundEffects.play(SoundEffects.getGameover());
            } else SoundEffects.play(SoundEffects.getHit());
        }


    }

}
