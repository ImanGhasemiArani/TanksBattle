package com.example.tanksbattle.model.tankobject;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.tanksbattle.model.tank.Tank;

public class Hull {
    private double x, y;
    private int maxSpeed;
    private int width, height;
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
    }//update

    public void draw(Canvas canvas, Paint paint) {
        //draw in canvas
        canvas.drawBitmap(hull, matrix, paint);
    }//draw
}//Hull
