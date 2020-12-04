package game;

import javafx.scene.canvas.GraphicsContext;


abstract public class Entity {
    double x, y, vx, vy, ax, ay, vMax, size;
    public Double[] border = new Double[4];
    protected static GraphicsContext gc;
    protected static EntityManager entityManager;

    /**
     * Adds itself to the entityManager, thus Entity can not exist without an EntityManager
     */
    public Entity() {
        entityManager.addEntity(this);
    }

    /**
     * All entities must implement draw where GraphicsContext is used.
     * This method should ONLY draw
     */
    abstract void draw();

    /**
     * Sets the EntityManager of all entities.
     * Assumptions that there will only be one EntityManager for all entities
     * @param em
     */
    public static void setEntityManager(EntityManager em){
        entityManager = em;
    }

    /**
     * Sets GraphicsContext from canvas
     * @param graphicsContext
     */
    public static void setGraphicsContext(GraphicsContext graphicsContext) {
        gc = graphicsContext;
    }

    /**
     * Updates velocity and location of Entity.
     * @param deltaTime
     */
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

    /**
     * Checks if entity is inside border in x
     * @param x
     * @return true if inside
     */
    public boolean isInsideBorderX(double x) {
        return (x <= border[2] - size && x >= border[0]);
    }

    /**
     * Checks if entity is inside border in y
     * @param y
     * @return true if inside
     */
    protected boolean isInsideBorderY(double y) {
        return (y < border[3] - size && y > border[1]);
    }

    /**
     * Sets border size
     * @param xTop
     * @param yTop
     * @param xBottom
     * @param yBottom
     */
    public void setBorder(double xTop, double yTop, double xBottom, double yBottom) {
        border[0] = xTop;
        border[1] = yTop;
        border[2] = xBottom;
        border[3] = yBottom;
    }

    /**
     * @param x
     */
    protected void setX(double x) {
        this.x = x;
    }

    /**
     * @param y
     */
    protected void setY(double y) {
        this.y = y;
    }

    /**
     * @return size of Entity
     */
    public double getSize() {
        return size;
    }

    /**
     * @return center x of Entity
     */
    public double getX(){
        return x+size/2;
    }

    /**
     * @return center x of Entity
     */
    public double getY(){
        return y+size/2;
    }

    public void onCollision(){
        entityManager.removeEntity(this);
    }

}
