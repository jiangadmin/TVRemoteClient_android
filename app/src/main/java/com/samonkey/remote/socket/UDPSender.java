package com.samonkey.remote.socket;

import com.samonkey.remote.utils.Config;
import com.samonkey.remote.utils.LogUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2017/3/24
 *
 * @author saker
 */

public class UDPSender {

    private static UDPSender sUDPSender = new UDPSender();
    private ExecutorService mExecutorService;
    private DatagramSocket mSocket;
    private DatagramPacket mPacket;
    private byte[] mBytes = new byte[64];

    public static UDPSender getInstance() {
        return sUDPSender;
    }

    private UDPSender() {
        mExecutorService = Executors.newCachedThreadPool();
        try {
            mSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public UDPSender setIp(String ip) {
        try {
            mPacket = new DatagramPacket(mBytes, mBytes.length, new InetSocketAddress(ip, Config.PORT));
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return sUDPSender;
    }

    public void sendMsg(String msg) {
        if (mPacket != null) {
            mExecutorService.execute(new SendRunnable(mSocket, mPacket, msg, false));
            LogUtils.d("send->" + msg);
        }
    }

    public void close() {
        if (mSocket != null) {
            mSocket.close();
        }
    }
}
