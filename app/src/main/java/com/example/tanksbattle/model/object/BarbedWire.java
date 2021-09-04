package com.example.tanksbattle.model.object;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.tanksbattle.image.Image;

public class BarbedWire implements DecorObjectInterface{

    private Bitmap barbedWire;
    private int x, y;
    private int width, height;

    public BarbedWire(int x, int y, Resources res) {
        this.x = x;
        this.y = y;
        barbedWire = BitmapFactory.decodeResource(res, Image.BARBED_WIRE[0]);
        width = barbedWire.getWidth();
        height = barbedWire.getHeight();
        float wPh = (float)width / (float)height;
        width = (int) (width / 4 * screenRatioX);
        height = (int) (width / wPh);
        barbedWire = Bitmap.createScaledBitmap(barbedWire, width, height, false);
    }//Constructor method

    @Override
    public void update(double updateX, double updateY) {
        x += updateX;
        y += updateY;
    }//update

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(barbedWire, x, y, paint);
    }//draw

    @Override
    public boolean isCollision(Rect rect) {
        Rect temp = new Rect(x, y, x + barbedWire.getWidth(), y + barbedWire.getHeight());
        return Rect.intersects(temp, rect);
    }//isCollision

    public int getWidth() {
        return width;
    }//getWidth

    public int getHeight() {
        return height;
    }//getHeight
}//BarbedWire
