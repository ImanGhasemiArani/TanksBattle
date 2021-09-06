package com.example.tanksbattle.fragment;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tanksbattle.R;
import com.example.tanksbattle.activity.MainActivity;
import com.example.tanksbattle.factory.BattlegroundBaseFactory;
import com.example.tanksbattle.multiplayer.bluetooth.BluetoothHandler;

public class BluetoothFragment extends Fragment {

    private View view;
    private Button btnCreateServer, btnJoinServer, btnBack;
    private ListView lvDevices;
    private TextView tvConnectionStatus;

    private BluetoothHandler bluetoothHandler;
    private BluetoothDevice[] devices;

    private BattlegroundBaseFactory battlegroundBaseFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bluetooth, container, false);

        findViewById();

        implementListeners();

        bluetoothHandler = new BluetoothHandler(handler);

        bluetoothHandler.enableBluetooth();

        return view;
    }//onCreateView

    private void findViewById() {
        btnCreateServer = view.findViewById(R.id.btnCreateServer);
        btnJoinServer = view.findViewById(R.id.btnJoinServer);
        btnBack = view.findViewById(R.id.btnBack);
        lvDevices = view.findViewById(R.id.lvDevices);
        tvConnectionStatus = view.findViewById(R.id.tvConnectionStatus);
    }//findViewById

    private void implementListeners() {
        btnBack.setOnClickListener(e->{
            bluetoothHandler.disableBluetooth();
            MultiplayerMenuFragment multiplayerMenuFragment = new MultiplayerMenuFragment();
            FragmentManager fragmentManager = MainActivity.appCompatActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_holder, multiplayerMenuFragment);
            fragmentTransaction.commit();
        });

        btnCreateServer.setOnClickListener(e->{
            btnCreateServer.setEnabled(false);
            btnJoinServer.setEnabled(false);
            bluetoothHandler.createServer();
        });

        btnJoinServer.setOnClickListener(e->{
            ArrayAdapter<String> arrayAdapter= bluetoothHandler.getPairedDevices();
            devices = bluetoothHandler.getDevices();
            if (arrayAdapter != null) {
                lvDevices.setAdapter(arrayAdapter);
            }

        });

        lvDevices.setOnItemClickListener((adapterView, view, i, l) -> bluetoothHandler.createClient(devices[i]));

//        btnBack.setOnClickListener(e->{
//            String str = String.valueOf(editText.getText());
//            bluetoothHandler.send(str);
//        });


    }

    private final Handler handler = new Handler(msg -> {
        switch (msg.what) {
            case BluetoothHandler.STATE_LISTENING:
                tvConnectionStatus.setText("Listening");
                break;
            case BluetoothHandler.STATE_CONNECTING:
                tvConnectionStatus.setText("Connecting");
                break;
            case BluetoothHandler.STATE_CONNECTED:
                tvConnectionStatus.setText("Connected");
                break;
            case BluetoothHandler.STATE_CONNECTION_FAILED:
                tvConnectionStatus.setText("Connection Failed");
                break;
            case BluetoothHandler.STATE_MESSAGE_RECEIVED:
                BattlegroundBaseFactory temp = bluetoothHandler.receiveData((byte[]) msg.obj, msg.arg1);
                if (temp != null) {
                    battlegroundBaseFactory = temp;
                    LoadingFragment loadingFragment = new LoadingFragment(battlegroundBaseFactory);
                    FragmentManager fragmentManager = MainActivity.appCompatActivity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_holder, loadingFragment);
                    fragmentTransaction.commit();
                }else {
                    System.out.println("null");
                }



                break;
        }
        return false;
    });


}//MainMenuFragment