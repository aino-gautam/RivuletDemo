package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 22/1/16.
 */
public class CallLogType implements Serializable {

    @DatabaseField(generatedId = true, columnName = "log_id")
    public int log_id;

    @DatabaseField(generatedId = true, columnName = "log_type")
    public String log_type;


    public CallLogType() {

    }

}

