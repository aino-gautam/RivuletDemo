package com.ainosoft.rivuletdemo.client.charts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.server.helper.DatabaseHelper;
import com.ainosoft.rivuletdemo.shared.slim.LineChart;
import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by tushar@ainosoft.com on 22/1/16.
 */
public class LineChartFragment extends Fragment {

    private LineChartView chart;
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = true;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = true;
    private boolean pointsHaveDifferentColor;

    private DatabaseHelper dbHelper;


    public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",};


    public LineChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_line_chart, container, false);

        chart = (LineChartView) rootView.findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());

        if(getContext()!=null){
            dbHelper = new DatabaseHelper(getContext());
        }

        generateInitialLineData();

        return rootView;
    }

    /**
     * Generates initial data for line chart. At the begining all Y values are equals 0. That will change when user
     * will select value on column chart.
     */
    private void generateInitialLineData() {
        int numValues = 7;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            values.add(new PointValue(i, 0));
            axisValues.add(new AxisValue(i).setLabel(days[i]));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN).setCubic(true);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        data = new LineChartData(lines);
        data.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        data.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));

        chart.setLineChartData(data);

        // For build-up animation you have to disable viewport recalculation.
        chart.setViewportCalculationEnabled(false);

        // And set initial max viewport and current viewport- remember to set viewports after data.
        Viewport v = new Viewport(0, 10, 6, 0);
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);

        chart.setZoomType(ZoomType.HORIZONTAL);
    }

    /**
     * this code is use to generate data
     */
    private void generateLineData() {
        ArrayList<Float> yAxisList = new ArrayList<Float>();
        ArrayList<Float> xAxisList = new ArrayList<Float>();
        int i=0;

        try{
            // Cancel last animation if not finished.
            chart.cancelDataAnimation();

            Dao<LineChart, Integer> projectDao = dbHelper.getLineChartDao();
            final ArrayList<LineChart> lineChartsList = (ArrayList)projectDao.queryForAll();

            Toast.makeText(getContext(), "value" + lineChartsList.size(), Toast.LENGTH_SHORT).show();

            for(LineChart lineChartList1:lineChartsList){
                Double xDouble = new Double(lineChartList1.xAxis);
                float xValue = xDouble.floatValue();
                xAxisList.add(xValue);

                Double yDouble = new Double(lineChartList1.yAxis);
                float yValue = yDouble.floatValue();
                yAxisList.add(yValue);
            }

            for(Line line : data.getLines()){
                if(line!=null){
                    for(PointValue pointValue : line.getValues()){

                        if(!xAxisList.isEmpty() && xAxisList.get(i)!=null && !yAxisList.isEmpty() && yAxisList.get(i)!=null){
                            pointValue.set(xAxisList.get(i),yAxisList.get(i));
                            i++;
                        }
                    }
                }
            }

            // Start new data animation with 300ms duration;
            chart.startDataAnimation(300);

        }catch(Exception se){
            Log.d("LineChartActivity", "generateLineData(): " + se);
        }
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
            generateLineData();

        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub
        }
    }
}

