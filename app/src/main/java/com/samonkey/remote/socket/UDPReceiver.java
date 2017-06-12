package com.samonkey.remote.socket;

import com.samonkey.remote.utils.Config;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created on 2017/3/24
 *
 * @author saker
 */

public class UDPReceiver {

    private static UDPReceiver sUDPReceiver = new UDPReceiver();
    private DatagramSocket mSocket;
    private DatagramPacket mPacket;
    private byte[] mBytes = new byte[64];
    private ReceiverCallback mReceiverCallback;
    private boolean mFlag;

    public static UDPReceiver getInstance() {
        return sUDPReceiver;
    }

    private UDPReceiver() {
        try {
            mSocket = new DatagramSocket(Config.PORT);
            mPacket = new DatagramPacket(mBytes, mBytes.length);
            mFlag = true;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public UDPReceiver setCallback(ReceiverCallback callback) {
        mReceiverCallback = callback;
        return sUDPReceiver;
    }

    public void read() {
        new Thread(new ReadRunnable()).start();
    }

    public void close() {
        if (mSocket != null) {
            mSocket.close();
            mFlag = false;
        }
    }

    private class ReadRunnable implements Runnable {

        @Override
        public void run() {
            while (mFlag) {
                try {
                    mSocket.receive(mPacket);
                    String data = new String(mPacket.getData(), 0, mPacket.getLength());
                    if (Config.SERVER_MODE.equals(data)) {// 拦截并处理全局数据
                        CheckSystem.isCheck = false;
                    } else {
                        if (mReceiverCallback != null) {
                            mReceiverCallback.receive(data);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
