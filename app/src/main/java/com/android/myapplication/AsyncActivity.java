package com.android.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class AsyncActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        // 异步的实现方式
        /*
         * 1. Thread()
         * 2. Thread() + handle() + looper
         * */
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    showToast("我是handle");
                }
            }
        };
        new Thread() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private void showToast(String str) {
        Toast.makeText(AsyncActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}