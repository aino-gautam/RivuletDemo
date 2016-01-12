package com.ainosoft.rivuletdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ainosoft.rivuletdemo.client.login.LoginActivity;
import com.ainosoft.rivuletdemo.shared.slim.SetUpDao;
import com.ainosoft.rivuletdemo.server.helper.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/*
Landing page
 */
public class LandingActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

   public void loginInApp(View view){
       initilizeDatabase();
        Intent intent = new Intent(LandingActivity.this, LoginActivity.class);
        startActivity(intent);
    }

public void initilizeDatabase(){
    try {
        final Dao<SetUpDao, Integer> setupDao = getHelper().getSetupDao();

        SetUpDao setup = new SetUpDao();
        setupDao.create(setup);

        setupDao.delete(setup);
    }catch (SQLException e){
        e.getStackTrace();
    }
}

    // This is how, DatabaseHelper can be initialized for future use
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
}
