package com.example.tanksbattle.model.tank;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.activity.MainActivity.screenRatioY;
import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;
import static com.example.tanksbattle.constant.ConstantData.currentGun;
import static com.example.tanksbattle.constant.ConstantData.currentHull;
import static com.example.tanksbattle.constant.ConstantData.currentMovingFire;
import static com.example.tanksbattle.constant.ConstantData.currentTire;
import static com.example.tanksbattle.constant.ConstantData.currentTrackA;
import static com.example.tanksbattle.constant.ConstantData.currentTrackB;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.tanksbattle.factory.BattlegroundFactory;
import com.example.tanksbattle.image.Image;
import com.example.tanksbattle.model.tankobject.MovingFire;
import com.example.tanksbattle.model.tankobject.Tire;
import com.example.tanksbattle.model.touchbutton.ButtonInterface;
import com.example.tanksbattle.model.tankobject.Gun;
import com.example.tanksbattle.model.tankobject.Hull;
import com.example.tanksbattle.model.tankobject.Track;

public class Tank {

    private final Resources res;
    private double x, y;
    private double angle, gunAngle;
    private int width, height;
    private int maxSpeed;
    private Hull hull;
    private Gun gun;
    private Track rightTrack;
    private Track leftTrack;
    private Tire rightTire;
    private Tire leftTire;
    private MovingFire rightMovingFire;
    private MovingFire leftMovingFire;
    private int gunRotating;
    private boolean isShooting;

    private BattlegroundFactory battlegroundFactory;

    public Tank(int x, int y, Resources res, BattlegroundFactory battlegroundFactory) {
        this.battlegroundFactory = battlegroundFactory;
        this.res = res;
        this.x = x;
        this.y = y;
        angle = gunAngle = 0;
        maxSpeed = 15;
        gunRotating = 0;
        isShooting = false;

        hull = new Hull(this, currentHull, x, y, res);

        gun = new Gun(currentGun, x, y, res);
        gun.setSwap(height/5f);

        rightTrack = new Track(currentTrackA, currentTrackB, x, y, res);
        rightTrack.setSwap(width/2f-rightTrack.getWidth()*2/3f, -height/2f);
        leftTrack = new Track(currentTrackA, currentTrackB, x, y, res);
        leftTrack.setSwap(-width/2f-leftTrack.getWidth()/3f, -height/2f);

        rightTire = new Tire(currentTire, x, y, res);
        leftTire = new Tire(currentTire, x, y, res);
        setSwapTiresUp();

        rightMovingFire = new MovingFire(currentMovingFire, x, y, res);
        rightMovingFire.setSwap(rightMovingFire.getWidth()/3f, height/2f);
        leftMovingFire = new MovingFire(currentMovingFire, x, y, res);
        leftMovingFire.setSwap(-leftMovingFire.getWidth()*4/3f, height/2f);

    }//Constructor method

    public void update(ButtonInterface[] buttons) {
        for (ButtonInterface button : buttons)
            button.update(this);

        if (gunRotating == 1) {
            gunAngle += maxSpeed / 2f * (screenRatioX + screenRatioY) / 2f;
        } else if (gunRotating == 2) {
            gunAngle -= maxSpeed / 2f * (screenRatioX + screenRatioY) / 2f;
        }

        updateTankObjects();
    }//update

    public void updateXY(double increaseX, double increaseY) {
        Rect rect = new Rect((int) (x - hull.getWidth()/2 + increaseX), (int) (y - hull.getHeight()/2 + increaseY),
                (int) (x+ increaseX + hull.getWidth()/2), (int) (y+ increaseY + hull.getHeight()/2));

//        Rect rect = getCollision();

        boolean rX = false, rY = false;
        if (!battlegroundFactory.isCollision(rect)) {
            if (increaseX > 0 && x + increaseX > screenX * 6 / 7f) {
                rX = battlegroundFactory.update(-increaseX, 0)[0];
            } else if (increaseX < 0 && x + increaseX < screenX / 7f ) {
                rX = battlegroundFactory.update(-increaseX, 0)[0];
            }
            if (!rX) {
                x += increaseX;
            }

            if (increaseY > 0 && y + increaseY > screenY * 4 / 5f) {
                rY = battlegroundFactory.update(0, -increaseY)[1];
            } else if (increaseY < 0 && y + increaseY < screenY / 5f ) {
                rY = battlegroundFactory.update(0, -increaseY)[1];
            }
            if (!rY) {
                y += increaseY;
            }
        }

    }//updateXY

    public void updateAngle(double increaseAngle) {
        hull.updateWidthHeight(angle + increaseAngle);
        Rect rect = new Rect((int) (x - hull.getWidth()/2), (int) (y - hull.getHeight()/2),
                (int) (x + hull.getWidth()/2), (int) (y+ hull.getHeight()/2));
        if (!battlegroundFactory.isCollision(rect)) {
            angle += increaseAngle;
            if (angle >= 360) {
                angle -= 360;
            }else if (angle <= 0) {
                angle += 360;
            }
        }

    }//updateAngle

    private void updateTankObjects() {
        hull.update(x, y, angle);

        gun.update(x, y, angle, gunAngle);

        rightTrack.update(x, y, angle);
        leftTrack.update(x, y, angle);

        rightTire.update(x, y, angle);
        leftTire.update(x, y, angle);

        rightMovingFire.update(x, y, angle);
        leftMovingFire.update(x, y, angle);
    }//updateTankObjects

    public void draw(Canvas canvas, Paint paint) {

        rightMovingFire.draw(canvas, paint);
        leftMovingFire.draw(canvas, paint);

        rightTire.draw(canvas, paint);
        leftTire.draw(canvas, paint);

        leftTrack.draw(canvas, paint);
        rightTrack.draw(canvas, paint);

        hull.draw(canvas, paint);

        gun.draw(canvas,paint);

    }//draw

    public int getMaxSpeed() {
        return maxSpeed;
    }//getMaxSpeed

    public double getAngle() {
        return angle/180*Math.PI;
    }//getAngle

    public void setWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }//setWidthHeight

    public void setIsShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public void setGunRotating(int rotate) {
        gunRotating = rotate;
    }

    public int getGunRotating() {
        return gunRotating;
    }

    public void nextTrackLeftImage() {
        if (leftTrack.wingCounter == 0) {
            leftTrack.wingCounter++;
        } else {
            leftTrack.wingCounter = 0;
        }
        leftTire.isDraw = true;
        leftMovingFire.isDraw = true;
    }

    public void nextTrackRightImage() {
        if (rightTrack.wingCounter == 0) {
            rightTrack.wingCounter++;
        } else {
            rightTrack.wingCounter = 0;
        }
        rightTire.isDraw = true;
        rightMovingFire.isDraw = true;
    }

    public void setSwapTiresUp() {
        rightTire.setSwap(width/2f - rightTire.getWidth()*2/3f, height/2f);
        leftTire.setSwap(-width/2f - leftTire.getWidth()/3f, height/2f);
    }

    public void setSwapTiresDown() {
        rightTire.setSwap(width/2f - rightTire.getWidth()*2/3f, -height/2f - rightTire.getHeight()/2f);
        leftTire.setSwap(-width/2f - leftTire.getWidth()/3f, -height/2f - leftTire.getHeight()/2f);
    }
}//Tank
