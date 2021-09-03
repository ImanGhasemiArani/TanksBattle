package com.example.tanksbattle.fragment;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.TransitionInflater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tanksbattle.R;
import com.example.tanksbattle.activity.MainActivity;

public class MultiplayerMenuFragment extends Fragment {

    private View view;
    private Button btnViaBluetooth, btnViaHotspot, btnViaInternet, btnBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_multiplayer_menu, container, false);

        findViewById();

        implementListeners();

        return view;
    }//onCreateView

    private void findViewById() {
        btnViaBluetooth = view.findViewById(R.id.btnMultiplayerViaBluetooth);
        btnViaHotspot = view.findViewById(R.id.btnMultiplayerViaHotSpot);
        btnViaInternet = view.findViewById(R.id.btnMultiplayerViaInternet);
        btnBack = view.findViewById(R.id.btnBack);
    }//findViewById

    private void implementListeners() {
        btnBack.setOnClickListener(e->{
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            FragmentManager fragmentManager = MainActivity.appCompatActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_holder, mainMenuFragment);
            fragmentTransaction.commit();
        });

        btnViaBluetooth.setOnClickListener(e->{
            Toast.makeText(getContext(), "Bluetooth mode will be creating in next update.", Toast.LENGTH_SHORT).show();
        });

        btnViaHotspot.setOnClickListener(e->{
            Toast.makeText(getContext(), "Hotspot mode will be creating in next update.", Toast.LENGTH_SHORT).show();
        });

        btnViaInternet.setOnClickListener(e->{
            Toast.makeText(getContext(), "Internet mode will be creating in next update.", Toast.LENGTH_SHORT).show();
        });
    }


}//MainMenuFragment