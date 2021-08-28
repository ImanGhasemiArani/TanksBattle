package com.example.tanksbattle.view;

import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.tanksbattle.joystick.Joystick;


public class GameView extends SurfaceView implements Runnable {

    private Thread gameThread;
    private boolean isPlaying;
    private final Paint paint;
    private final Joystick joystick;

    public GameView(Context context) {
        super(context);

        paint = new Paint();

        //define the joystick
        joystick = new Joystick(screenX / 5 , screenY / 4 * 3, screenX / 15, screenX / 40);


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

        //update joystick
        joystick.update();


    }//update

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas= getHolder().lockCanvas();

            //draw joystick
            joystick.draw(canvas);

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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (joystick.isPressed(event.getX(), event.getY())) {
                    joystick.setIsPressed(true);
//                    player.setIsWalking(true);
                }//if
                break;
            //case
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    joystick.setActuator(event.getX(), event.getY());
                }//if
                break;
            //case
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
//                player.setIsWalking(false);
                break;
            //case
        }//switch
        return true;
    }//onTouchEvent
}//GameView


