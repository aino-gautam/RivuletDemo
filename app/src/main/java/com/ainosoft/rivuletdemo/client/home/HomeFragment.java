package com.ainosoft.rivuletdemo.client.home;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ainosoft.rivuletdemo.R;

/**
 * author tushar@ainosoft.com
 */
public class HomeFragment extends Fragment {

    private TextView timerValue;
    private ProgressBar progressBar;
    private Handler customHandler = new Handler();

    private int timerFlag;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;
    private long startTime = 0L;

    private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        timerValue = (TextView)rootView.findViewById(R.id.timerValue);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);

        progressBar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (timerFlag == 0) {

                    timerFlag = 1;

                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);

                } else {

                    timerFlag = 0;

                    timeSwapBuff += timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                }
            }
        });

        return rootView;
    }


    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            progressBar.setProgress(mins);
            int hours = mins / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText(""+hours+":" + mins + ":" + String.format("%02d", secs));
            customHandler.postDelayed(this, 0);
        }

    };




    /**
     * This method is use to create new instance of this fragment which returns view of Fragment
     * @param sectionNumber
     * @return HomeLogScreenFragment
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
