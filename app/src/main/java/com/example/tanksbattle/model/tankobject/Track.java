package com.example.tanksbattle.model.tankobject;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;


public class Track {
    private double x, y;
    private int maxSpeed;
    private int width, height, swapX, swapY;
    private final double wPH;
    private Bitmap track;
    private Matrix matrix;

    public Track(int trackId, int x, int y, Resources res) {
        track = BitmapFactory.decodeResource(res, trackId);
        width = track.getWidth();
        height = track.getHeight();
        wPH = (double) width / (double) height;
        width = (int) (track.getWidth() / 3 * screenRatioX);
        height = (int) (width / wPH);
        track = Bitmap.createScaledBitmap(track, width, height, false);

        this.x = x;
        this.y = y;

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
        canvas.drawBitmap(track, matrix, paint);
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
