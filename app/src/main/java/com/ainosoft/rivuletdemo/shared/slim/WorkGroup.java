package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 11/1/16.
 */
public class WorkGroup implements Serializable {

    @DatabaseField(generatedId = true, columnName = "work_id")
    public int work_id;

    @DatabaseField(columnName = "groupname")
    public String groupname;

    public WorkGroup() {

    }


    public WorkGroup(String groupName) {
        this.groupname = groupName;
    }
}
