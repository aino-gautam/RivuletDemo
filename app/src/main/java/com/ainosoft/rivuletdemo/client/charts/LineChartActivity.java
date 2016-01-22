package com.ainosoft.rivuletdemo.client.charts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ainosoft.rivuletdemo.R;


/**
 * author tushar@ainosoft.com
 */
public class LineChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new LineChartFragment()).commit();
        }
    }
}
