package com.ainosoft.rivuletdemo.client.projectscreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.server.helper.DatabaseHelper;
import com.ainosoft.rivuletdemo.shared.slim.Project;
import com.j256.ormlite.dao.Dao;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * author swapnil@ainosoft.com
 */
public class ProjectScreen extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_project_screen, container, false);
        try {
            Spinner spinner = (Spinner) rootView.findViewById(R.id.projectscreenspinner);

            List<String> list = new ArrayList<>();
            list.add("By name");
            list.add("By size");
            list.add("By date");
            list.add("By short name");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);

            TableLayout parentLayout = (TableLayout) rootView.findViewById(R.id.projectnamerelativeLayout);

            int i;

            DatabaseHelper dbHelper = new DatabaseHelper(getContext());

            Dao<Project, Integer> projectDao = dbHelper.getProjectDao();
            final ArrayList<Project> projectList=(ArrayList)projectDao.queryForAll();
            for (i = 0; i < projectList.size(); i++) {
                ProjectScreenRow projectScreen = new ProjectScreenRow();

                TableRow tableRow = projectScreen.onCreateTableRow(getContext(),projectList.get(i));


                tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                parentLayout.addView(tableRow);


            }

            LinearLayout createNewProject = (LinearLayout) rootView.findViewById(R.id.newproject);

            createNewProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAlert(v);
                }
            });

        }catch(SQLException e){
            e.getStackTrace();
        }
        return rootView;
    }


    /**
     * This method is use to create dialog box on click of title of log screen
     * @param view
     */
    public void openAlert(View view) {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle("Create New Project");

// set positive button: Yes message
            alertDialogBuilder.setPositiveButton("Create Project", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
// go to a new activity of the app
/*Intent positveActivity = new Intent(getContext(), PositiveActivity.class);
startActivity(positveActivity);*/
                }
            });

            // set neutral button: Exit the app message
            alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // exit the app and go to the HOME
                    //AlertDialogActivity.this.finish();
                }
            });

            final EditText input = new EditText(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setHint("Project Name");
            input.setLayoutParams(lp);

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setView(input);
            alertDialog.show();


        } catch (Exception e) {
            Log.d("BaseManager", "openAlert(): " + e);
        }
    }


    /**
     * This method is use to create new instance of this fragment which returns view of Fragment
     * @param sectionNumber
     * @return HomeLogScreenFragment
     */
    public static ProjectScreen newInstance(int sectionNumber) {
        ProjectScreen fragment = new ProjectScreen();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

}
