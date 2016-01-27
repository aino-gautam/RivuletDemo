package com.ainosoft.rivuletdemo.client.appbase;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ainosoft.rivuletdemo.R;
import com.ainosoft.rivuletdemo.client.charts.histogram.HistogramActivity;
import com.ainosoft.rivuletdemo.client.charts.linechart.LineChartActivity;
import com.ainosoft.rivuletdemo.client.charts.piechart.PieChartActivity;

/**
 * author mahesh@ainosoft.com
 */
public class BaseManager extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ImageView humberger_Image=null;
    private ClipData.Item home;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       try {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_base_manager);

           // Create the adapter that will return a fragment for each of the three
           // primary sections of the activity.
           mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

           // Set up the ViewPager with the sections adapter.
           mViewPager = (ViewPager) findViewById(R.id.container);
           mViewPager.setAdapter(mSectionsPagerAdapter);

           humberger_Image=(ImageView)findViewById(R.id.drawer_humburger);

           humberger_Image.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                   drawer.openDrawer(GravityCompat.START);
               }
           });
           final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
           tabLayout.setupWithViewPager(mViewPager);
           tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#44cceb"));
           tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

               @Override
               public void onTabSelected(TabLayout.Tab tab) {
                   tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#44cceb"));
               }

               @Override
               public void onTabUnselected(TabLayout.Tab tab) {
               }

               @Override
               public void onTabReselected(TabLayout.Tab tab) {
               }
           });


           /******************************For Scroll Handling of LogScreen**********************************************************/

           Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForScroll);
           setSupportActionBar(toolbar);
           ActionBar actionBar = getSupportActionBar();
           actionBar.setDisplayHomeAsUpEnabled(true);


           collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
           collapsingToolbarLayout.setTitle(getResources().getString(R.string.project_name));

           dynamicToolbarColor();
           toolbarTextAppernce();
       }catch (Exception e){
           Log.d("BaseManager","onCreate(): "+e);
       }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_base_manager, menu);
        }catch (Exception e){
            Log.d("BaseManager","onCreateOptionsMenu(): "+e);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }
        }catch (Exception e){
            Log.d("BaseManager","onOptionsItemSelected(): "+e);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is use to create dialog box on click of title of log screen
     * @param view
     */
    public void openAlert(View view) {
    try {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BaseManager.this);
        alertDialogBuilder.setTitle("Edit Title");

        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
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

        final EditText input = new EditText(BaseManager.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setView(input);
        alertDialog.show();
    }catch (Exception e){
        Log.d("BaseManager","openAlert(): "+e);
    }
  }

    /**
     * This method is use to set color to the title bar on scroll in log screen
     * the title bar will get color of the behind widget.
     */
    private void dynamicToolbarColor() {
    try {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rivulet_icon);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark));
            }
        });
    }catch (Exception e){
        Log.d("BaseManager","dynamicToolbarColor(): "+e);
    }
    }

    /**
     * this method is use to set title to the scroll widget of log screen
     */
    private void toolbarTextAppernce() {
        try {
            collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
            collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
        }catch (Exception e){
            Log.d("BaseManager","toolbarTextAppernce(): "+e);
        }
    }

    /**
     * This method is use to create view of linechart
     * @param view
     */
    public void lineChart(View view){
        Intent intent = new Intent(BaseManager.this, LineChartActivity.class);
        startActivity(intent);
    }

    /**
     * This method is use to create view of histogram
     * @param view
     */
    public void histogramView(View view){
        Intent intent = new Intent(this,HistogramActivity.class);
        startActivity(intent);
    }

    /**
     * This method is use to create view of piechart
     * @param view
     */
    public void pieChartView(View view){
        Intent intent = new Intent(this,PieChartActivity.class);
        startActivity(intent);
    }



}
