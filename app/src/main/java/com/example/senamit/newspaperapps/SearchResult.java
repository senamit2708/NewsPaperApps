package com.example.senamit.newspaperapps;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchResult extends AppCompatActivity {

    public static final String LOG_TAG = SearchResult.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        Intent intent = getIntent();
//        String result = intent.getStringExtra("key");
//        Log.i(LOG_TAG, "the key is " +result);

        TextView textView = (TextView) findViewById(R.id.text);
//        textView.setText(result);

//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.options_menu, menu);
//
//            SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
//            MenuItem itemSearch = menu.findItem(R.id.search_button);
//            SearchView searchView = (SearchView)itemSearch.getActionView();
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//            searchView.setSubmitButtonEnabled(true);
//            searchView.setOnQueryTextListener(this);
//
//            return true;
//        }


    }
}
