package com.samonkey.remote.utils;

/**
 * Created on 2017/3/24
 *
 * @author saker
 */

public interface Config {

    int PORT = 22225;

    // 连接标识
    String SERVER_IP = "00";
    String CLIENT_IP = "01";
    String SERVER_STOP = "04";

    String SERVER_MODE = "02";// 服务端接收反馈
    String CLIENT_MODE = "03";

    // 遥控模式
    String CLIENT_MODE_TRADITIONAL = "0A";// 传统
    String CLIENT_MODE_MOUSE = "0B";// 鼠标
    String CLIENT_MODE_TOUCH = "0C";// 手势
    String CLIENT_MODE_HANDLE = "0D";// 手柄
    String CLIENT_MODE_GYROSCOPE = "0E";// 陀螺仪
    String CLIENT_MODE_KEYBOARD = "0F";// 键盘
    String CLIENT_MODE_VOICE = "0G";// 语音

    // 传统指令
    String BACK = "10";
    String HOME = "11";
    String MENU = "12";
    String LEFT = "13";
    String UP = "14";
    String RIGHT = "15";
    String DOWN = "16";
    String CENTER = "17";
    String VOLUME_ADD = "18";
    String VOLUME_SUB = "19";

    // 鼠标指令
    String SCROLL = "20";
    String CLICK = "21";

    // 触控指令
    String TOUCH_DOWN = "2A";
    String TOUCH_UP = "2B";
}
