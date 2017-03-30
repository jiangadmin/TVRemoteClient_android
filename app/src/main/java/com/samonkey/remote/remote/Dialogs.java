package com.samonkey.remote.remote;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.samonkey.remote.socket.CheckSystem;
import com.samonkey.remote.utils.Config;

/**
 * Created on 2017/3/28
 *
 * @author saker
 */

public class Dialogs {
    public static String[] sStrings = {"传统模式", "鼠标模式", "触控模式"};
    // 与上面的String数组对应
    public static final int MODE_TRADITIONAL = 0;
    public static final int MODE_MOUSE = 1;
    public static final int MODE_TOUCH = 2;

    public static void changeMode(final Activity activity, final int curIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("遥控模式").setItems(sStrings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 选择当前模式则无需操作
                if (which == curIndex) {
                    return;
                }
                String mode = null;
                Intent intent = null;
                switch (which) {
                    case MODE_TRADITIONAL:
                        mode = Config.CLIENT_MODE + Config.CLIENT_MODE_TRADITIONAL;
                        intent = new Intent(activity, TraditionalActivity.class);
                        break;
                    case MODE_MOUSE:
                        mode = Config.CLIENT_MODE + Config.CLIENT_MODE_MOUSE;
                        intent = new Intent(activity, MouseActivity.class);
                        break;
                    case MODE_TOUCH:
                        mode = Config.CLIENT_MODE + Config.CLIENT_MODE_TOUCH;
                        intent = new Intent(activity, TouchActivity.class);
                        break;
                    default:
                        break;
                }
                if (intent != null) {
                    // 重传机制发送遥控模式
                    CheckSystem.send(mode);
                    activity.startActivity(intent);
                    activity.finish();
                }
            }
        });
        builder.create().show();
    }

}
