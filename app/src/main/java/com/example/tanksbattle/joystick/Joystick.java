package com.example.tanksbattle.joystick;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Joystick {

    private final int outerCircleCenterPositionX;
    private final int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private final int outerCircleRadius;
    private final int innerCircleRadius;
    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private double centerToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    public Joystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius) {

        //outer and inner circle make up the joystick
        outerCircleCenterPositionX = centerPositionX;
        outerCircleCenterPositionY = centerPositionY;
        innerCircleCenterPositionX = centerPositionX;
        innerCircleCenterPositionY = centerPositionY;

        //radii of circles
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        //set up paint
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.GRAY);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);


    }//Constructor method

    public boolean isPressed(double touchPositionX, double touchPositionY) {
        centerToTouchDistance = Math.sqrt(
                Math.pow(outerCircleCenterPositionX - touchPositionX,2) +
                        Math.pow(outerCircleCenterPositionY - touchPositionY,2));
        return centerToTouchDistance < outerCircleRadius;
    }//isPressed

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }//setIsPressed

    public boolean getIsPressed() {
        return isPressed;
    }//getIsPressed

    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltaX = touchPositionX - outerCircleCenterPositionX;
        double deltaY =  touchPositionY - outerCircleCenterPositionY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        if (deltaDistance < outerCircleRadius) {
            actuatorX = deltaX / outerCircleRadius;
            actuatorY = deltaY / outerCircleRadius;
        }else {
            actuatorX = deltaX / deltaDistance;
            actuatorY = deltaY / deltaDistance;
        }//if else
    }//setActuator

    public void resetActuator() {
        actuatorX = actuatorY = 0.0;
    }//resetActuator

    public void draw(Canvas canvas) {
        //draw circles in canvas
        canvas.drawCircle(outerCircleCenterPositionX, outerCircleCenterPositionY, outerCircleRadius, outerCirclePaint);
        canvas.drawCircle(innerCircleCenterPositionX, innerCircleCenterPositionY, innerCircleRadius, innerCirclePaint);
    }//draw

    public void update() {
        updateInnerCirclePosition();
    }//update

    private void updateInnerCirclePosition() {
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX * outerCircleRadius);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY * outerCircleRadius);
    }//updateInnerCirclePosition

    public double getActuatorX() {
        return actuatorX;
    }//getActuatorX

    public double getActuatorY() {
        return actuatorY;
    }//getActuatorY
}//Joystick
