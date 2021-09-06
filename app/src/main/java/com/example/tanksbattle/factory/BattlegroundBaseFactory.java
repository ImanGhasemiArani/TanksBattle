package com.example.tanksbattle.factory;

import static com.example.tanksbattle.constant.ConstantData.X_COUNTER_OF_BLOCK_BACKGROUND;
import static com.example.tanksbattle.constant.ConstantData.Y_COUNTER_OF_BLOCK_BACKGROUND;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class BattlegroundBaseFactory implements Serializable {


    private int[][] backgroundBlocks;
    private int[][] barbedWires;
    private int xPTank, yPTank;


    public BattlegroundBaseFactory() {
        generateBackgroundData();
        generateBarbedWiresData();
        generateTank();
    }

    public BattlegroundBaseFactory(String str) {
        importData(str);
    }

    private void generateTank() {
        xPTank = 100;
        yPTank = 100;
    }

    private void generateBarbedWiresData() {
        barbedWires = new int[Y_COUNTER_OF_BLOCK_BACKGROUND * 2][X_COUNTER_OF_BLOCK_BACKGROUND * 2];
        Arrays.fill(barbedWires[0], 1);
        Arrays.fill(barbedWires[Y_COUNTER_OF_BLOCK_BACKGROUND*2-1], 1);
        for (int i = 1; i < barbedWires.length - 1; i++) {
            Arrays.fill(barbedWires[i], 0);
            barbedWires[i][0] = barbedWires[i][X_COUNTER_OF_BLOCK_BACKGROUND*2-1] = 1;
        }

    }

    private void generateBackgroundData() {

        backgroundBlocks = new int[Y_COUNTER_OF_BLOCK_BACKGROUND][X_COUNTER_OF_BLOCK_BACKGROUND];
        Random r = new Random();
        for (int j = 0; j < backgroundBlocks.length; j++) {
            for (int i = 0; i < backgroundBlocks[0].length; i++) {
                backgroundBlocks[j][i] = r.nextInt(2);
            }//inner for
        }//outer for

    }

    public int[][] getBackgroundBlocksData() {
        return backgroundBlocks;
    }

    public int[][] getBarbedWiresData() {
        return barbedWires;
    }

    public int getxPTank() {
        return xPTank;
    }

    public int getyPTank() {
        return yPTank;
    }

    @Override
    public String toString() {
        String str = "start\n";

        str += backgroundBlocks.length + " " + backgroundBlocks[0].length + " #\n";
        for (int i = 0; i < backgroundBlocks.length; i++) {
            for (int j = 0; j < backgroundBlocks[0].length; j++) {
                str += backgroundBlocks[i][j] + " ";
            }
            str += "\n";
        }
        str += "#";

        str += barbedWires.length + " " + barbedWires[0].length + " #\n";
        for (int i = 0; i < barbedWires.length; i++) {
            for (int j = 0; j < barbedWires[0].length; j++) {
                str += barbedWires[i][j] + " ";
            }
            str += "\n";
        }
        str += "#";

        str += xPTank + " " + yPTank + "#";
        str += " end";

        return str;
    }

    private void importData(String str) {

        str = str.substring(6, str.length() - 3);

//        String[] lines = str.split("#");
        System.out.println("###############################");
        System.out.println(str);
        System.out.println("###############################");



    }
}
