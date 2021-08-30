package com.example.tanksbattle.model.touchbutton;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tanksbattle.model.tank.Tank;

public abstract class Button implements ButtonInterface{
    protected int x, y, cX, cY;
    protected int width, height;
    protected Bitmap button;
    protected boolean isPressed;

    public Button(int x, int y, Resources res) {
        this.x = x;
        this.y = y;
        isPressed = false;
        setBitmap(res);
    }

    protected abstract void setBitmap(Resources res);

    public abstract void pressedDown();

    public abstract void pressedUp();

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(button, x, y, paint);
    }//draw

    public boolean isPressed(double touchX, double touchY) {
        return (Math.sqrt(Math.pow(cX - touchX, 2) + Math.pow(cY - touchY, 2))) <=
                (Math.sqrt(Math.pow(width/2f, 2) + Math.pow(height/2f, 2)));
    }//isPressed

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }
}
