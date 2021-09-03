package com.example.tanksbattle.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tanksbattle.R;
import com.example.tanksbattle.activity.MainActivity;
import com.example.tanksbattle.factory.BattleGroundFactory;

public class LoadingFragment extends Fragment {

    private ProgressBar pbLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loading, container, false);

        pbLoading = view.findViewById(R.id.pbLoading);
//        pbLoading.setProgress(0);

        new LoadGame().start();

        return view;
    }//onCreateView

    private class LoadGame extends Thread {

        @Override
        public void run() {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GameFragment gameFragment = new GameFragment();

            pbLoading.setProgress(50);

            gameFragment.setBattleGroundFactory(new BattleGroundFactory(getResources()));
            FragmentManager fragmentManager = MainActivity.appCompatActivity.getSupportFragmentManager();

            pbLoading.setProgress(75);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            pbLoading.setProgress(100);

            fragmentTransaction.replace(R.id.frame_holder, gameFragment);

            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fragmentTransaction.commit();
        }
    }


}//LoadingFragment