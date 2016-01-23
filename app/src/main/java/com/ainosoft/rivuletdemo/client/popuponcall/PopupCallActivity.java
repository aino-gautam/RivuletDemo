package com.ainosoft.rivuletdemo.client.popuponcall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ainosoft.rivuletdemo.R;

/**
 * author tushar@ainosoft.com
 */
public class PopupCallActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_call);

        openAlert();
    }

    /**
     * This method is use to create dialog box.
     */
    private void openAlert() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PopupCallActivity.this);

        //alertDialogBuilder.setTitle(this.getTitle()+ " decision");
        //alertDialogBuilder.setMessage("Are you sure?");

        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // go to a new activity of the app
               /* Intent positveActivity = new Intent(getApplicationContext(), PositiveActivity.class);
                startActivity(positveActivity);*/
                PopupCallActivity.this.finish();
                Toast.makeText(getApplicationContext(), "You pressed Yes button", Toast.LENGTH_LONG).show();
            }
        });

        // set negative button: No message
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // cancel the alert box and put a Toast to the user
                PopupCallActivity.this.finish();
                //dialog.cancel();
                Toast.makeText(getApplicationContext(), "You pressed No button", Toast.LENGTH_LONG).show();
            }
        });

        // set neutral button: Exit the app message
        alertDialogBuilder.setNeutralButton("Exit app.", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // exit the app and go to the HOME
                PopupCallActivity.this.finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        // show alert
        alertDialog.show();
    }

}
