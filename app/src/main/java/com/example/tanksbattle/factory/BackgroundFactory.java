package com.example.tanksbattle.factory;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;
import static com.example.tanksbattle.constant.ConstantData.IMAGE_RATIO_TOTAL;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tanksbattle.image.Image;

public class BackgroundFactory {

    private Bitmap[] grounds;
    private int[][] blocks;
    private int width, height;
    private int xMin, xMax, yMin, yMax, zeroXP, zeroYP;

    public BackgroundFactory(Resources res, int[][] blocks) {
        this.blocks = blocks;
        grounds = new Bitmap[2];
        grounds[0] = BitmapFactory.decodeResource(res, Image.GROUND_TILE[0]);
        grounds[1] = BitmapFactory.decodeResource(res, Image.GROUND_TILE[1]);
        width = grounds[0].getWidth();
        height = grounds[0].getHeight();
        double wPh = (float) width / (float) height;
        width = (int) (width * IMAGE_RATIO_TOTAL * screenRatioX);
        height = (int) (width / wPh);
        grounds[0] = Bitmap.createScaledBitmap(grounds[0], width, height, false);
        grounds[1] = Bitmap.createScaledBitmap(grounds[1], width, height, false);

        xMin = -blocks[0].length / 4 * width;
        xMax = blocks[0].length * 3 / 4 * width;
        yMin = -blocks.length / 4 * height;
        yMax = blocks.length * 3 / 4 * height;

        zeroXP = blocks[0].length / 4 - 1;
        zeroYP = blocks.length / 4 - 1;

        System.out.println(xMin + " " + xMax + " " + yMin + " " + yMax);
        System.out.println(xMin + " " + (xMax - screenX) + " " + yMin + " " + (yMax - screenY));

    }//Constructor method

    public void update(double xUpdate, double yUpdate) {
        xMin += xUpdate;
        xMax += xUpdate;
        yMin += yUpdate;
        yMax += yUpdate;
    }//update

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0, x = xMin; i < blocks[0].length; i++, x += width) {
            for (int j = 0, y = yMin; j < blocks.length; j++, y += height) {
                canvas.drawBitmap(grounds[blocks[j][i]], x, y, paint);
            }//inner for
        }//outer for
    }//draw

    public int getxMin() {
        return xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMin() {
        return yMin;
    }

    public int getyMax() {
        return yMax;
    }

    public int getZeroXP() {
        return zeroXP;
    }

    public int getZeroYP() {
        return zeroYP;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] setupPosition(String upDown, String leftRight, int xTank, int yTank) {
        int xSwap = 0;
        int ySwap = 0;
        if (upDown.equals("U")) {
            ySwap = -yTank + screenY / 4;
            yMin += ySwap;
            yMax += ySwap;
        } else if (upDown.equals("D")) {
            ySwap = -(yTank - screenY) - screenY / 4;
            yMin += ySwap;
            yMax += ySwap;
        }

        if (leftRight.equals("L")) {
            xSwap = -xTank + screenX / 4;
            xMin += xSwap;
            xMax += xSwap;
        } else if((leftRight.equals("R"))) {
            xSwap = -(xTank - screenX) - screenX / 4;
            xMin += xSwap;
            xMax += xSwap;
        }

        return new int[] {xSwap, ySwap};
    }
}//BackgroundFactory
