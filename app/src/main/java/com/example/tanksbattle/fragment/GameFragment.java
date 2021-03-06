package com.example.tanksbattle.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tanksbattle.activity.MainActivity;
import com.example.tanksbattle.factory.BattlegroundBaseFactory;
import com.example.tanksbattle.view.GameView;

public class GameFragment extends Fragment {

    private GameView gameView;
    private BattlegroundBaseFactory battleGroundBaseFactory;

    public GameFragment(BattlegroundBaseFactory battleGroundBaseFactory) {
        this.battleGroundBaseFactory = battleGroundBaseFactory;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gameView = new GameView(MainActivity.appCompatActivity, battleGroundBaseFactory);

        return gameView;
    }//onCreateView

    @Override
    public void onPause() {
        super.onPause();
        gameView.pause();
    }//onPause

    @Override
    public void onResume() {
        super.onResume();
        gameView.resume();
    }//onResume

}//LoadingFragment