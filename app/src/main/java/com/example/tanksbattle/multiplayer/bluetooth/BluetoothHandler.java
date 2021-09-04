package com.example.tanksbattle.multiplayer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tanksbattle.R;
import com.example.tanksbattle.activity.MainActivity;
import com.example.tanksbattle.factory.BattleGroundFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.UUID;

public class BluetoothHandler {

    private final AppCompatActivity activity;
    private final Handler handler;

    private final BluetoothAdapter myBluetoothAdapter;
    private BluetoothDevice[] devices;

    private SendReceive sendReceive;

    public static final int STATE_CONNECTING = 2;
    public static final int STATE_LISTENING = 1;
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTION_FAILED = 4;
    public static final int STATE_MESSAGE_RECEIVED = 5;

    int REQUEST_ENABLE_BLUETOOTH = 1;

    public static final String APP_NAME = "MultiplayerViaBluetooth";
    public static final UUID MY_UUID = UUID.fromString("8ce255c0-223a-11e0-ac64-0803450c9a66");

    public BluetoothHandler(Handler handler) {
        activity = MainActivity.appCompatActivity;
        this.handler = handler;
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (myBluetoothAdapter == null) {
            //bluetooth is not supported on device
        }
    }

    public void enableBluetooth() {
        if (!myBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableIntent, REQUEST_ENABLE_BLUETOOTH);
        }
    }

    public void disableBluetooth() {
        if (myBluetoothAdapter.isEnabled()) {
            myBluetoothAdapter.disable();
        }
    }

    public ArrayAdapter<String> getPairedDevices() {
        Set<BluetoothDevice> bts = myBluetoothAdapter.getBondedDevices();
        devices = new BluetoothDevice[bts.size()];
        String[] deviceNames = new String[bts.size()];
        int i = 0;
        if (bts.size() > 0) {
            for (BluetoothDevice device: bts) {
                devices[i] = device;
                deviceNames[i] = device.getName();
                i++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(activity.getApplicationContext(), R.layout.row, deviceNames);
            return arrayAdapter;
        }
        return null;
    }

    public BluetoothDevice[] getDevices() {
        return devices;
    }

    public void createClient(BluetoothDevice device) {
        Client client = new Client(device, handler, sendReceive);
        client.start();
        Message msg = Message.obtain();
        msg.what = STATE_CONNECTING;
        handler.sendMessage(msg);
    }

    public void createServer() {
        Server serviceClass = new Server(myBluetoothAdapter, handler, sendReceive, this);
        serviceClass.start();
    }

    public void sendData(BattleGroundFactory battleGroundFactory) {
        byte[] buffer;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(battleGroundFactory);
            objectOutputStream.flush();
            buffer = byteArrayOutputStream.toByteArray();
            sendReceive.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BattleGroundFactory receiveData(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            BattleGroundFactory battleGroundFactory = (BattleGroundFactory) objectInputStream.readObject();
            return battleGroundFactory;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void send() {
        sendData(new BattleGroundFactory(activity.getResources()));
    }




}
