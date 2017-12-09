package com.example.quarter;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.quarter.mdata.Mydata;
import com.example.quarter.parameters.Parameter;
import com.example.quarter.presenter.MPresenter;

import java.util.Timer;
import java.util.TimerTask;

import base.BaseActivity;
import base.BasePresenter;

public class MainActivity extends AppCompatActivity{
    private Handler han = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int num = (int) msg.obj;
            if(num == 0){
                Intent in = new Intent(MainActivity.this,HomePage.class);
                startActivity(in);
                finish();
            }
        }
    };
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer t = new Timer();
        TimerTask tt= new TimerTask() {
            private int num = 3;
            @Override
            public void run() {
                num--;
                Message msg = new Message();
                msg.obj = num;
                han.sendMessage(msg);
            }
        };
        t.schedule(tt,1000,1000);
    }
}
