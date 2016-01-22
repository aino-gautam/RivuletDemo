package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by tushar@ainosoft.com on 20/1/16.
 */
public class LineChart implements Serializable {

    @DatabaseField(generatedId = true, columnName = "line_id")
    public int line_id;

    @DatabaseField(columnName = "xAxis")
    public Double xAxis;

    @DatabaseField(columnName = "yAxis")
    public Double yAxis;

    public LineChart(){

    }

    public LineChart(Double xAxis,Double yAxis){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }
}
