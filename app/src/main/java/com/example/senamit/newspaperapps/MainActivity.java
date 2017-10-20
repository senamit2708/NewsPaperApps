package com.example.senamit.newspaperapps;





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
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public String name = "india";
    private TabLayout tabLayout;
    boolean connected = false;
    Snackbar snackbar;

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
            setUpViewPager(viewPager);
        }
    }

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
        viewPager.setAdapter(pagerAdapter);
    }
}
