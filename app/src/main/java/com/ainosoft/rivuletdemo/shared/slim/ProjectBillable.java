package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 11/1/16.
 */
public class ProjectBillable implements Serializable {

    @DatabaseField(generatedId = true, columnName = "proj_bill_id")
    public int proj_bill_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public Project project_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public Billable billable_id;

    public ProjectBillable() {

    }


    public ProjectBillable(Project projectId, Billable billableId) {
        this.project_id = projectId;
        this.billable_id = billableId;
    }
}
