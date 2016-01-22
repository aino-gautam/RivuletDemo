package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 9/1/16.
 */
public class LogEntry implements Serializable {

    @DatabaseField(generatedId = true, columnName = "log_id")
    public int log_id;

    @DatabaseField(columnName = "log_title")
    public String log_title;

    @DatabaseField(columnName = "type")
    public String type;

    @DatabaseField(columnName = "duration")
    public String duration;

    @DatabaseField(columnName = "start_time")
    public String start_time;


    @DatabaseField(columnName = "end_time")
    public String end_time;

    @DatabaseField(columnName = "date")
    public String date;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public Project project;


    public LogEntry(){

    }


    public LogEntry(String logTitle,String type,String duration,String startTime,String endTime,Project project){
        this.log_title = logTitle;
        this.type=type;
        this.duration=duration;
        this.start_time=startTime;
        this.end_time=endTime;
        this.project=project;
    }
}
