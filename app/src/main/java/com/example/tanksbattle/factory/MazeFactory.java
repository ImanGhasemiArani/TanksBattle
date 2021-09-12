package com.example.tanksbattle.factory;

import android.content.res.Resources;
import android.os.Build;

import com.example.tanksbattle.constant.ConstantData;
import com.example.tanksbattle.model.object.BarbedWire;
import com.example.tanksbattle.model.object.Container;
import com.example.tanksbattle.model.object.DecorObjectInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MazeFactory {

    private ArrayList<DecorObjectInterface> decors;
    private int[][] decorsData;
    private int width1, height1, width2, height2;
    private int xMin, yMin;
    private ArrayList<int[]> path;

    public MazeFactory(Resources res, int[][] decorsData, int width, int height, int xMin, int yMin) {
        this.decorsData = decorsData;
        this.width1 = width*2/3;
        this.height1 = height*2/3;
        this.width2 = width*4/3;
        this.height2 = height*4/3;
        this.xMin = xMin;
        this.yMin = yMin;

        decors = new ArrayList<>();
        path = new ArrayList<>();

        for (int i = 0, y = yMin; i < decorsData.length; i++) {
            for (int j = 0, x = xMin; j < decorsData[0].length; j++) {
                if (decorsData[i][j] == ConstantData.BARBED_WIRE) {
                    decors.add(new BarbedWire(x, y, res));
                    x += width1;
                } else if (decorsData[i][j] == ConstantData.WALL_HORIZONTALLY) {
                    Container temp = new Container(x, y, "H", res);
                    decors.add(temp);
                    x += width2;
                } else if (decorsData[i][j] == ConstantData.WALL_VERTICALLY) {
                    Container temp = new Container(x, y, "V", res);
                    decors.add(temp);
                    x += width1;
                } else if(decorsData[i][j] == ConstantData.EMPTY || decorsData[i][j] == ConstantData.PATH) {
                    if (decorsData[i][j] == ConstantData.PATH) {
                        path.add(new int[] {y+ height1, x+ width1});
                    }

                    if (i % 2 == 0) {
                        x += width2;
                    } else {
                        if (j % 2 == 1) {
                            x += width2;
                        } else {
                            x += width1;
                        }
                    }
                }
            }
            if (i % 2 == 0) {
                y += height1;
            } else {
                y += height2;
            }
        }

    }

    public ArrayList<DecorObjectInterface> getDecors() {
        return decors;
    }

    public ArrayList<int[]> getPath() {
        return path;
    }
}
