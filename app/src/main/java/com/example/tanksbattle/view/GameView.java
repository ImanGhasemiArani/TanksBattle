package com.example.tanksbattle.view;

import static com.example.tanksbattle.activity.MainActivity.screenX;
import static com.example.tanksbattle.activity.MainActivity.screenY;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;


import androidx.core.view.MotionEventCompat;

import com.example.tanksbattle.R;
import com.example.tanksbattle.model.tank.Tank;
import com.example.tanksbattle.model.touchbutton.ArrowButton;
import com.example.tanksbattle.model.touchbutton.DownButton;
import com.example.tanksbattle.model.touchbutton.LeftButton;
import com.example.tanksbattle.model.touchbutton.RightButton;
import com.example.tanksbattle.model.touchbutton.UpButton;


public class GameView extends SurfaceView implements Runnable {

    private Thread gameThread;
    private boolean isPlaying;
    private final Paint paint;
//    private final Joystick joystick;
    private final ArrowButton buttons[];
//    private final UpButton upButton;
//    private final DownButton downButton;
//    private final RightButton rightButton;
//    private final LeftButton leftButton;
    private Bitmap fixBackground;
    private Tank playerTank;

    public GameView(Context context) {
        super(context);

        paint = new Paint();

        //define the joystick
//        joystick = new Joystick(screenX / 5 , screenY / 4 * 3, screenX / 15, screenX / 40);
        buttons = new ArrowButton[4];
        buttons[0] = new UpButton(screenX / 9, screenY / 6*3, getResources());
        buttons[1] = new DownButton(screenX / 9, screenY / 6*3 + screenY / 5, getResources());
        buttons[2] = new RightButton(screenX / 7 * 5 + screenX / 9, screenY / 6*3 + screenY / 5, getResources());
        buttons[3] = new LeftButton(screenX / 7 * 5, screenY / 6*3 + screenY / 5, getResources());

        setFixBackground();

        playerTank = new Tank(getResources());


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

        playerTank.update();

        //update joystick
//        joystick.update();
//        upButton.update();
//        downButton.update();
//        rightButton.update();
//        leftButton.update();

    }//update

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas= getHolder().lockCanvas();

            canvas.drawBitmap(fixBackground, 0, 0, paint);

            playerTank.draw(canvas, paint);

            //draw joystick
//            joystick.draw(canvas);
            for (ArrowButton button : buttons)
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

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                if (joystick.isPressed(event.getX(), event.getY())) {
//                    joystick.setIsPressed(true);
////                    player.setIsWalking(true);
//                }//if
//                break;
//            //case
//            case MotionEvent.ACTION_MOVE:
//                if (joystick.getIsPressed()) {
//                    joystick.setActuator(event.getX(), event.getY());
//                }//if
//                break;
//            //case
//            case MotionEvent.ACTION_UP:
//                joystick.setIsPressed(false);
//                joystick.resetActuator();
////                player.setIsWalking(false);
//                break;
//            //case
//        }//switch
//        return true;
//    }//onTouchEvent

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = MotionEventCompat.getActionIndex(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                for (ArrowButton button: buttons) {
                    if (!button.getIsPressed()) {
                        if (button.isPressed(event.getX(index), event.getY(index))) {
                            button.setIsPressed(true);
                            button.updateDown();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                for (ArrowButton button: buttons) {
                    if (button.getIsPressed()) {
                        if (button.isPressed(event.getX(index), event.getY(index))) {
                            button.setIsPressed(false);
                            button.updateUp();
                        }
                    }
                }
                break;
            //case
        }//switch
        return true;
    }//onTouchEvent



    private void setFixBackground() {
        fixBackground = BitmapFactory.decodeResource(getResources(), R.drawable.battleground);
        fixBackground = Bitmap.createScaledBitmap(fixBackground, screenX, screenY, false);
    }
}//GameView


