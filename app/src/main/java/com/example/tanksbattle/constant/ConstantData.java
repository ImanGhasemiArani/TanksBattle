package com.example.tanksbattle.constant;

import com.example.tanksbattle.image.Image;

public class ConstantData {
    public static final int X_COUNTER_OF_BLOCK_BACKGROUND = 24;
    public static final int Y_COUNTER_OF_BLOCK_BACKGROUND = 12;

    public static final double IMAGE_RATIO_TOTAL = 1;
    public static final double IMAGE_RATIO = 1 / 3f;

    public static final int EMPTY = 0;
    public static final int BARBED_WIRE = 1;
    public static final int WALL_HORIZONTALLY = 2;
    public static final int WALL_VERTICALLY = 3;
    public static final int PATH = 4;

    public static int currentTrackA = Image.TANK_TRACK_A[0];
    public static int currentTrackB = Image.TANK_TRACK_B[0];
    public static int currentHull = Image.TANK_HULL[0];
    public static int currentGun = Image.TANK_GUN[0];
    public static int currentTire = Image.TANK_TIRE[0];
    public static int currentMovingFire = Image.OTHER_OBJECTS[0];


}
