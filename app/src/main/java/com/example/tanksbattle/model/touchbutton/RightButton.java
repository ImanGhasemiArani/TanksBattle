package com.example.tanksbattle.model.touchbutton;


import static com.example.tanksbattle.activity.MainActivity.screenRatioX;
import static com.example.tanksbattle.activity.MainActivity.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tanksbattle.R;
import com.example.tanksbattle.model.tank.Tank;

public class RightButton extends Button {


    public RightButton(int x, int y, Resources res) {
        super(x, y, res);
    }

    @Override
    protected void setBitmap(Resources res) {
        button = BitmapFactory.decodeResource(res, R.drawable.right_touch_button);
        width = button.getWidth() / 15;
        height = button.getHeight() / 15;
        button = Bitmap.createScaledBitmap(button, width, height, false);
        cX = x + width/2;
        cY = y + height/2;
    }

    @Override
    public void pressedDown() {
        x += 40 * screenRatioX;
    }

    @Override
    public void pressedUp() {
        x -= 40 * screenRatioX;
    }

    @Override
    public void update(Tank playerTank) {
        if (isPressed) {
            playerTank.updateAngle(playerTank.getMaxSpeed() / 2f * (screenRatioX + screenRatioY) / 2f);
        }
    }

}
