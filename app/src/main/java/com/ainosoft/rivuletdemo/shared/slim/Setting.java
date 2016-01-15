package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 9/1/16.
 */
public class Setting implements Serializable {

    @DatabaseField(generatedId = true, columnName = "setting_id")
    public int setting_id;

    @DatabaseField(columnName = "time_interval")
    public String time_interval;

    @DatabaseField(columnName = "confirm_time_stop")
    public Boolean confirm_time_stop;

    @DatabaseField(columnName = "web_access")
    public Boolean web_access;

    @DatabaseField(columnName = "work_group")
    public String work_group;

    @DatabaseField(columnName = "start_screen")
    public String start_screen;

    public Setting(){

    }

    public Setting(String timeInterval,Boolean confirmTimeStop,Boolean webAccess,String workGroup,String startScreen){
        this.time_interval=timeInterval;
        this.confirm_time_stop=confirmTimeStop;
        this.web_access=webAccess;
        this.work_group=workGroup;
        this.start_screen=startScreen;
    }
}
