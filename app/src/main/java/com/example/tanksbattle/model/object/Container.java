package com.example.tanksbattle.model.object;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.activity.MainActivity.screenRatioY;
import static com.example.tanksbattle.constant.ConstantData.IMAGE_RATIO;
import static com.example.tanksbattle.constant.ConstantData.IMAGE_RATIO_TOTAL;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.tanksbattle.image.Image;

public class Container implements DecorObjectInterface{

    private Bitmap container;
    private int x, y;
    private int width, height;

    public Container(int x, int y, String vORh, Resources res) {
        this.x = x;
        this.y = y;
        int id = 0;
        if (vORh.equals("V"))
            id = Image.DECORS[3];
        else if (vORh.equals("H"))
            id = Image.DECORS[2];
        container = BitmapFactory.decodeResource(res, id);
        width = container.getWidth();
        height = container.getHeight();
        float wPh = (float)width / (float)height;
        width = (int) (width * IMAGE_RATIO * 2 * IMAGE_RATIO_TOTAL * screenRatioX);
        height = (int) (width / wPh);
        container = Bitmap.createScaledBitmap(container, width, height, false);
    }//Constructor method

    @Override
    public void update(double updateX, double updateY) {
        x += updateX;
        y += updateY;
    }//update

    @Override
    public void draw(Canvas canvas, Paint paint) {
        Rect temp = new Rect(x, y, x + container.getWidth(), y + container.getHeight());

        canvas.drawBitmap(container, x, y, paint);
        canvas.drawRect(temp, paint);
    }//draw

    @Override
    public boolean isCollision(Rect rect) {
        Rect temp = new Rect(x, y, x + container.getWidth(), y + container.getHeight());
        return Rect.intersects(temp, rect);
    }//isCollision

    public int getWidth() {
        return width;
    }//getWidth

    public int getHeight() {
        return height;
    }//getHeight
}//BarbedWire
