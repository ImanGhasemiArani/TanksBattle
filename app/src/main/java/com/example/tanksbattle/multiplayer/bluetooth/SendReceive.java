package com.example.tanksbattle.multiplayer.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SendReceive extends Thread {

    private Handler handler;
    private final BluetoothSocket bluetoothSocket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public SendReceive(BluetoothSocket bluetoothSocket, Handler handler) {
        this.handler = handler;
        this.bluetoothSocket = bluetoothSocket;
        InputStream tempIn = null;
        OutputStream tempOut = null;

        try {
            tempIn = bluetoothSocket.getInputStream();
            tempOut = bluetoothSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputStream = tempIn;
        outputStream = tempOut;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int bytes = 1024;

        while (true) {
            try {
                inputStream.read(buffer);
                handler.obtainMessage(BluetoothHandler.STATE_MESSAGE_RECEIVED, bytes, -1, buffer).sendToTarget();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(byte[] bytes) {
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
