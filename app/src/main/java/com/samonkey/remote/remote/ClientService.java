package com.samonkey.remote.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.samonkey.remote.socket.ReceiverCallback;
import com.samonkey.remote.socket.UDPBroadcast;
import com.samonkey.remote.socket.UDPReceiver;
import com.samonkey.remote.socket.UDPSender;
import com.samonkey.remote.utils.Config;
import com.samonkey.remote.utils.Utils;

public class ClientService extends Service {
    public ClientService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 连接服务器，放在onStartCommand中以便使用startService重连
        connectServer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        UDPSender.getInstance().close();
        UDPReceiver.getInstance().close();
        super.onDestroy();
    }

    private void connectServer() {
        if (!Utils.isWifi(this)) {
            // TODO: 2017/3/24 提示连接WiFi
            return;
        }
        // 开启接收器
        UDPReceiver.getInstance().setCallback(new Callback()).read();
        // 广播本机IP
        new UDPBroadcast().sendMsg(Config.CLIENT_IP + Utils.getWifiIp(this));
        // test
        UDPSender.getInstance().setIp("192.168.1.63");
    }

    private class Callback implements ReceiverCallback {

        @Override
        public void receive(String data) {
            String flag = data.substring(0, 2);
            if (Config.SERVER_IP.equals(flag)) {
                // 为发送器设置IP
                String ip = data.substring(2);
                UDPSender.getInstance().setIp(ip);
            }
        }
    }
}
