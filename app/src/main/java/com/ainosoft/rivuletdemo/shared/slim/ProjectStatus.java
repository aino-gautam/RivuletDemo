package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 22/1/16.
 */
public class ProjectStatus implements Serializable {

    @DatabaseField(generatedId = true, columnName = "status_id")
    public int status_id;

    @DatabaseField(generatedId = true, columnName = "status_name")
    public String status_name;


    public ProjectStatus() {

    }

}
