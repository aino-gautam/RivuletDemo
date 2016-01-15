package com.ainosoft.rivuletdemo.server.helper;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.shared.slim.Billable;
import com.ainosoft.rivuletdemo.shared.slim.LogEntry;
import com.ainosoft.rivuletdemo.shared.slim.Contacts;
import com.ainosoft.rivuletdemo.shared.slim.LogMember;
import com.ainosoft.rivuletdemo.shared.slim.Project;
import com.ainosoft.rivuletdemo.shared.slim.ProjectBillable;
import com.ainosoft.rivuletdemo.shared.slim.Setting;
import com.ainosoft.rivuletdemo.shared.slim.SetUpDao;
import com.ainosoft.rivuletdemo.shared.slim.WorkContactProject;
import com.ainosoft.rivuletdemo.shared.slim.WorkGroup;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
/**
 * Created by comp5 on 8/1/16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "rivuletdemo.db";
    private static final int DATABASE_VERSION = 1;

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
            TableUtils.createTable(connectionSource,Billable.class);
            TableUtils.createTable(connectionSource,LogEntry.class);
            TableUtils.createTable(connectionSource,Contacts.class);
            TableUtils.createTable(connectionSource,Project.class);
            TableUtils.createTable(connectionSource,Setting.class);
            TableUtils.createTable(connectionSource, SetUpDao.class);
            TableUtils.createTable(connectionSource,LogMember.class);
            TableUtils.createTable(connectionSource,ProjectBillable.class);
            TableUtils.createTable(connectionSource,WorkGroup.class);
            TableUtils.createTable(connectionSource,WorkContactProject.class);



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
}
