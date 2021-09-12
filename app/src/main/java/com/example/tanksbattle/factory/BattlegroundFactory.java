package com.example.tanksbattle.factory;

import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.tanksbattle.model.object.DecorObjectInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class BattlegroundFactory {

    private final Resources res;
    private BackgroundFactory backgroundFactory;
    private ArrayList<DecorObjectInterface> decorObjects;
    private int xPTank, yPTank, xMin, xMax, yMin, yMax, zeroXP, zeroYP, width, height;
    private ArrayList<int[]> path;

    public BattlegroundFactory(Resources res, BattlegroundBaseFactory battleGroundBaseFactory) {
        this.res = res;
        backgroundFactory = new BackgroundFactory(res, battleGroundBaseFactory.getBackgroundBlocksData());
        setMinMaxZeroPos();
        decorObjects = new ArrayList<>();
//        BarbedWireFactory barbedWireFactory= new BarbedWireFactory(res, battleGroundBaseFactory.getBarbedWiresData(), width, height, xMin, yMin);
//        decorObjects.addAll(barbedWireFactory.getBarbedWires());

        MazeFactory mazeFactory = new MazeFactory(res, battleGroundBaseFactory.getDecorsData(), width, height, xMin, yMin);
        decorObjects.addAll(mazeFactory.getDecors());
        path = mazeFactory.getPath();

        generatePositionTank();

        setupFirstPositionOfObjects();

    }

    private void generatePositionTank() {
        int[] yx = path.get(new Random().nextInt(path.size()));
        xPTank = yx[1];
        yPTank = yx[0];

        while (!(xPTank < screenX * 3 / 2 && xPTank > -screenX / 2 &&
                yPTank < screenY * 3 / 2 && yPTank > -screenY / 2)) {
            yx = path.get(new Random().nextInt(path.size()));
            xPTank = yx[1];
            yPTank = yx[0];
        }

    }

    private void setupFirstPositionOfObjects() {
        String upDown = "NON", leftRight = "NON";
        if (xPTank < 0) {
            leftRight = "L";
        } else if (xPTank > screenX) {
            leftRight = "R";
        }

        if (yPTank < 0) {
            upDown = "U";
        } else if (yPTank > screenY) {
            upDown = "D";
        }

        int[] temp = backgroundFactory.setupPosition(upDown, leftRight, xPTank, yPTank);
        int xSwap = temp[0], ySwap = temp[1];

        System.out.println("\n\nscreens"+screenX + " " + screenY);
        System.out.println("tank" + xPTank +" " + yPTank);
        System.out.println("swaps" + xSwap +" " + ySwap);
        xPTank += xSwap;
        yPTank += ySwap;

        System.out.println("tank" +xPTank +" " + yPTank);

        for (DecorObjectInterface object : decorObjects) {
            object.update(xSwap, ySwap);
        }//for
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

    public int getXPTank() {
        return xPTank;
    }

    public int getYPTank() {
        return yPTank;
    }
}
