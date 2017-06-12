package com.samonkey.remote.remote;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.samonkey.remote.BaseActivity;
import com.samonkey.remote.R;
import com.samonkey.remote.socket.UDPSender;
import com.samonkey.remote.utils.Config;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TraditionalActivity extends BaseActivity {

    @BindView(R.id.tv_remote_title)
    TextView mTvRemoteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traditional);
        ButterKnife.bind(this);
        mTvRemoteTitle.setText("传统模式");
    }

    @OnClick({R.id.iv_back, R.id.iv_remote_mode, R.id.rl_traditional_center,
            R.id.iv_traditional_up, R.id.iv_traditional_down, R.id.iv_traditional_left,
            R.id.iv_traditional_right, R.id.iv_remote_volume_sub, R.id.iv_remote_volume_add,
            R.id.iv_remote_home, R.id.iv_remote_back, R.id.iv_remote_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_remote_mode:
                Dialogs.changeMode(this, Dialogs.MODE_TRADITIONAL);
                break;
            case R.id.rl_traditional_center:
                UDPSender.getInstance().sendMsg(Config.CENTER);
                break;
            case R.id.iv_traditional_up:
                UDPSender.getInstance().sendMsg(Config.UP);
                break;
            case R.id.iv_traditional_down:
                UDPSender.getInstance().sendMsg(Config.DOWN);
                break;
            case R.id.iv_traditional_left:
                UDPSender.getInstance().sendMsg(Config.LEFT);
                break;
            case R.id.iv_traditional_right:
                UDPSender.getInstance().sendMsg(Config.RIGHT);
                break;
            case R.id.iv_remote_volume_sub:
                UDPSender.getInstance().sendMsg(Config.VOLUME_SUB);
                break;
            case R.id.iv_remote_volume_add:
                UDPSender.getInstance().sendMsg(Config.VOLUME_ADD);
                break;
            case R.id.iv_remote_home:
                UDPSender.getInstance().sendMsg(Config.HOME);
                break;
            case R.id.iv_remote_back:
                UDPSender.getInstance().sendMsg(Config.BACK);
                break;
            case R.id.iv_remote_menu:
                UDPSender.getInstance().sendMsg(Config.MENU);
                break;
        }
    }

}
