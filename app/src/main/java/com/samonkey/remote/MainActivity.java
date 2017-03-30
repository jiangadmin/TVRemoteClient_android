package com.samonkey.remote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.samonkey.remote.remote.ClientService;
import com.samonkey.remote.remote.TraditionalActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // test
        startService(new Intent(this, ClientService.class));
    }

    public void btnClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_traditional:
                intent.setClass(this, TraditionalActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
