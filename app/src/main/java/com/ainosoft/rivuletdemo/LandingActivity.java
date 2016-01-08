package com.ainosoft.rivuletdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ainosoft.rivuletdemo.client.login.LoginActivity;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void loginInApp(View view){
        Intent intent = new Intent(LandingActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
