package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 9/1/16.
 */
public class Contacts implements Serializable {

    @DatabaseField(generatedId = true, columnName = "contact_id")
    public int contact_id;

    @DatabaseField(columnName = "contact_name")
    public String contact_name;

    @DatabaseField(columnName = "number")
    public String number;

    @DatabaseField(columnName = "alternate_number")
    public String alternate_number;

    @DatabaseField(columnName = "note")
    public String note;

    @DatabaseField(columnName = "created_on")
    public String created_on;

    @DatabaseField(columnName = "photo")
    public String photo;

    public Contacts(){

    }


    public Contacts(String name,String number,String alternateNumber,String note,String created_On,String photo){
        this.contact_name = name;
        this.number=number;
        this.alternate_number=alternateNumber;
        this.note=note;
        this.created_on=created_On;
        this.photo=photo;

    }
}
