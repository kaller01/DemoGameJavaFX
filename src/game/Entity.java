package game;

import javafx.scene.canvas.GraphicsContext;


abstract public class Entity {
    double x, y, vx, vy, ax, ay, vMax, size;
    public Double[] border = new Double[4];
    protected static GraphicsContext gc;

    public Entity() {
    }

    abstract void draw();

    public void move() {

    }

    public static void setGraphicsContext(GraphicsContext graphicsContext) {
        gc = graphicsContext;
    }

    public void update(double deltaTime) {
        double vx = this.vx + ax * deltaTime;
        double vy = this.vy + ay * deltaTime;

        //Limit velocity
        if (Math.abs(vx) < vMax) {
            this.vx = vx;
        }
        if (Math.abs(vy) < vMax) {
            this.vy = vy;
        }

        double x = this.x + vx * deltaTime;
        double y = this.y + vy * deltaTime;

        setX(x);
        setY(y);
    }

    public boolean isInsideBorderX(double x) {
        return (x <= border[2] - size && x >= border[0]);
    }

    protected boolean isInsideBorderY(double y) {
        return (y < border[3] - size && y > border[1]);
    }

    public void setBorder(double xTop, double yTop, double xBottom, double yBottom) {
        border[0] = xTop;
        border[1] = yTop;
        border[2] = xBottom;
        border[3] = yBottom;
    }

    protected void setX(double x) {
        this.x = x;
    }

    protected void setY(double y) {
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public double getX(){
        return x+size/2;
    }

    public double getY(){
        return y+size/2;
    }

}
