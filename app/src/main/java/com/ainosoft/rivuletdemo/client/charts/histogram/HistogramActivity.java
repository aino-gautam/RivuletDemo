package com.ainosoft.rivuletdemo.client.charts.histogram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ainosoft.rivuletdemo.R;

/**
 * author tushar@ainosoft.com
 */
public class HistogramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new HistogramFragment()).commit();
        }
    }
}
