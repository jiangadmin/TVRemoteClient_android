package com.samonkey.remote.socket;

import com.samonkey.remote.utils.Config;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 用于发送广播搜寻服务端
 * Created on 2017/3/24
 *
 * @author saker
 */

public class UDPBroadcast {

    private static final String BROADCAST_IP = "255.255.255.255";
    private byte[] mBytes = new byte[64];
    private DatagramSocket mSocket;
    private DatagramPacket mPacket;

    public UDPBroadcast() {
        try {
            mSocket = new DatagramSocket();
            mPacket = new DatagramPacket(mBytes, mBytes.length,
                    new InetSocketAddress(BROADCAST_IP, Config.PORT));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        // 发送完成后关闭Socket
        new Thread(new SendRunnable(mSocket, mPacket, msg, true)).start();
    }

}
