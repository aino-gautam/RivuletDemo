package com.ainosoft.rivuletdemo.client.logscreen;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ainosoft.rivuletdemo.R;

/**
 * Created by comp4 on 13/1/16.
 */
public class EditPopupFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.editpopup, container, false);
        return rootView;
    }


}
