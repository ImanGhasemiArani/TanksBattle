package com.example.tanksbattle.model.touchbutton;

import static com.example.tanksbattle.activity.MainActivity.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.tanksbattle.R;

public class DownButton extends ArrowButton {

    public DownButton(int x, int y, Resources res) {
        super(x, y, res);
    }

    @Override
    protected void setBitmap(Resources res) {
        button = BitmapFactory.decodeResource(res, R.drawable.up_touch_button);
        width = button.getWidth() / 15;
        height = button.getHeight() / 15;
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1, button.getWidth()/2f, button.getHeight()/2f);
        button = Bitmap.createBitmap(button, 0, 0, button.getWidth(), button.getHeight(), matrix, true);
        button = Bitmap.createScaledBitmap(button, width, height, false);
        cX = x + width/2;
        cY = y + height/2;
    }

    @Override
    public void updateDown() {
        y += 40 * screenRatioY;
    }

    @Override
    public void updateUp() {
        y -= 40 * screenRatioY;
    }
}
