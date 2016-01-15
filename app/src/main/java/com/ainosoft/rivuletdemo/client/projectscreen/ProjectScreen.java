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
import android.support.v4.app.Fragment;
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

        Spinner spinner=(Spinner)rootView.findViewById(R.id.projectscreenspinner);

        List<String> list=new ArrayList<>();
        list.add("By name");
        list.add("By size");
        list.add("By date");
        list.add("By short name");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        TableLayout parentLayout=(TableLayout)rootView.findViewById(R.id.projectnamerelativeLayout);

        int i;
        for(i=1;i<=10;i++) {

            TableRow.LayoutParams relativeParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);


            TableRow tableRow=new TableRow(getActivity());
            TableLayout.LayoutParams tableRowParams= new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=5;
            int topMargin=3;
            int rightMargin=0;
            int bottomMargin=2;

            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tableRow.setLayoutParams(tableRowParams);

            tableRow.setGravity(View.TEXT_ALIGNMENT_CENTER);

            RelativeLayout relativeLayout=new RelativeLayout(getActivity());
            relativeLayout.setPadding(10,10,10,10);

            relativeLayout.setLayoutParams(relativeParams);

            TextView textView=new TextView(getActivity());
            textView.setText("Project Long Name\nProjetcName\n12h7m");
            RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

            relativeLayout.addView(textView, textParams);



            TextView subTextView=new TextView(getActivity());
            subTextView.setText("PLANED:");
            RelativeLayout.LayoutParams subtextParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            subtextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            relativeLayout.addView(subTextView, subtextParams);


            tableRow.addView(relativeLayout);
            tableRow.setBackgroundResource(R.drawable.row_border);


            parentLayout.addView(tableRow);

        }

        LinearLayout createNewProject=(LinearLayout)rootView.findViewById(R.id.newproject);

        createNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlert(v);
            }
        });

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
