package com.example.tanksbattle.model.tankobject;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.tanksbattle.model.tank.Tank;

public class Hull {
    private double x, y;
    private int maxSpeed;
    private int width, height;
    private double chord, tempAngle;
    private final double wPH;
    private Bitmap hull;
    private Matrix matrix;

    public Hull(Tank tank, int hullId, int x, int y, Resources res) {
        hull = BitmapFactory.decodeResource(res, hullId);
        width = hull.getWidth();
        height = hull.getHeight();
        wPH = (double) width / (double) height;
        width = (int) (hull.getWidth() / 3 * screenRatioX);
        height = (int) (width / wPH);
        chord = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)) / 2;
        tempAngle = Math.atan((double) width/height);
        hull = Bitmap.createScaledBitmap(hull, width, height, false);

        tank.setWidthHeight(width, height);

        this.x = x;
        this.y = y;

        matrix = new Matrix();

    }//Constructor method

    public void update(double updateX, double updateY, double angle) {
        matrix = new Matrix();
        matrix.postTranslate(-hull.getWidth()/2f, -hull.getHeight()/2f);
        matrix.postRotate((float) angle);
        x = updateX;
        y = updateY;
        matrix.postTranslate((float) x,(float) y);
        updateWidthHeight(angle);
    }//update

    public void draw(Canvas canvas, Paint paint) {
        //draw in canvas
        canvas.drawBitmap(hull, matrix, paint);
    }//draw

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void updateWidthHeight(double angle) {
        angle = angle/180*Math.PI;
        if (angle > Math.PI*1.5) {
            width = (int) (Math.cos(angle-Math.PI*1.5 - tempAngle)*chord*2);
            height = (int) (Math.sin(angle + tempAngle - Math.PI*1.5)*chord*2);
        } else if (angle > Math.PI) {
            width = (int) (Math.sin(angle - Math.PI + tempAngle)*chord*2);
            height = (int) (Math.cos(angle - tempAngle - Math.PI)*chord*2);
        }else if (angle > Math.PI/2) {
            width = (int) (Math.cos(angle-Math.PI/2 - tempAngle)*chord*2);
            height = (int) (Math.sin(angle + tempAngle - Math.PI/2)*chord*2);
        }else {
            width = (int) (Math.sin(angle+tempAngle)*chord*2);
            height = (int) (Math.cos(angle - tempAngle)*chord*2);
        }
    }
}//Hull
