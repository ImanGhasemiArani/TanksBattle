package com.example.tanksbattle.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.tanksbattle.R;
import com.example.tanksbattle.fragment.MainMenuFragment;

public class MainActivity extends AppCompatActivity {

    public static int screenX, screenY;
    public static double screenRatioX, screenRatioY;
    public static AppCompatActivity appCompatActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        appCompatActivity = this;

        Point screenSize = new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(screenSize);
        screenX = screenSize.x;
        screenY = screenSize.y;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_holder, mainMenuFragment);
        fragmentTransaction.commit();
    }//onCreate
}//MainActivity