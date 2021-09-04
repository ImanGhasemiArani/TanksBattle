package com.example.tanksbattle.factory;

import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.tanksbattle.model.object.DecorObjectInterface;

import java.util.ArrayList;

public class BattlegroundFactory {

    private final Resources res;
    private BackgroundFactory backgroundFactory;
    private ArrayList<DecorObjectInterface> decorObjects;
    private int xMin, xMax, yMin, yMax, zeroXP, zeroYP, width, height;

    public BattlegroundFactory(Resources res, BattlegroundBaseFactory battleGroundBaseFactory) {
        this.res = res;
        backgroundFactory = new BackgroundFactory(res, battleGroundBaseFactory.getBackgroundBlocksData());
        setMinMaxZeroPos();
        decorObjects = new ArrayList<>();
        BarbedWireFactory barbedWireFactory= new BarbedWireFactory(res, battleGroundBaseFactory.getBarbedWiresData(), width, height, xMin, yMin);
        decorObjects.addAll(barbedWireFactory.getBarbedWires());
    }

    private void setMinMaxZeroPos() {
        xMin = backgroundFactory.getxMin();
        xMax = backgroundFactory.getxMax();
        yMin = backgroundFactory.getyMin();
        yMax = backgroundFactory.getyMax();
        zeroXP = backgroundFactory.getZeroXP();
        zeroYP = backgroundFactory.getZeroYP();
        width = backgroundFactory.getWidth() / 2;
        height = backgroundFactory.getHeight() / 2;
    }

    public boolean[] update(double xUpdate, double yUpdate) {
        boolean bX = false, bY = false;
        if ((xUpdate < 0 && xMax + xUpdate >= screenX) || (xUpdate > 0 && xMin + xUpdate <= 0)) {
            xMin += xUpdate;
            xMax += xUpdate;
            bX = true;
        }//if

        if ((yUpdate < 0 && yMax + yUpdate >= screenY) || (yUpdate > 0 && yMin + yUpdate <= 0)) {
            yMin += yUpdate;
            yMax += yUpdate;
            bY = true;
        }//if

        double iX = 0, iY = 0;
        if (bX) {
            iX = xUpdate;
        }//if
        if (bY) {
            iY = yUpdate;
        }//if

        backgroundFactory.update(iX, iY);
        for (DecorObjectInterface object : decorObjects) {
            object.update(iX, iY);
        }//for
        return new boolean[]{bX, bY};
    }//update

    public void draw(Canvas canvas, Paint paint) {
        backgroundFactory.draw(canvas, paint);
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
}
