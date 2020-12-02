package game;


public class Projectile extends Entity {
    double size = 20;


    public Projectile(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        this.ax = 0;
        this.ay = 0;
    }

    public void draw() {
        gc.fillRect(x, y, size, size);
    }
}
