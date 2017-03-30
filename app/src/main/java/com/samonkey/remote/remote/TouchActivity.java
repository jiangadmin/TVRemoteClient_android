package com.samonkey.remote.remote;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.samonkey.remote.BaseActivity;
import com.samonkey.remote.R;
import com.samonkey.remote.socket.UDPSender;
import com.samonkey.remote.utils.Config;
import com.samonkey.remote.utils.ScreenUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TouchActivity extends BaseActivity {

    @BindView(R.id.tv_remote_title)
    TextView mTvRemoteTitle;
    @BindView(R.id.rl_touch_container)
    RelativeLayout mRlTouchContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        ButterKnife.bind(this);
        mTvRemoteTitle.setText("触控模式");
        initEvent();
    }

    private int mScreenWidth;
    private int mScreenHeight;
    // 多点记录手指的规则像堆栈，用List记录手指固定的ID
    private List<Integer> mFingerIds = new ArrayList<>();

    private void initEvent() {
        mScreenWidth = ScreenUtils.getScreenWidth(this);
        mScreenHeight = ScreenUtils.getScreenHeight(this);
        mRlTouchContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int actionIndex = event.getActionIndex();
                float x = event.getX(actionIndex);
                float y = event.getY(actionIndex);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        DecimalFormat format = new DecimalFormat("0.0000");
                        UDPSender.getInstance().sendMsg(Config.TOUCH_DOWN + actionIndex +
                                format.format(x / mScreenWidth) + "," +
                                format.format(y / mScreenHeight));
                        mFingerIds.add(actionIndex, actionIndex);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        UDPSender.getInstance().sendMsg(Config.TOUCH_UP +
                                mFingerIds.remove(actionIndex));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_remote_mode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_remote_mode:
                Dialogs.changeMode(this, Dialogs.MODE_TOUCH);
                break;
        }
    }
}
