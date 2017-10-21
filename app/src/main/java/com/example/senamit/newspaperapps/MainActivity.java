package com.example.senamit.newspaperapps;





import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;



import android.support.v4.view.ViewPager;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private TabLayout tabLayout;
    boolean connected = false;
    String name = null;

//
//    Sometimes you want close the entire application in certain back button press.
//    Here best practice is open up the home window instead of exiting application.
//    For that you need to override onBackPressed() method. usually this method open up the top activity in the stack.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if (!CheckNetwork.isInternetAvailable(getApplicationContext())) {
            AlertDialogSettingFragment alertDialogSettingFragment = new AlertDialogSettingFragment();
            alertDialogSettingFragment.show(getSupportFragmentManager(), "dialog");



        }
        else {
//            progreesDialog();
            setUpViewPager(viewPager);
        }
    }

//    private void progreesDialog() {
//
//         progress = new ProgressDialog(this);
//        progress.setTitle("Loading");
//        progress.setMessage("Wait while loading...");
//        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
//        progress.show();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem itemSearch = menu.findItem(R.id.search_button);
        SearchView searchView = (SearchView) itemSearch.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        name = query;
        intentCreator();
        return true;
    }

    private void intentCreator() {
        Intent intent = new Intent(MainActivity.this, SearchResult.class);
        intent.putExtra("key", name);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new Fragment_one(), "Sports");
        pagerAdapter.addFragment(new Fragment_two(), "Weather");
//        pagerAdapter.addFragment(new Fragment_three(), "Politics");
        viewPager.setAdapter(pagerAdapter);
//        progress.dismiss();
    }
}
