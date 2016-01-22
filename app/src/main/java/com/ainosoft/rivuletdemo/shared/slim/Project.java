package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 8/1/16.
 */
public class Project implements  Serializable{

    public static final String PASSWORD_FIELD_NAME = "project_name";
    public static final String PASSWORD_STATUS = "project_status";


    @DatabaseField(generatedId = true, columnName = "project_id")
    public int project_id;


    @DatabaseField(columnName = "project_name")
    public String project_name;

    @DatabaseField(columnName = "short_name")
    public String short_name;

    @DatabaseField(columnName = "created_on")
    public String created_on;

    @DatabaseField(columnName = "started_on")
    public String started_on;

    @DatabaseField(columnName = "completed_on")
    public String completed_on;

    @DatabaseField(columnName = "description")
    public String description;



    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public ProjectStatus project_status;

    public Project(){

    }

    public Project(String name,String shortName,String createdOn,String completedOn,String description,ProjectStatus projectStatus){
        this.project_name = name;
        this.short_name=shortName;
        this.created_on=createdOn;
        this.completed_on=completedOn;
        this.description=description;
        this.project_status=projectStatus;
    }



}