package game;


import javafx.scene.paint.Color;

public class Projectile extends Entity {

    /**
     * @param x  start position x
     * @param y  start position y
     * @param vx constant velocity x
     * @param vy constant velocity y
     */
    public Projectile(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        //Projectils have constant velocity
        this.ax = 0;
        this.ay = 0;
        this.size = 20;

        border[1] = 0.0;
        border[3] = 1000.0;
    }

    /**
     * Draws the projectile
     */
    public void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, size, size);
        gc.setFill(Color.RED);
        gc.fillRect(getX(), getY(), 1, 1);
    }

    @Override
    public void setY(double y) {
        if (isInsideBorderY(y)) this.y = y;
        else this.vy = -this.vy;
    }
}
