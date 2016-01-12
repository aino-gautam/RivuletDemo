package com.ainosoft.rivuletdemo.shared.slim;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by comp5 on 11/1/16.
 */
public class WorkContactProject implements Serializable {
    @DatabaseField(generatedId = true, columnName = "work_proj_contact_id")
    public int work_proj_contact_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public Project project_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public Contacts contact_id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public WorkGroup work_group_id;

    public WorkContactProject() {

    }


    public WorkContactProject(Project projectId, Contacts contactId,WorkGroup work_group) {
        this.project_id = projectId;
        this.contact_id = contactId;
        this.work_group_id=work_group;
    }
}
