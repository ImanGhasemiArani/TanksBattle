package com.example.tanksbattle.model.touchbutton;


import static com.example.tanksbattle.activity.MainActivity.screenRatioX;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tanksbattle.R;

public class RightButton extends ArrowButton{


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
    public void updateDown() {
        x += 40 * screenRatioX;
    }

    @Override
    public void updateUp() {
        x -= 40 * screenRatioX;
    }

}
