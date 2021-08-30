package com.example.tanksbattle.model.tank;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tanksbattle.image.TankImage;
import com.example.tanksbattle.model.touchbutton.Joystick;
import com.example.tanksbattle.model.tankobject.Gun;
import com.example.tanksbattle.model.tankobject.Hull;
import com.example.tanksbattle.model.tankobject.Track;

public class Tank {

    private Resources res;
    private int x, y;
    private double xVelocity, yVelocity;
    private int maxSpeed;
    private Hull hull;
    private Gun gun;
    private Track track;

    public Tank(Resources res) {
        this.res = res;
        hull = new Hull(TankImage.TANK_HULL[0], res);

        maxSpeed = 10;
    }//Constructor method

    public void update() {
//        xVelocity = joystick.getActuatorX() * maxSpeed;
//        yVelocity = joystick.getActuatorY() * maxSpeed;
//        float angle = (float) joystick.getAngle();




//        hull.update(xVelocity, yVelocity, angle);


    }//update

    public void draw(Canvas canvas, Paint paint) {
        hull.draw(canvas, paint);

    }//draw
}//Tank
