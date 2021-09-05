package com.example.tanksbattle.multiplayer.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

public class Client extends Thread {

    private BluetoothHandler bluetoothHandler;
    private Handler handler;
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private SendReceive sendReceive;

    public Client(BluetoothDevice device, Handler handler, SendReceive sendReceive, BluetoothHandler bluetoothHandler) {
        this.bluetoothHandler = bluetoothHandler;
        this.sendReceive = sendReceive;
        this.handler = handler;
        this.device = device;
        try {
            socket = device.createRfcommSocketToServiceRecord(BluetoothHandler.MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            socket.connect();
            Message msg = Message.obtain();
            msg.what = BluetoothHandler.STATE_CONNECTED;
            handler.sendMessage(msg);

            sendReceive = new SendReceive(socket, handler);
            bluetoothHandler.setSendReceive(sendReceive);
            sendReceive.start();
        } catch (IOException e) {
            e.printStackTrace();
            Message msg = Message.obtain();
            msg.what = BluetoothHandler.STATE_CONNECTION_FAILED;
            handler.sendMessage(msg);
        }
    }
}
