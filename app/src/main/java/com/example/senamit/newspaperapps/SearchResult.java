package com.example.senamit.newspaperapps;


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
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;


public class SearchResult extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItems>>,SearchView.OnQueryTextListener {
    public String QUERY = "q";
    public String name = "india";
    public String APIKEY = "api-key";
    public String KEY = "aef7f093-8dd8-48b2-94c7-4a2f0ec3005c";
    public String ORDER_BY = "order-by";

    private Context context;

    Uri buildUri = null;

    TextView textView;

    public int number = 0;

    public static final String LOG_TAG = SearchResult.class.getSimpleName();

    public String JSON_DATA = "https://content.guardianapis.com/search";

    RecyclerView recycerView;

    RecyclerAdapter recyclerAdapter;

    //    public SearchResult(Context context){
//        this.context= context;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Intent intent = getIntent();
         name = intent.getStringExtra("key");
        Log.i(LOG_TAG, "the key is " + name);

//         textView = (TextView) findViewById(R.id.text1);
//        textView.setText(result);

//                ArrayList<NewsItems> newsItemsArrayList = new ArrayList<NewsItems>();
//        Log.i(LOG_TAG, "arraylist onject created");


        recyclerAdapter = new RecyclerAdapter(new ArrayList<NewsItems>());
        Log.i(LOG_TAG, "object of recycleview is created");
        recycerView = (RecyclerView) findViewById(R.id.recycler_view);
        Log.i(LOG_TAG, "recylceveiw is created");
        recycerView.setLayoutManager(new LinearLayoutManager(this));
        Log.i(LOG_TAG, "setlayout manager");
        recycerView.setHasFixedSize(true);
        Log.i(LOG_TAG, "has fixed size");
//
//
//        recycerView.setAdapter(recyclerAdapter);
//        Log.i(LOG_TAG, "setAdapter called");

//        new SearchResult(this);
//
//       getLoaderManager().initLoader(0, null,this);

        loaderRecycleNews();


    }

    public void loaderRecycleNews() {

        buildUri = Uri.parse(JSON_DATA).buildUpon().appendQueryParameter(QUERY, name).appendQueryParameter(ORDER_BY, "newest").appendQueryParameter(APIKEY, KEY).build();
        Log.i(LOG_TAG, "builduri createde with value " + buildUri.toString());
        number++;

        Log.i(LOG_TAG, "the builduri in fragment is  " + buildUri);
        getSupportLoaderManager().initLoader(number, null, this);


        Log.i(LOG_TAG, "current number of searches is  " + number);
    }

    @Override
    public Loader<List<NewsItems>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, buildUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsItems>> loader, List<NewsItems> newsItemsList) {
        RecyclerAdapter recyclerAdapter1 = new RecyclerAdapter(newsItemsList);
        recycerView.setAdapter(recyclerAdapter1);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsItems>> loader) {
        recyclerAdapter = new RecyclerAdapter(new ArrayList<NewsItems>());
    }
//    public void loaderRecycleNews() {
//
//        buildUri = Uri.parse(JSON_DATA).buildUpon().appendQueryParameter(QUERY, name).appendQueryParameter(ORDER_BY,"newest").appendQueryParameter(APIKEY, KEY).build();
//        Log.i(LOG_TAG, "builduri createde with value " + buildUri.toString());
//        number++;
//
//
//        Log.i(LOG_TAG,"current number of searches is  "+number);
//}


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

