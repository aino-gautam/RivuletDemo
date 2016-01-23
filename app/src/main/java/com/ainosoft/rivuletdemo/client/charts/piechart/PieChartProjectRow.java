package com.ainosoft.rivuletdemo.client.charts.piechart;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.shared.slim.Project;
public class PieChartProjectRow {

    public void PieChartProjectRow(){

    }


    public TableRow onCreateTableRow(Context context,Project project,Long i,Long totalSum) {
        TableRow.LayoutParams relativeParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow tableRow=new TableRow(context);
        TableLayout.LayoutParams tableRowParams= new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);

        int leftMargin=5;
        int topMargin=3;
        int rightMargin=0;
        int bottomMargin=2;

        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        tableRow.setLayoutParams(tableRowParams);

        tableRow.setGravity(View.TEXT_ALIGNMENT_CENTER);

        RelativeLayout relativeLayout=new RelativeLayout(context);
        relativeLayout.setPadding(10,10,10,10);

        relativeLayout.setLayoutParams(relativeParams);

        TextView textView=new TextView(context);

        textView.setText("#"+project.project_id+"  "+project.project_name+"(49%,"+i+"min)");
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        relativeLayout.addView(textView, textParams);



        TextView subTextView=new TextView(context);
        subTextView.setText(">");
        subTextView.setTextSize(15);
        RelativeLayout.LayoutParams subtextParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        subtextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        relativeLayout.addView(subTextView, subtextParams);


        tableRow.addView(relativeLayout);
        tableRow.setBackgroundResource(R.drawable.row_border);

        return tableRow;
    }
}


