package com.example.tanksbattle.factory;

import android.content.res.Resources;

import com.example.tanksbattle.constant.ConstantData;
import com.example.tanksbattle.model.object.BarbedWire;

import java.util.ArrayList;

public class BarbedWireFactory {

    private ArrayList<BarbedWire> barbedWires;
    private int[][] barbedWiresData;
    private int width, height;
    private int xMin, yMin;

    public BarbedWireFactory(Resources res, int[][] barbedWiresData, int width, int height, int xMin, int yMin) {
        this.barbedWiresData = barbedWiresData;
        this.width = width;
        this.height = height;
        this.xMin = xMin;
        this.yMin = yMin;

        barbedWires = new ArrayList<>();

        for (int i = 0, y = yMin; i < barbedWiresData.length; i++, y += height) {
            for (int j = 0, x = xMin; j < barbedWiresData[0].length; j++, x += width) {
                if (barbedWiresData[i][j] == ConstantData.BARBED_WIRE) {
                    barbedWires.add(new BarbedWire(x, y, res));
                }
            }
        }
    }

    public ArrayList<BarbedWire> getBarbedWires() {
        return barbedWires;
    }
}
