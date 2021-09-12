package com.example.tanksbattle.model.tankobject;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.constant.ConstantData.IMAGE_RATIO;
import static com.example.tanksbattle.constant.ConstantData.IMAGE_RATIO_TOTAL;

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
    public int wingCounter;
    private final double wPH;
    private Bitmap trackA, trackB;
    private Matrix matrix;

    public Track(int trackAId, int trackBId, int x, int y, Resources res) {
        trackA = BitmapFactory.decodeResource(res, trackAId);
        trackB = BitmapFactory.decodeResource(res, trackBId);
        width = trackA.getWidth();
        height = trackA.getHeight();
        wPH = (double) width / (double) height;
        width = (int) (trackA.getWidth() * IMAGE_RATIO * IMAGE_RATIO_TOTAL * screenRatioX);
        height = (int) (width / wPH);
        trackA = Bitmap.createScaledBitmap(trackA, width, height, false);
        trackB = Bitmap.createScaledBitmap(trackB, width, height, false);

        this.x = x;
        this.y = y;
        wingCounter = 0;

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

    private Bitmap getTrack() {
        if (wingCounter == 0) {
            return trackA;
        }
        return trackB;
    }

    public void draw(Canvas canvas, Paint paint) {
        //draw in canvas
        canvas.drawBitmap(getTrack(), matrix, paint);
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
