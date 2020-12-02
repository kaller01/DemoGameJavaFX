package game;

import javafx.scene.canvas.GraphicsContext;



abstract public class Entity {
    double x, y, vx, vy, ax, ay, vMax, size;
    public Double[] border = new Double[4];
    GraphicsContext gc;

    public Entity(GraphicsContext gc){
        this.gc = gc;
    }

    abstract void draw();

    public void move(){

    }

    public void update(double deltaTime){
        double vx = this.vx + ax*deltaTime;
        double vy = this.vy + ay*deltaTime;

        //Limit velocity
        if(Math.abs(vx)<vMax){
            this.vx = vx;
        }
        if(Math.abs(vy)<vMax){
            this.vy = vy;
        }

        //Friction
        if(ax == 0){
            this.vx*=0.95;
        }
        if(ay == 0){
            this.vy*=0.95;
        }

        //Keep inside
        double x = this.x + vx*deltaTime;
        double y = this.y + vy*deltaTime;

        if(x<border[2]-size && x>border[0]) this.x = x;
        else this.vx = -this.vx;
        if(y<border[3]-size && y>border[1]) this.y = y;
        else this.vy = -this.vy;
    }

    public void setBorder(double xTop,double yTop,double xBottom,double yBottom){
        border[0] = xTop;
        border[1] = yTop;
        border[2] = xBottom;
        border[3] = yBottom;
    }


}
