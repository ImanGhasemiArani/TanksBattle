package com.example.tanksbattle.fragment;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tanksbattle.R;
import com.example.tanksbattle.activity.MainActivity;
import com.example.tanksbattle.factory.BattlegroundBaseFactory;

public class MainMenuFragment extends Fragment {

    private View view;
    private Button btnMultiplayer, btnSinglePlayer, btnStore, btnSetting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        findViewById();

        implementListeners();

        return view;
    }//onCreateView

    private void findViewById() {
        btnMultiplayer = view.findViewById(R.id.btnMultiplayer);
        btnSinglePlayer = view.findViewById(R.id.btnSingleplayer);
        btnStore = view.findViewById(R.id.btnStore);
        btnSetting = view.findViewById(R.id.btnSetting);
    }//findViewById

    private void implementListeners() {
        btnMultiplayer.setOnClickListener(e->{
            MultiplayerMenuFragment multiplayerMenuFragment = new MultiplayerMenuFragment();
            FragmentManager fragmentManager = MainActivity.appCompatActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_holder, multiplayerMenuFragment);
            fragmentTransaction.commit();
        });

        btnSinglePlayer.setOnClickListener(e->{
            Toast.makeText(getContext(), "Single player mode will be creating in next update.", Toast.LENGTH_SHORT).show();
        });

        btnStore.setOnClickListener(e->{
            Toast.makeText(getContext(), "Store will be creating in next update.", Toast.LENGTH_SHORT).show();
        });

        btnSetting.setOnClickListener(e->{
            Toast.makeText(getContext(), "Setting will be creating in next update.", Toast.LENGTH_SHORT).show();
        });

    }


}//MainMenuFragment