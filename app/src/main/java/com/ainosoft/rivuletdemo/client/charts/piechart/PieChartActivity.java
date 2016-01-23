package com.ainosoft.rivuletdemo.client.charts.piechart;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ainosoft.rivuletdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnil@ainosoft.com on 18/1/16.
 */
public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        Spinner allprojectspinner = (Spinner) findViewById(R.id.allprojectspineer);

        List<String> projectlist = new ArrayList<>();
        projectlist.add("All Projects");
        projectlist.add("Demo 1");
        projectlist.add("Demo 2");
        projectlist.add("Demo 3");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, projectlist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allprojectspinner.setAdapter(dataAdapter);

        Spinner thisweekspinner = (Spinner) findViewById(R.id.thisweekspineer);

        List<String> list = new ArrayList<>();
        list.add("This Week");
        list.add("Demo 1");
        list.add("Demo 2");
        list.add("Demo 3");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thisweekspinner.setAdapter(dataAdapter1);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PieChartFragment()).commit();
        }
    }
}