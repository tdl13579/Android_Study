package com.android.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {
    TextView leftButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        context = this;
        // 启动activity的三种方式
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);

//        ComponentName name = new ComponentName("com.android.myapplication.TestActivity","com.android.myapplication.MainActivity");
//        intent.setComponent(name);

//        startActivityForResult(intent,1000);

        // 启动4秒后关闭activity
        new Thread() { //
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(4000);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        leftButton = findViewById(R.id.leftButton);
        setListener();
        // 开启UI子线程


    }

    // 事件监听
    private void setListener() {
        leftButton.setOnClickListener(view -> {
            // 开启子线程
            new Thread(new Runnable() {
                @Override
                public void run() { // 子线程的需要做的事
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // 方式二
            new Thread() {
                @Override
                public void run() { // 子线程的需要做的事
                    super.run();
                }
            }.start();
            // 主线程中开启子线程
            System.out.println("主线程开启---");
            Thread thread = new Thread(new ChildThread());
            thread.start(); // 启动子线程
            System.out.println("主线程结束---");
            // 子线程中开启主线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() { // 开启主线程
                        @Override
                        public void run() {

                        }
                    });
                }
            }).start();
            new AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("hello world")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"确定按钮", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"取消按钮",Toast.LENGTH_SHORT).show();
                        }
                    }).create().show();
        });
    }

    class ChildThread implements Runnable {
        @Override
        public void run() {

            try {
                System.out.println("子线程开始--");
                Thread.sleep(3000);
                System.out.println("开启子线程任务--");
                System.out.println("子线程结束--");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    /* activity 的生命周期
     * onCreate() ->  onStart() -> onResume() -> onPause() -> onStop（） -> onDestroy()
     *
     * */


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart", "---");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume", "---");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "---");
    }
}