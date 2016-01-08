package com.ainosoft.rivuletdemo.client.appbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ainosoft.rivuletdemo.R;

public class AppBaseManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_base_manager);
    }
}
