package com.samonkey.remote.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created on 2017/3/27
 *
 * @author saker
 */

public class SendRunnable implements Runnable {

    private DatagramSocket mSocket;
    private DatagramPacket mPacket;
    private String mMessage;
    private boolean mAutoClose;// 发送完成后自动关闭Socket

    public SendRunnable(DatagramSocket socket, DatagramPacket packet, String message,
                        boolean autoClose) {
        mSocket = socket;
        mPacket = packet;
        mMessage = message;
        mAutoClose = autoClose;
    }

    @Override
    public void run() {
        mPacket.setData(mMessage.getBytes(), 0, mMessage.getBytes().length);
        try {
            mSocket.send(mPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mAutoClose) {
            mSocket.close();
        }
    }
}
