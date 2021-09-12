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

public class Gun {
    private double x, y;
    private int maxSpeed;
    private int width, height, swap;
    private final double wPH;
    private Bitmap gun;
    private Matrix matrix;

    public Gun(int gunId, int x, int y, Resources res) {
        gun = BitmapFactory.decodeResource(res, gunId);
        width = gun.getWidth();
        height = gun.getHeight();
        wPH = (double) width / (double) height;
        width = (int) (gun.getWidth() * IMAGE_RATIO * IMAGE_RATIO_TOTAL * screenRatioX);
        height = (int) (width / wPH);
        gun = Bitmap.createScaledBitmap(gun, width, height, false);

        this.x = x;
        this.y = y;

        matrix = new Matrix();

    }//Constructor method

    public void update(double updateX, double updateY,double angle, double gunAngle) {

        matrix = new Matrix();
        matrix.postTranslate(-gun.getWidth()/2f, -gun.getHeight()*2.4f/4f);
        matrix.postRotate((float) gunAngle);


        matrix.postTranslate(0, gun.getHeight()*2.4f/4f - gun.getHeight()/2f);
        matrix.postRotate((float) angle);
        x = updateX;
        y = updateY;
        matrix.postTranslate((float) x,(float) y);
    }//update

    public void draw(Canvas canvas, Paint paint) {
        //draw in canvas
        canvas.drawBitmap(gun, matrix, paint);
    }//draw

    public void setSwap(double swap) {
        this.swap = (int) swap;
    }//setSwap
}
