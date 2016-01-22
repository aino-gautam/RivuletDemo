package com.ainosoft.rivuletdemo.client.piechart;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.server.helper.DatabaseHelper;
import com.ainosoft.rivuletdemo.shared.slim.Project;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by swapnil@ainosoft.com on 18/1/16.
 */
public class PieChartClass  extends AppCompatActivity {

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