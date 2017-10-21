package com.example.senamit.newspaperapps;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;


public class SearchResult extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItems>>, SearchView.OnQueryTextListener {
    private String QUERY = "q";
    private String name = "india";
    private String APIKEY = "api-key";
    private String KEY = "aef7f093-8dd8-48b2-94c7-4a2f0ec3005c";
    private String ORDER_BY = "order-by";
    private String SHOW_TAGS = "show-tags";
    private Uri buildUri = null;
    private int number = 0;
    private static final String LOG_TAG = SearchResult.class.getSimpleName();
    private String JSON_DATA = "https://content.guardianapis.com/search";
    RecyclerView recycerView;
    RecyclerAdapter recyclerAdapter;
    Dialog dialog;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchResult.this, MainActivity.class);
        startActivity(intent);
        Log.i(LOG_TAG, "Inside back button");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        name = intent.getStringExtra("key");
        recyclerAdapter = new RecyclerAdapter(new ArrayList<NewsItems>());
        recycerView = (RecyclerView) findViewById(R.id.recycler_view);
        recycerView.setLayoutManager(new LinearLayoutManager(this));
        recycerView.setHasFixedSize(true);
        dialogShower();
        loaderRecycleNews();
    }

    private void dialogShower() {
        dialog = new Dialog(SearchResult.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_progress);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    public void loaderRecycleNews() {
        buildUri = Uri.parse(JSON_DATA).buildUpon().appendQueryParameter(QUERY, name).appendQueryParameter(ORDER_BY, "newest").appendQueryParameter(SHOW_TAGS,"contributor").appendQueryParameter(APIKEY, KEY).build();
        number++;
        getSupportLoaderManager().initLoader(number, null, this);
    }

    @Override
    public Loader<List<NewsItems>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, buildUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsItems>> loader, List<NewsItems> newsItemsList) {
        RecyclerAdapter recyclerAdapter1 = new RecyclerAdapter(newsItemsList);
        recycerView.setAdapter(recyclerAdapter1);
        dialog.dismiss();
    }

    @Override
    public void onLoaderReset(Loader<List<NewsItems>> loader) {
        recyclerAdapter = new RecyclerAdapter(new ArrayList<NewsItems>());
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
        loaderRecycleNews();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

}

