package com.ainosoft.rivuletdemo.client.projectscreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.client.appbase.BaseManager;

import java.util.ArrayList;
import java.util.List;

public class ProjectScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_screen);

        Spinner spinner=(Spinner)findViewById(R.id.projectscreenspinner);

        List<String> list=new ArrayList<>();
        list.add("By name");
        list.add("By size");
        list.add("By date");
        list.add("By short name");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        TableLayout parentLayout=(TableLayout)findViewById(R.id.projectnamerelativeLayout);

        int i;
        for(i=1;i<=5;i++) {

            TableRow.LayoutParams relativeParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);


            TableRow tableRow=new TableRow(this);
            TableLayout.LayoutParams tableRowParams= new TableLayout.LayoutParams
                    (TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=10;
            int topMargin=3;
            int rightMargin=0;
            int bottomMargin=2;

            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tableRow.setLayoutParams(tableRowParams);

            tableRow.setGravity(View.TEXT_ALIGNMENT_CENTER);

            RelativeLayout relativeLayout=new RelativeLayout(this);
            relativeLayout.setPadding(10,10,10,10);

            relativeLayout.setLayoutParams(relativeParams);

            TextView textView=new TextView(this);
            textView.setText("Project Long Name\nProjetcName\n12h7m");
            RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

            relativeLayout.addView(textView, textParams);



            TextView subTextView=new TextView(this);
            subTextView.setText("PLANED:");
            RelativeLayout.LayoutParams subtextParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            subtextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            relativeLayout.addView(subTextView, subtextParams);


            tableRow.addView(relativeLayout);
            tableRow.setBackgroundResource(R.drawable.row_border);


           parentLayout.addView(tableRow);

        }

        LinearLayout createNewProject=(LinearLayout)findViewById(R.id.newproject);

        createNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlert(v);
            }
        });

    }

    /**
     * This method is use to create dialog box on click of title of log screen
     * @param view
     */
    public void openAlert(View view) {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProjectScreen.this);
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

            final EditText input = new EditText(ProjectScreen.this);
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

}
