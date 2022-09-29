package com.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText name = findViewById(R.id.name);
        EditText pwd = findViewById(R.id.pwd);
        Button btn = findViewById(R.id.login);
        preference =  getSharedPreferences("login", Context.MODE_PRIVATE);
        reload();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = preference.edit();
                editor.putString("name",name.getText().toString());
                editor.putString("pwd",pwd.getText().toString());
                editor.commit();
            }
        });
    }
    private void reload(){
        String name =  preference.getString("name","");
        String pwd =  preference.getString("pwd","");
        Log.e("reload",""+name+pwd);
    }
}