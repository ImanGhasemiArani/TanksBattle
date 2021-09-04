package com.example.tanksbattle.multiplayer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

public class Server extends Thread {

    private BluetoothHandler bluetoothHandler;
    private BluetoothAdapter myBluetoothAdapter;
    private Handler handler;
    private BluetoothServerSocket bluetoothServerSocket;
    private SendReceive sendReceive;

    public Server(BluetoothAdapter myBluetoothAdapter, Handler handler, SendReceive sendReceive, BluetoothHandler bluetoothHandler) {
        this.bluetoothHandler = bluetoothHandler;
        this.sendReceive = sendReceive;
        this.myBluetoothAdapter = myBluetoothAdapter;
        this.handler = handler;
        try {
            bluetoothServerSocket = myBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(BluetoothHandler.APP_NAME, BluetoothHandler.MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        BluetoothSocket socket = null;

        while (socket == null) {
            try {
                Message msg = Message.obtain();
                msg.what = BluetoothHandler.STATE_CONNECTING;
                handler.sendMessage(msg);
                socket = bluetoothServerSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                Message msg = Message.obtain();
                msg.what = BluetoothHandler.STATE_CONNECTION_FAILED;
                handler.sendMessage(msg);
            }

            if (socket != null) {
                Message msg = Message.obtain();
                msg.what = BluetoothHandler.STATE_CONNECTED;
                handler.sendMessage(msg);

                sendReceive = new SendReceive(socket, handler);
                sendReceive.start();
                bluetoothHandler.send();
            }
        }
    }
}
