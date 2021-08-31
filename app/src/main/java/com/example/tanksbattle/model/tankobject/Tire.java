package com.example.tanksbattle.model.tankobject;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Tire {
    private double x, y;
    private int maxSpeed;
    private int width, height, swapX, swapY;
    private final double wPH;
    private Bitmap tire;
    private Matrix matrix;
    public boolean isDraw;

    public Tire(int trackAId, int x, int y, Resources res) {
        tire = BitmapFactory.decodeResource(res, trackAId);
        width = tire.getWidth();
        height = tire.getHeight();
        wPH = (double) width / (double) height;
        width = (int) (tire.getWidth() / 3 * screenRatioX);
        height = (int) (width / wPH);
        tire = Bitmap.createScaledBitmap(tire, width, height, false);

        this.x = x;
        this.y = y;
        isDraw = false;

        matrix = new Matrix();

    }//Constructor method

    public void update(double updateX, double updateY, double angle) {
        matrix = new Matrix();
        matrix.postTranslate(swapX, swapY);
        matrix.postRotate((float) angle);
        x = updateX;
        y = updateY;
        matrix.postTranslate((float) x,(float) y);
    }//update

    public void draw(Canvas canvas, Paint paint) {
        //draw in canvas
        if (isDraw) {
            canvas.drawBitmap(tire, matrix, paint);
            isDraw = false;
        }
    }//draw

    public int getWidth() {
        return width;
    }//getWidth

    public int getHeight() {
        return height;
    }//getHeight

    public void setSwap(double sx, double sy) {
        swapX = (int) sx;
        swapY = (int) sy;
    }//setSwap
}
