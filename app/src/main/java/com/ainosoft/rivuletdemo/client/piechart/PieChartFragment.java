package com.ainosoft.rivuletdemo.client.piechart;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by swapnil@ainosoft.com on 22/1/16.
 */
public class PieChartFragment extends Fragment {

    private PieChartView chart;
    private PieChartData data;

    private boolean hasLabels = true;
    private boolean hasLabelsOutside = false;
    private boolean hasCenterCircle = true;
    private boolean hasCenterText1 = true;
    private boolean hasCenterText2 = true;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;
    private HashMap<Integer,Long> projectMin=new HashMap<>();
    private List<Project> projectList;
    private Long totalSum=0L;

    public PieChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);

            chart = (PieChartView) rootView.findViewById(R.id.chart);

            DatabaseHelper dbHelper = new DatabaseHelper(getContext());

            Dao<Project, Integer> projectDao = dbHelper.getProjectDao();

            ////////////////////////////////////////////
            Dao<Project, Integer> projectDaoList = dbHelper.getProjectDao();
            QueryBuilder<Project, Integer> queryBuilder = projectDaoList.queryBuilder();
            Where<Project, Integer> where = queryBuilder.where();
            where.eq(Project.PASSWORD_STATUS,1);
            PreparedQuery<Project> preparedQuery = queryBuilder.prepare();
            projectList = projectDaoList.query(preparedQuery);
            ///////////////////////////////////

            generateData();

            TableLayout parentLayout = (TableLayout) rootView.findViewById(R.id.piecharttable);

            for (int i = 0; i < projectList.size(); i++) {
                Double d=(double)projectMin.get(projectList.get(i).project_id) /(double) totalSum;

                PieChartProjectRow projectScreen = new PieChartProjectRow();

                TableRow tableRow = projectScreen.onCreateTableRow(getContext(), projectList.get(i),projectMin.get(projectList.get(i).project_id),totalSum);

                tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                parentLayout.addView(tableRow);
            }
            return rootView;
        }catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }

    private void generateData() {
        totalSum=0L;
        try {

            DatabaseHelper dbHelper = new DatabaseHelper(getContext());
            List<SliceValue> values = new ArrayList<SliceValue>();

            //final Dao<LogEntry, Integer> logDao = dbHelper.getCallLogDao();

            final Dao<Project,Integer> projectDao=dbHelper.getProjectDao();

            //totalSum = logDao.queryRawValue("SELECT SUM(duration) AS total FROM (logentry) WHERE PASSWORD_STATUS =?","1");*/

            int numValues=projectList.size();

            for (int i = 0; i < numValues; ++i) {
                Project project=projectList.get(i);
                Long sum1 = projectDao.queryRawValue("SELECT SUM(duration) AS total FROM (logentry) WHERE project_id=" + project.project_id);
                totalSum=totalSum+sum1;
                projectMin.put(project.project_id,sum1);
                SliceValue sliceValue = new SliceValue((float) sum1, ChartUtils.pickColor());
                values.add(sliceValue);
            }

            data = new PieChartData(values);
            data.setHasLabels(hasLabels);
            data.setHasLabelsOnlyForSelected(hasLabelForSelected);
            data.setHasLabelsOutside(hasLabelsOutside);
            data.setHasCenterCircle(hasCenterCircle);

            if (isExploded) {
                data.setSlicesSpacing(24);
            }

            if (hasCenterText1) {
                data.setCenterText1("" + totalSum);

                // Get roboto-italic font.
                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");
                data.setCenterText1Typeface(tf);

                // Get font size from dimens.xml and convert it to sp(library uses sp values).
                data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                        (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
            }

            if (hasCenterText2) {
                data.setCenterText2("Total Billable Time");

                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");

                data.setCenterText2Typeface(tf);
                data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                        (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));
            }

            chart.setValueSelectionEnabled(hasLabelForSelected);
            chart.setPieChartData(data);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}

