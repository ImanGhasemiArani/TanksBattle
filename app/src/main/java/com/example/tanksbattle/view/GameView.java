package com.example.tanksbattle.view;

import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;


import androidx.core.view.MotionEventCompat;

import com.example.tanksbattle.factory.BattlegroundBaseFactory;
import com.example.tanksbattle.factory.BattlegroundFactory;
import com.example.tanksbattle.model.tank.Tank;
import com.example.tanksbattle.model.touchbutton.Button;
import com.example.tanksbattle.model.touchbutton.DownButton;
import com.example.tanksbattle.model.touchbutton.LeftButton;
import com.example.tanksbattle.model.touchbutton.RightButton;
import com.example.tanksbattle.model.touchbutton.UpButton;


public class GameView extends SurfaceView implements Runnable {

    private BattlegroundBaseFactory battleGroundBaseFactory;
    private Thread gameThread;
    private boolean isPlaying;
    private final Paint paint;
    private final Button buttons[];
    private BattlegroundFactory battlegroundFactory;
    private Tank playerTank;

    public GameView(Context context, BattlegroundBaseFactory battleGroundBaseFactory) {
        super(context);

        paint = new Paint();

        battlegroundFactory = new BattlegroundFactory(getResources(), battleGroundBaseFactory);

        buttons = new Button[4];
        buttons[0] = new UpButton(screenX / 9, screenY / 6*3, getResources());
        buttons[1] = new DownButton(screenX / 9, screenY / 6*3 + screenY / 5, getResources());
        buttons[2] = new RightButton(screenX / 7 * 5 + screenX / 9, screenY / 6*3 + screenY / 5, getResources());
        buttons[3] = new LeftButton(screenX / 7 * 5, screenY / 6*3 + screenY / 5, getResources());

        playerTank = new Tank(battlegroundFactory.getXPTank(), battlegroundFactory.getYPTank(), getResources(), battlegroundFactory);


    }//Constructor method

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }//while
    }//run

    private void update() {

        playerTank.update(buttons);

    }//update

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas= getHolder().lockCanvas();

            battlegroundFactory.draw(canvas, paint);

            playerTank.draw(canvas, paint);

            //draw joystick
            for (Button button : buttons)
                button.draw(canvas, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }//if
    }//draw

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//try catch
    }//sleep

    public void pause() {
        try {
            isPlaying = false;
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//try catch
    }//pause

    public void resume() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }//resume

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = MotionEventCompat.getActionIndex(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                for (Button button: buttons) {
                    if (!button.getIsPressed()) {
                        if (button.isPressed(event.getX(index), event.getY(index))) {
                            button.setIsPressed(true);
                            button.pressedDown();
                        }
                    }
                }
                if (playerTank.getGunRotating() == 0 && !buttons[2].getIsPressed() && !buttons[3].getIsPressed()) {
                    if (event.getX(index) > screenX*5/6f) {
                        playerTank.setGunRotating(1);
                    }else if (event.getX(index) > screenX*4/6f && event.getX(index) < screenX*5/6f) {
                        playerTank.setGunRotating(2);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                for (Button button: buttons) {
                    if (button.getIsPressed()) {
                        if (button.isPressed(event.getX(index), event.getY(index))) {
                            button.setIsPressed(false);
                            button.pressedUp();
                        }
                    }
                }
                if (playerTank.getGunRotating() != 0 && event.getX(index) > screenX*4/6f && event.getX(index) < screenX) {
                    playerTank.setGunRotating(0);
                }
                break;
            //case
        }//switch
        return true;
    }//onTouchEvent

}//GameView


