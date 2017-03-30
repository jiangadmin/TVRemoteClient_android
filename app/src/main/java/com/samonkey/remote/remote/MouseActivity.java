package com.samonkey.remote.remote;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.samonkey.remote.BaseActivity;
import com.samonkey.remote.R;
import com.samonkey.remote.socket.UDPSender;
import com.samonkey.remote.utils.Config;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MouseActivity extends BaseActivity {

    @BindView(R.id.tv_remote_title)
    TextView mTvRemoteTitle;
    @BindView(R.id.rl_mouse_container)
    RelativeLayout mRlMouseContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);
        ButterKnife.bind(this);
        mTvRemoteTitle.setText("鼠标模式");
        initEvent();
    }

    private long mLastMillis;
    private int mLastX;
    private int mLastY;

    private void initEvent() {
        final GestureDetector gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                        long millis = System.currentTimeMillis();
                        mLastX += (distanceX * 0.5);
                        mLastY += (distanceY * 0.5);
                        if (millis - mLastMillis > 10) {
                            UDPSender.getInstance().sendMsg(Config.SCROLL + -mLastX + "," + -mLastY);
                            mLastMillis = millis;
                            mLastX = 0;
                            mLastY = 0;
                        }
                        return false;
                    }

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        UDPSender.getInstance().sendMsg(Config.CLICK);
                        return super.onSingleTapConfirmed(e);
                    }
                });
        mRlMouseContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_remote_mode, R.id.iv_remote_volume_sub, R.id.iv_remote_volume_add, R.id.iv_remote_home, R.id.iv_remote_back, R.id.iv_remote_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_remote_mode:
                Dialogs.changeMode(this, Dialogs.MODE_MOUSE);
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
