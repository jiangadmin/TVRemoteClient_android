package com.samonkey.remote.socket;

import android.os.Handler;

/**
 * Created on 2017/3/29
 *
 * @author saker
 */

public class CheckSystem {

    public static boolean isCheck;// 是否校验
    private static final int DELAY_TIME = 500;
    private static Handler sHandler = new Handler();

    public static void send(String data) {
        isCheck = true;
        sHandler.post(new Send(data));
    }

    private static class Send implements Runnable {
        private String mData;

        public Send(String data) {
            mData = data;
        }

        @Override
        public void run() {
            if (isCheck) {
                UDPSender.getInstance().sendMsg(mData);
                sHandler.postDelayed(this, DELAY_TIME);
            } else {
                sHandler.removeCallbacks(this);
            }
        }
    }
}
