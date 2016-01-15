package com.ainosoft.rivuletdemo.client.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.server.dao.Billable;
import com.ainosoft.rivuletdemo.server.dao.SetUpDao;
import com.ainosoft.rivuletdemo.server.helper.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by comp5 on 8/1/16.
 */
public class AddProject extends Activity {

    // Reference of DatabaseHelper class to access its DAOs and other components
    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        Button b = (Button) findViewById(R.id.submitbutton);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "New Activity is going to start", Toast.LENGTH_LONG).show();

              /*  try {
                    final Dao<SetUpDao, Integer>  setupDao= getHelper().getSetupDao();

                    SetUpDao setup=new SetUpDao();
                    setupDao.create(setup);

                    setupDao.delete(setup);

                } catch (SQLException e) {
                    e.printStackTrace();
                }*/
            }
        });


        Button b1=(Button)findViewById(R.id.showbutton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Debugging started",Toast.LENGTH_LONG).show();
            }
        });

    }

    // This is how, DatabaseHelper can be initialized for future use
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

/*

    public void testmethod() {
        final Project techDetails = new Project();
        techDetails.project_name = "Hello";
        // This is how, a reference of DAO object can be done
        try {
            final Dao<Project, Integer> techerDao = getHelper().getProjectDao();


            // techDetails.projectName="HelloWorld";
            //This is the way to insert data into a database table
            techerDao.create(techDetails);
        } catch (SQLException e) {
            e.getStackTrace();
        }

    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();

		/*
         * You'll need this in your class to release the helper when done.
		 */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
