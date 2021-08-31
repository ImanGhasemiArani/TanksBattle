package com.example.tanksbattle.model.tank;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.activity.MainActivity.screenRatioY;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tanksbattle.image.TankImage;
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
    private final ButtonInterface[] buttons;
    private int gunRotating;
    private boolean isShooting;

    public Tank(int x, int y, Resources res, ButtonInterface[] buttons) {
        this.res = res;
        this.buttons = buttons;
        this.x = x;
        this.y = y;
        angle = gunAngle = 0;
        maxSpeed = 10;
        gunRotating = 0;
        isShooting = false;

        hull = new Hull(this, TankImage.TANK_HULL[0], x, y, res);

        gun = new Gun(TankImage.TANK_GUN[0], x, y, res);
        gun.setSwap(height/5f);

        rightTrack = new Track(TankImage.TANK_TRACK_A[0], TankImage.TANK_TRACK_B[0], x, y, res);
        rightTrack.setSwap(width/2f-rightTrack.getWidth()*2/3f, -height/2f);
        leftTrack = new Track(TankImage.TANK_TRACK_A[0], TankImage.TANK_TRACK_B[0], x, y, res);
        leftTrack.setSwap(-width/2f-leftTrack.getWidth()/3f, -height/2f);

        rightTire = new Tire(TankImage.TIRE_TANK[0], x, y, res);
        leftTire = new Tire(TankImage.TIRE_TANK[0], x, y, res);
        setSwapTiresUp();


    }//Constructor method

    public void update() {
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
        x += increaseX;
        y += increaseY;
    }//updateXY

    public void updateAngle(double increaseAngle) {
        angle += increaseAngle;
        if (angle >= 360) {
            angle -= 360;
        }else if (angle <= -360) {
            angle += 360;
        }
    }//updateAngle

    private void updateTankObjects() {
        hull.update(x, y, angle);
        gun.update(x, y, angle, gunAngle);
        rightTrack.update(x, y, angle);
        leftTrack.update(x, y, angle);

        rightTire.update(x, y, angle);
        leftTire.update(x, y, angle);
    }//updateTankObjects

    public void draw(Canvas canvas, Paint paint) {
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
    }

    public void nextTrackRightImage() {
        if (rightTrack.wingCounter == 0) {
            rightTrack.wingCounter++;
        } else {
            rightTrack.wingCounter = 0;
        }
        rightTire.isDraw = true;
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
