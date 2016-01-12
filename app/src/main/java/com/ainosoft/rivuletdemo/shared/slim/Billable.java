package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 9/1/16.
 */
public class Billable implements Serializable {

    @DatabaseField(generatedId = true, columnName = "bill_id")
    public int bill_id;

    @DatabaseField(columnName = "rate")
    public Double rate;

    @DatabaseField(columnName = "converted_value")
    public Double converted_value;

    public Billable(){

    }


    public Billable(Double rate,Double convertedValue){
        this.rate = rate;
        this.converted_value=convertedValue;
    }
}
