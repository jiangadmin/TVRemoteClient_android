package com.samonkey.remote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.samonkey.remote.utils.SystemBarTintManager;

/**
 * Created on 2017/3/27
 *
 * @author saker
 */

public class BaseActivity extends AppCompatActivity {

    private SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintColor(getResources().getColor(R.color.statusColor));
        super.onCreate(savedInstanceState);
    }
}
