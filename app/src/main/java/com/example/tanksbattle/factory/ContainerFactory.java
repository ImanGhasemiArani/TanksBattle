package com.example.tanksbattle.factory;

import android.content.res.Resources;

import com.example.tanksbattle.constant.ConstantData;
import com.example.tanksbattle.model.object.Container;

import java.util.ArrayList;

public class ContainerFactory {

    private ArrayList<Container> containers;
    private int[][] containersData;
    private int width, height;
    private int xMin, yMin;

    public ContainerFactory(Resources res, int[][] containersData, int width, int height, int xMin, int yMin) {
        this.containersData = containersData;
        this.width = width*2;
        this.height = height*2;
        this.xMin = xMin;
        this.yMin = yMin;

        containers = new ArrayList<>();

        for (int i = 0, y = yMin; i < containersData.length; i++, y += height) {
            for (int j = 0, x = xMin; j < containersData[0].length; j++, x += width) {
                if (containersData[i][j] == ConstantData.WALL_HORIZONTALLY) {
                    containers.add(new Container(x, y, "V", res));
                }
            }
        }
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }
}
