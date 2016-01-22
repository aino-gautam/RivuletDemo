package com.ainosoft.rivuletdemo.client.charts;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by tushar@ainosoft.com on 22/1/16.
 */
public class HistogramFragment extends Fragment {

    public final static String[] months = new String[]{"Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec",};

    public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",};

    private LineChartView chartTop;
    private ColumnChartView chartBottom;

    private LineChartData lineData;
    private ColumnChartData columnData;

    private DatabaseHelper dbHelper;


    List<AxisValue> axisValues = new ArrayList<AxisValue>();
    List<Column> columns = new ArrayList<Column>();
    List<SubcolumnValue> values;

    public HistogramFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_histogram, container, false);

        chartBottom = (ColumnChartView) rootView.findViewById(R.id.chart_bottom);

        generateColumnData();

        return rootView;
    }

    private void generateColumnData() {

        int numSubcolumns = 1;
        int numColumns = days.length;


        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f+5, ChartUtils.pickColor()));
            }

            axisValues.add(new AxisValue(i).setLabel(days[i]));

            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }

        columnData = new ColumnChartData(columns);

        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(2));

        chartBottom.setColumnChartData(columnData);

        // Set value touch listener that will trigger changes for chartTop.
        chartBottom.setOnValueTouchListener(new ValueTouchListener());

        // Set selection mode to keep selected month column highlighted.
        chartBottom.setValueSelectionEnabled(true);

        chartBottom.setZoomType(ZoomType.HORIZONTAL);

        // chartBottom.setOnClickListener(new View.OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // SelectedValue sv = chartBottom.getSelectedValue();
        // if (!sv.isSet()) {
        // generateInitialLineData();
        // }
        //
        // }
        // });

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
            chartBottom.cancelDataAnimation();

            Dao<LineChart, Integer> projectDao = dbHelper.getLineChartDao();
            final ArrayList<LineChart> lineChartsList=(ArrayList)projectDao.queryForAll();

            Toast.makeText(getActivity(), "value" + lineChartsList.size(), Toast.LENGTH_SHORT).show();

            for(LineChart lineChartList1:lineChartsList){
                Double xDouble = new Double(lineChartList1.xAxis);
                float xValue = xDouble.floatValue();
                xAxisList.add(xValue);

                Double yDouble = new Double(lineChartList1.yAxis);
                float yValue = yDouble.floatValue();
                yAxisList.add(yValue);
            }

            for(Column column : columnData.getColumns()){
                if(column!=null){
                    for(SubcolumnValue subcolumnValue : column.getValues()){

                        if(!xAxisList.isEmpty() && xAxisList.get(i)!=null && !yAxisList.isEmpty() && yAxisList.get(i)!=null){
                            subcolumnValue.setTarget(yAxisList.get(i));
                            i++;
                        }
                    }
                }
            }

            // Start new data animation with 300ms duration;
            chartBottom.startDataAnimation(300);

        }catch(Exception se){
            Log.d("LineChartActivity", "generateLineData(): " + se);
        }
    }



    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {

            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();

            generateLineData();

        }

        @Override
        public void onValueDeselected() {


        }
    }
}
