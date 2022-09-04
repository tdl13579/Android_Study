package com.android.myapplication.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.myapplication.R;
import com.android.myapplication.databinding.ActivityOkhttpRequestBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpRequestActivity extends AppCompatActivity {
    String TAG = "OkhttpRequestActivity";
    ActivityOkhttpRequestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOkhttpRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // get同步
        binding.getSync.setOnClickListener(view -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .get()
                            .build();
                    Call call = new OkHttpClient().newCall(request);
                    try {
                        Response response = call.execute();
                        if (response.isSuccessful()) {
                            Log.e(TAG, response.body().toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
        // get异步
        binding.get.setOnClickListener(view -> {
            Request request = new Request.Builder()
                    .url("http:www.baidu.com")
                    .get()
                    .build();
            Call call = new OkHttpClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Log.e(TAG, response.body().toString());
                    }
                }
            });
        });
        // post同步
        binding.postSync.setOnClickListener(view -> {
            RequestBody requestBody = new FormBody.Builder()
                    .add("a", "1")
                    .add("b", "2")
                    .build();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .post(requestBody)
                            .build();
                    Call call = new OkHttpClient().newCall(request);
                    try {
                        Response response = call.execute();
                        if(response.isSuccessful()){
                            Log.e(TAG, response.body().toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        });
    }
}