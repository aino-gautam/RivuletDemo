package com.ainosoft.rivuletdemo.client.popuponcall;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ainosoft.rivuletdemo.R;

/**
 * author tushar@ainosoft.com
 */
public class PopupCallActivity extends Activity {

    private Button button;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        RelativeLayout dialogButton = (RelativeLayout) findViewById(R.id.wrongRelativeLayout);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupCallActivity.this.finish();
            }
        });

    }
}
