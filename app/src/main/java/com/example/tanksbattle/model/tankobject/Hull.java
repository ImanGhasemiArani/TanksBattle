package com.example.tanksbattle.model.tankobject;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.activity.MainActivity.screenRatioY;
import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Hull {
    private int x, y;
    private int maxSpeed;
    private int width, height;
    private Bitmap hull;
    private Matrix matrix;

    public Hull(int hullId, Resources res) {
        hull = BitmapFactory.decodeResource(res, hullId);
        width = (int) (hull.getWidth() / 3 * screenRatioX);
        height = (int) (hull.getHeight() / 3 * screenRatioY);
        hull = Bitmap.createScaledBitmap(hull, width, height, false);

        x = screenX / 2;
        y = screenY / 2;

        matrix = new Matrix();

    }//Constructor method

    public void update(double updateX, double updateY, float angle) {


        matrix = new Matrix();
        matrix.postTranslate(-hull.getWidth()/2f, -hull.getHeight()/2f);
        matrix.postRotate(angle);
        matrix.postTranslate(x, y);


//        x += updateX;
//        y += updateY;

    }//update

    public void draw(Canvas canvas, Paint paint) {
        //draw in canvas

        canvas.drawBitmap(hull, matrix, paint);
    }//draw
}//Hull
