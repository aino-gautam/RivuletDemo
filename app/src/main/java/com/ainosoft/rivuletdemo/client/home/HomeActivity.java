package com.ainosoft.rivuletdemo.client.home;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ainosoft.rivuletdemo.R;

/**
 * author tushar@ainosoft.com
 */
public class HomeActivity extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home, container, false);
        return rootView;
    }

    /**
     * This method is use to create new instance of this fragment which returns view of Fragment
     * @param sectionNumber
     * @return HomeLogScreenFragment
     */
    public static HomeActivity newInstance(int sectionNumber) {
        HomeActivity fragment = new HomeActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
