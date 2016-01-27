package com.ainosoft.rivuletdemo.client.charts.linechart;

import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.server.helper.DatabaseHelper;
import com.ainosoft.rivuletdemo.shared.slim.LineChart;
import com.ainosoft.rivuletdemo.shared.slim.LogEntry;
import com.ainosoft.rivuletdemo.shared.slim.Project;
import com.j256.ormlite.dao.Dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
    private HashMap<String,Long> dateList;
    private DatabaseHelper dbHelper;
    private ArrayList<Float> lineChartsList;

    public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",};


    public LineChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_line_chart, container, false);

        dateList=new HashMap<>();

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

        try {

            dateList=new HashMap<>();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Calendar cal = Calendar.getInstance();

            cal.add(Calendar.DATE,0);

            String strDate = dateFormat.format(cal.getTime());
            String pid="1";

            dbHelper=new DatabaseHelper(getContext());
            final Dao<LogEntry,Integer> projectDao=dbHelper.getCallLogDao();

            Long sum1 = projectDao.queryRawValue("SELECT SUM(duration) AS total FROM (logentry) WHERE project_id=? AND date=? ",pid,strDate);

            Date dt1 = dateFormat.parse(strDate);
            DateFormat format2 = new SimpleDateFormat("EEEE");
            String finalDay = format2.format(dt1);


            dateList.put(finalDay,sum1);


            for(int i1=1;i1<7;i1++){
                cal.add(Calendar.DATE,-1);

                strDate = dateFormat.format(cal.getTime());

                sum1 = projectDao.queryRawValue("SELECT SUM(duration) AS total FROM (logentry) WHERE project_id=? AND date=?", pid, strDate);

                dt1 = dateFormat.parse(strDate);
                format2 = new SimpleDateFormat("EEEE");
                finalDay = format2.format(dt1);


                dateList.put(finalDay,sum1);

            }

            lineChartsList=new ArrayList<Float>();
            for(int i=0;i<7;i++){
                lineChartsList.add(0f);
            }

            for(String str : dateList.keySet()){
                if(str.equals("Monday"))
                    lineChartsList.set(0,(float)dateList.get(str));
                else if(str.equals("Tuesday"))
                    lineChartsList.set(1,(float)dateList.get(str));
                else if(str.equals("Wednesday"))
                    lineChartsList.set(2,(float)dateList.get(str));
                else if(str.equals("Thursday"))
                    lineChartsList.set(3,(float)dateList.get(str));
                else if(str.equals("Friday"))
                    lineChartsList.set(4,(float)dateList.get(str));
                else if(str.equals("Saturday"))
                    lineChartsList.set(5,(float)dateList.get(str));
                else if(str.equals("Sunday"))
                    lineChartsList.set(6,(float)dateList.get(str));


            }
        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayList<Float> yAxisList = new ArrayList<Float>();
        ArrayList<Float> xAxisList = new ArrayList<Float>();
        int i=0;

        try{

            // Cancel last animation if not finished.
            chart.cancelDataAnimation();

//            Toast.makeText(getContext(), "value" + strDate, Toast.LENGTH_SHORT).show();

            for(int i1=0;i1<7;i1++){
                xAxisList.add((float)i1);
                yAxisList.add(lineChartsList.get(i1));
            }

            for(Line line : data.getLines()){
                if(line!=null){
                    for(PointValue pointValue : line.getValues()){

                        if(!xAxisList.isEmpty() && xAxisList.get(i)!=null && !yAxisList.isEmpty() && yAxisList.get(i)!=null){
                            pointValue.set(pointValue.getX(),yAxisList.get(i));
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

