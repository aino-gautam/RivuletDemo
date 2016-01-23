package com.ainosoft.rivuletdemo.server.helper;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.telecom.Call;
import android.util.Log;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.shared.slim.Billable;
import com.ainosoft.rivuletdemo.shared.slim.LineChart;
import com.ainosoft.rivuletdemo.shared.slim.CallLogType;
import com.ainosoft.rivuletdemo.shared.slim.LogEntry;
import com.ainosoft.rivuletdemo.shared.slim.Contacts;
import com.ainosoft.rivuletdemo.shared.slim.LogMember;
import com.ainosoft.rivuletdemo.shared.slim.Project;
import com.ainosoft.rivuletdemo.shared.slim.ProjectBillable;
import com.ainosoft.rivuletdemo.shared.slim.ProjectStatus;
import com.ainosoft.rivuletdemo.shared.slim.Setting;
import com.ainosoft.rivuletdemo.shared.slim.SetUpDao;
import com.ainosoft.rivuletdemo.shared.slim.WorkContactProject;
import com.ainosoft.rivuletdemo.shared.slim.WorkGroup;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
/**
 * Created by swapnil@ainosoft.com on 8/1/16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "rivuletdemo.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<CallLogType,Integer> callLogTypeDao;
    private Dao<ProjectStatus,Integer> projectStatusesDao;
    private Dao<Billable, Integer> billableDao;
    private Dao<LogEntry, Integer> call_logDao;
    private Dao<Contacts, Integer> contactDao;
    private Dao<Project, Integer> projectDao;
    private Dao<Setting, Integer> settingDao;
    private Dao<SetUpDao,Integer>setUpDao;
    private Dao<LogMember,Integer>logMemberDao;
    private Dao<ProjectBillable,Integer>projectBillableDao;
    private Dao<WorkGroup,Integer>workGroupDao;
    private Dao<WorkContactProject,Integer>workContactProjectDao;
    private Dao<LineChart,Integer>lineChartDao;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        if(context==null){
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases");
        }

    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {


            // Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
            TableUtils.createTable(connectionSource,ProjectStatus.class);
            TableUtils.createTable(connectionSource,CallLogType.class);
            TableUtils.createTable(connectionSource,Billable.class);
            TableUtils.createTable(connectionSource,LogEntry.class);
            TableUtils.createTable(connectionSource,Contacts.class);
            TableUtils.createTable(connectionSource, Project.class);
            TableUtils.createTable(connectionSource, Setting.class);
            TableUtils.createTable(connectionSource, SetUpDao.class);
            TableUtils.createTable(connectionSource,LogMember.class);
            TableUtils.createTable(connectionSource,ProjectBillable.class);
            TableUtils.createTable(connectionSource,WorkGroup.class);
            TableUtils.createTable(connectionSource,WorkContactProject.class);
            TableUtils.createTable(connectionSource,LineChart.class);

            initilizecontaint();



        } catch (SQLException e) {
            e.getStackTrace();
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {

            TableUtils.dropTable(connectionSource, Billable.class, true);
            TableUtils.dropTable(connectionSource, LogEntry.class, true);
            TableUtils.dropTable(connectionSource, Contacts.class, true);
            TableUtils.dropTable(connectionSource, Project.class, true);
            TableUtils.dropTable(connectionSource, Setting.class, true);
            TableUtils.dropTable(connectionSource, SetUpDao.class, true);
            TableUtils.dropTable(connectionSource, ProjectBillable.class, true);

            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }


    public Dao<Billable, Integer> getBillableDao() throws SQLException {
        if (billableDao == null) {
            billableDao = getDao(Billable.class);
        }
        return billableDao;
    }

    public Dao<LogEntry, Integer> getCallLogDao() throws SQLException {
        if (call_logDao == null) {
            call_logDao = getDao(LogEntry.class);
        }
        return call_logDao;
    }

    public Dao<Contacts, Integer> getContactsDao() throws SQLException {
        if (contactDao == null) {
            contactDao = getDao(Contacts.class);
        }
        return contactDao;
    }
    public Dao<Project, Integer> getProjectDao() throws SQLException {
        if (projectDao == null) {
            projectDao = getDao(Project.class);
        }
        return projectDao;
    }

    public Dao<Setting, Integer> getSettingDao() throws SQLException {
        if (settingDao == null) {
            settingDao = getDao(Setting.class);
        }
        return settingDao;
    }

    public Dao<SetUpDao, Integer> getSetupDao() throws SQLException {
        if (setUpDao == null) {
            setUpDao = getDao(SetUpDao.class);
        }
        return setUpDao;
    }

    public Dao<LogMember, Integer> getLogMemberDao() throws SQLException {
        if (logMemberDao == null) {
            logMemberDao = getDao(LogMember.class);
        }
        return logMemberDao;
    }

    public Dao<ProjectBillable, Integer> getProjectBillableDao() throws SQLException {
        if (projectBillableDao == null) {
            projectBillableDao = getDao(ProjectBillable.class);
        }
        return projectBillableDao;
    }

    public Dao<WorkGroup, Integer> getWorkGroupDao() throws SQLException {
        if (workGroupDao == null) {
            workGroupDao = getDao(WorkGroup.class);
        }
        return workGroupDao;
    }

    public Dao<WorkContactProject, Integer> getWorkContactProjectDao() throws SQLException {
        if (workContactProjectDao == null) {
            workContactProjectDao = getDao(WorkContactProject.class);
        }
        return workContactProjectDao;
    }

    public Dao<LineChart, Integer> getLineChartDao() throws SQLException {
        if (lineChartDao == null) {
            lineChartDao = getDao(LineChart.class);
        }
        return lineChartDao;
    }

    public Dao<ProjectStatus, Integer> getProjectStatusesDao() throws SQLException {
        if (projectStatusesDao == null) {
            projectStatusesDao = getDao(ProjectStatus.class);
        }
        return projectStatusesDao;
    }


    public Dao<CallLogType, Integer> getCallLogTypeDao() throws SQLException {
        if (callLogTypeDao == null) {
            callLogTypeDao = getDao(CallLogType.class);
        }
        return callLogTypeDao;
    }

    private void initilizecontaint()
    {
        try{
            Dao<Billable, Integer> billableDao=getBillableDao();
            Dao<LogEntry, Integer> call_logDao=getCallLogDao();
            Dao<Contacts, Integer> contactDao=getContactsDao();
            Dao<Project, Integer> projectDao=getProjectDao();
            Dao<Setting, Integer> settingDao=getSettingDao();
            Dao<LogMember,Integer>logMemberDao=getLogMemberDao();
            Dao<ProjectBillable,Integer>projectBillableDao=getProjectBillableDao();
            Dao<WorkGroup,Integer>workGroupDao=getWorkGroupDao();
            Dao<WorkContactProject,Integer>workContactProjectDao=getWorkContactProjectDao();
            Dao<LineChart,Integer>lineChartDao=getLineChartDao();
            Dao<CallLogType,Integer> callLogTypeDao=getCallLogTypeDao();
            Dao<ProjectStatus,Integer> projectStatusesDao=getProjectStatusesDao();

            CallLogType callLog=new CallLogType();
            callLog.log_type="Meeting";
            callLogTypeDao.create(callLog);

            callLog.log_type="Call";
            callLogTypeDao.create(callLog);

            callLog.log_type="Stoper";
            callLogTypeDao.create(callLog);

            ProjectStatus projectStatus=new ProjectStatus();

            projectStatus.status_name="Started";
            projectStatusesDao.create(projectStatus);

            projectStatus.status_name="Completed";
            projectStatusesDao.create(projectStatus);

            projectStatus.status_name="Finished";
            projectStatusesDao.create(projectStatus);

            Contacts contact=new Contacts();

            contact.contact_name="Person";
            contact.number="9854125674";
            contact.alternate_number="9850487956";
            contact.note="Test";
            contact.created_on="14/1/2015";

            Project project=new Project();
            project.short_name="Test";
            project.description="created to test saving";
            project.created_on="11/1/16";
            project.started_on="14/1/16";
            project.completed_on="20/2/16";

            Billable billable=new Billable();
            billable.rate=60.0;
            billable.converted_value=120.0;

            LogEntry logEntry1=new LogEntry();
            logEntry1.log_title="Aion";
            logEntry1.duration="5.00";
            logEntry1.end_time="13.00";
            logEntry1.start_time="12.55";
            logEntry1.project=project;
            logEntry1.type="Test";
            logEntry1.date="19/10/2015";

            LogEntry logEntry2=new LogEntry();
            logEntry2.log_title="Aion";
            logEntry2.duration="5.00";
            logEntry2.end_time="13.00";
            logEntry2.start_time="12.55";
            logEntry2.project=project;
            logEntry2.type="Test";
            logEntry2.date="20/10/2015";


            LogEntry logEntry3=new LogEntry();
            logEntry3.log_title="Aion";
            logEntry3.duration="5.00";
            logEntry3.end_time="13.00";
            logEntry3.start_time="12.55";
            logEntry3.project=project;
            logEntry3.type="Test";
            logEntry3.date="21/10/2015";

            LogEntry logEntry4=new LogEntry();
            logEntry4.log_title="Aion";
            logEntry4.duration="5.00";
            logEntry4.end_time="13.00";
            logEntry4.start_time="12.55";
            logEntry4.project=project;
            logEntry4.type="Test";
            logEntry4.date="22/10/2015";

            LogEntry logEntry5=new LogEntry();
            logEntry5.log_title="Aion";
            logEntry5.duration="5.00";
            logEntry5.end_time="13.00";
            logEntry5.start_time="12.55";
            logEntry5.project=project;
            logEntry5.type="Test";
            logEntry5.date="23/10/2015";

            LogEntry logEntry6=new LogEntry();
            logEntry6.log_title="Aion";
            logEntry6.duration="5.00";
            logEntry6.end_time="13.00";
            logEntry6.start_time="12.55";
            logEntry6.project=project;
            logEntry6.type="Test";
            logEntry6.date="24/10/2015";

            LogEntry logEntry7=new LogEntry();
            logEntry7.log_title="Aion";
            logEntry7.duration="5.00";
            logEntry7.end_time="13.00";
            logEntry7.start_time="12.55";
            logEntry7.project=project;
            logEntry7.type="Test";
            logEntry7.date="25/10/2015";

            LogMember logMember=new LogMember();
            logMember.log_id=logEntry1;
            logMember.contact_id=contact;

            ProjectBillable projectBillable=new ProjectBillable();
            projectBillable.billable_id=billable;
            projectBillable.project_id=project;

            Setting setting=new Setting();
            setting.confirm_time_stop=true;
            setting.start_screen="Home";
            setting.time_interval="5";
            setting.web_access=false;
            setting.work_group="Employee";

            WorkGroup workGroup=new WorkGroup();
            workGroup.groupname="Employee";

            WorkContactProject workContactProject=new WorkContactProject();

            workContactProject.contact_id=contact;
            workContactProject.project_id=project;
            workContactProject.work_group_id=workGroup;


            LineChart lineChart = new LineChart();


            ArrayList<ProjectStatus> projList=(ArrayList)projectStatusesDao.queryForAll();
            for(int i=0;i<5;i++){
                contactDao.create(contact);
                project.project_name="Demo Project"+i;
                if(i%2==0)
                project.project_status=projList.get(0);
                else
                project.project_status=projList.get(2);
                projectDao.create(project);
                billableDao.create(billable);
                logMemberDao.create(logMember);
                projectBillableDao.create(projectBillable);
                settingDao.create(setting);
                workGroupDao.create(workGroup);
                workContactProjectDao.create(workContactProject);

                lineChart.xAxis = i+0.1;
                lineChart.yAxis = i+0.2;

                lineChartDao.create(lineChart);
            }

            List<Project> list=projectDao.queryForAll();

            logEntry1.project=list.get(0);
            call_logDao.create(logEntry1);
            logEntry2.project=list.get(1);
            call_logDao.create(logEntry2);
            logEntry3.project=list.get(2);
            call_logDao.create(logEntry3);
            logEntry4.project=list.get(3);
            call_logDao.create(logEntry4);
            logEntry5.project=list.get(4);
            call_logDao.create(logEntry5);
            logEntry6.project=list.get(0);
            call_logDao.create(logEntry6);
            logEntry7.project=list.get(1);
            call_logDao.create(logEntry7);


            logEntry1.project=list.get(0);
            call_logDao.create(logEntry1);
            logEntry2.project=list.get(1);
            call_logDao.create(logEntry2);
            logEntry3.project=list.get(2);
            call_logDao.create(logEntry3);
            logEntry4.project=list.get(3);
            call_logDao.create(logEntry4);
            logEntry5.project=list.get(4);
            call_logDao.create(logEntry5);
            logEntry6.project=list.get(0);
            call_logDao.create(logEntry6);
            logEntry7.project=list.get(1);
            call_logDao.create(logEntry7);

        }catch (SQLException e){
            e.getStackTrace();
        }

    }
}
