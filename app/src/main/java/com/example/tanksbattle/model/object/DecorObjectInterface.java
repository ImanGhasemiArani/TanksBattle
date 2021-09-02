package com.example.tanksbattle.model.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public interface DecorObjectInterface {

    void update(double updateX, double updateY);

    void draw(Canvas canvas, Paint paint);

    boolean isCollision(Rect rect);
}//DecorObjectInterface
