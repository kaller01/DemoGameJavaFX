package game;

import javafx.scene.paint.Color;
import sounds.SoundEffects;

//
public class Heavy extends Player implements SpecialAbility {
    double chargeTimer = 0;
    double chargeTime = 1;
    double charge = 0;

    Heavy(int number) {
        super(number);
        projectileCapacity = 6;
        projectileReloadTimer = 1;
    }

    public void update(double deltaTime) {
        super.update(deltaTime);
        updateCharge(deltaTime);
    }

    public void updateCharge(double deltaTime) {
        if (hold) {
            //Slows speed while charging
            vMax = 50;
            //Makes sure the ammo doesnt reload while charging
            if (chargeTimer > chargeTime*0.5) projectileReloadTimer = 1;
            chargeTimer += deltaTime;
            System.out.println(chargeTimer);
            if (chargeTimer >= chargeTime) {
                chargeTimer = 0;
                if (projectiles > 0) {
                    projectiles--;
                    charge++;
                }
            }
            System.out.println(charge);
        } else {
            charge = 0;
            chargeTimer = 0;
            vMax = 200;
        }
    }

    @Override
    public void draw() {
        double stroke = getSize() * 0.07;
        gc.setFill(new Color(1, 0, 0, ((hp + 3) / 8)));
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(stroke);
        gc.fillRect(x, y, getSize(), getSize());
        gc.strokeRect(x + stroke / 2, y + stroke / 2, getSize() - stroke, getSize() - stroke);
        gc.setFill(Color.WHITE);
        double projectilesSize = getSize() / 4;
        for (int i = 0; i < projectiles; i++) {
            if (i < 3) {
                gc.fillRect(x + ((getSize() - projectilesSize) / 2) * i, getY() - getSize() * 0.75 - projectilesSize / 2, getSize() / 4, getSize() / 4);
            } else {
                gc.fillRect(x + ((getSize() - projectilesSize) / 2) * (i - 3), getY() + getSize() * 0.75 - projectilesSize / 2, getSize() / 4, getSize() / 4);
            }

        }
    }

    protected void prepareShoot(Boolean key) {
        if (key) {
            //Makes sure you can't hold in the button
            hold = true;
        } else if (hold) {
            if (charge > 0) {
                shootCharge();
            } else shoot();
            hold = false;
        }
    }

    public void shootCharge() {
        SoundEffects.play(SoundEffects.getFire1());
        double xComp;
        if (projectileVx > 0) xComp = getSize() * 1.5;
        else xComp = -getSize();
        new HeavyProjectile(getX() + xComp, getY() - getSize() / 3, projectileVx + vx, vy, charge);
        charge = 0;
        chargeTimer = 0;
        projectileReloadTimer = 1;
    }

}
