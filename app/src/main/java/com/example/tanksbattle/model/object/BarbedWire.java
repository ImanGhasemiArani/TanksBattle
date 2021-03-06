package com.example.tanksbattle.model.object;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.constant.ConstantData.IMAGE_RATIO;
import static com.example.tanksbattle.constant.ConstantData.IMAGE_RATIO_TOTAL;

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
        barbedWire = BitmapFactory.decodeResource(res, Image.DECORS[1]);
        width = barbedWire.getWidth();
        height = barbedWire.getHeight();
        float wPh = (float)width / (float)height;
        width = (int) (width * IMAGE_RATIO * IMAGE_RATIO_TOTAL * screenRatioX);
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
        Rect temp = new Rect(x, y, x + barbedWire.getWidth(), y + barbedWire.getHeight());
        canvas.drawBitmap(barbedWire, x, y, paint);
        canvas.drawRect(temp, paint);
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
