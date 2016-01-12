package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 11/1/16.
 */
public class LogMember implements Serializable {



    @DatabaseField(generatedId = true, columnName = "log_mem_id")
    public int log_mem_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public LogEntry log_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public Contacts contact_id;

    public LogMember() {

    }


    public LogMember(LogEntry log, Contacts contacts) {
        this.log_id = log;
        this.contact_id = contacts;
    }
}
