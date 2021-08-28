package com.example.tanksbattle.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanksbattle.R;
import com.example.tanksbattle.activity.MainActivity;

public class MainMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        view.findViewById(R.id.startGameButton).setOnClickListener(e->{
            GameFragment gameFragment = new GameFragment();
            FragmentManager fragmentManager = MainActivity.appCompatActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_holder, gameFragment);
            fragmentTransaction.commit();
        });

        return view;
    }//onCreateView
}//MainMenuFragment