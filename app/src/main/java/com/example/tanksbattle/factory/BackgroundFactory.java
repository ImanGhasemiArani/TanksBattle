package com.example.tanksbattle.factory;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tanksbattle.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class BackgroundFactory {

    private Bitmap[] grounds;
    private int x, y, counterX, counterY;
    private int maxX, maxY, minX, minY, width, height;
    private int[][] ground;


    public BackgroundFactory(int minX, int maxX, int minY, int maxY, Resources res) {
        grounds = new Bitmap[2];
        grounds[0] = BitmapFactory.decodeResource(res, Image.GROUND_TILE[0]);
        grounds[1] = BitmapFactory.decodeResource(res, Image.GROUND_TILE[1]);
        width = grounds[0].getWidth();
        height = grounds[0].getHeight();
        double wPh = (float) width / (float) height;
        width = (int) (width / 3 * screenRatioX);
        height = (int) (width / wPh);
        grounds[0] = Bitmap.createScaledBitmap(grounds[0], width, height, false);
        grounds[1] = Bitmap.createScaledBitmap(grounds[1], width, height, false);

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        counterX = screenX * 2 / width;
        counterY = screenY * 2 /height;

        Random r = new Random();
        ground = new int[counterY][counterX];
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[0].length; j++) {
                ground[i][j] = r.nextInt(2);
            }//inner for
        }//outer for

    }//Constructor method

    public void update(double xUpdate, double yUpdate) {
        minX += xUpdate;
        maxX += xUpdate;
        minY += yUpdate;
        maxY += yUpdate;
    }//update

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0, x = minX; i < counterX; x += width, i++) {
            for (int j = 0, y = minY; j < counterY; y += height, j++) {
                canvas.drawBitmap(grounds[ground[j][i]], x, y, paint);
            }//inner for
        }//outer for
    }//draw
}//BackgroundFactory
