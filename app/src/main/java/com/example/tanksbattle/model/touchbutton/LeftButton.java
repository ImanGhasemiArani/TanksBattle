package com.example.tanksbattle.model.touchbutton;

import static com.example.tanksbattle.activity.MainActivity.screenRatioX;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.tanksbattle.R;

public class LeftButton extends ArrowButton{

    public LeftButton(int x, int y, Resources res) {
        super(x, y, res);
    }

    @Override
    protected void setBitmap(Resources res) {
        button = BitmapFactory.decodeResource(res, R.drawable.right_touch_button);
        width = button.getWidth() / 15;
        height = button.getHeight() / 15;
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1, button.getWidth()/2f, button.getHeight()/2f);
        button = Bitmap.createBitmap(button, 0, 0, button.getWidth(), button.getHeight(), matrix, true);
        button = Bitmap.createScaledBitmap(button, width, height, false);
        cX = x + width/2;
        cY = y + height/2;
    }

    @Override
    public void updateDown() {
        x -= 40 * screenRatioX;
    }

    @Override
    public void updateUp() {
        x += 40 * screenRatioX;
    }

}
