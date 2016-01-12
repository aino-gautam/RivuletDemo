package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 11/1/16.
 */
public class SetUpDao implements Serializable {

    @DatabaseField(generatedId = true, columnName = "setup_id")
    public int setup_id;

    public SetUpDao(){

    }
}
