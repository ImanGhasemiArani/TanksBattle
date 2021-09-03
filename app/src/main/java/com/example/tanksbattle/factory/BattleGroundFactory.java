package com.example.tanksbattle.factory;

import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.tanksbattle.model.object.BarbedWire;
import com.example.tanksbattle.model.object.DecorObjectInterface;

import java.util.ArrayList;

public class BattleGroundFactory {

    private final Resources res;
    private int minX, maxX, minY, maxY;
    private final int totalX, totalY;
    private final BackgroundFactory background;
    private final ArrayList<DecorObjectInterface> decorObjects;

    public BattleGroundFactory(Resources res) {
        maxX = (int) (screenX * 1.5);
        maxY = (int) (screenY * 1.5);
        minX = (int) (screenX * 0.5) * -1;
        minY = (int) (screenY * 0.5) * -1;
        totalX = maxX - minX;
        totalY = maxY - minY;
        this.res = res;
        background = new BackgroundFactory(minX, maxX, minY, maxY, res);
        decorObjects = new ArrayList<>();

        setupBarbedWires();
    }//Constructor method

    private void setupBarbedWires() {
        BarbedWire temp = new BarbedWire(0,0,res);
        int width = temp.getWidth();
        int height = temp.getHeight();
        int counterX = totalX / width + 1;
        int counterY = totalY / height - 1;
        for (int x = minX, i = 0; i < counterX; x += width, i++) {
            decorObjects.add(new BarbedWire(x, minY, res));
            decorObjects.add(new BarbedWire(x, maxY - height, res));
        }//for
        for (int y = minY + height, i = 0; i < counterY ; y += height, i++) {
            decorObjects.add(new BarbedWire(minX, y, res));
            decorObjects.add(new BarbedWire(maxX - width, y, res));
        }//for
    }//setupBarbedWires

    public boolean[] update(double xUpdate, double yUpdate) {
        boolean bX = false, bY = false;
        if ((xUpdate < 0 && maxX + xUpdate >= screenX) || (xUpdate > 0 && minX + xUpdate <= 0)) {
            minX += xUpdate;
            maxX += xUpdate;
            bX = true;
        }//if

        if ((yUpdate < 0 && maxY + yUpdate >= screenY) || (yUpdate > 0 && minY + yUpdate <= 0)) {
            minY += yUpdate;
            maxY += yUpdate;
            bY = true;
        }//if

        double iX = 0, iY = 0;
        if (bX) {
            iX = xUpdate;
        }//if
        if (bY) {
            iY = yUpdate;
        }//if

        background.update(iX, iY);
        for (DecorObjectInterface object : decorObjects) {
            object.update(iX, iY);
        }//for
        return new boolean[]{bX, bY};
    }//update

    public void draw(Canvas canvas, Paint paint) {
        background.draw(canvas, paint);
        for (DecorObjectInterface object : decorObjects) {
            object.draw(canvas, paint);
        }//for
    }//draw

    public boolean isCollision(Rect rect) {
        for (DecorObjectInterface o: decorObjects) {
            if (o.isCollision(rect))
                return true;
        }//for
        return false;
    }//isCollision
}//BattleGroundFactory
