package com.example.senamit.newspaperapps;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItems>>, SearchView.OnQueryTextListener {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    public String JSON_DATA = "https://content.guardianapis.com/search";

    RecyclerView recycerView;

    RecyclerAdapter recyclerAdapter;

    public String QUERY = "q";
    public String name = "debate";
    public String APIKEY = "api-key";
    public String KEY = "test";

    Uri buildUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //I studied that always try to use List instead of arrayList or any other list...
        //because at the end ..if you need anywhere to implement the list ..its easy to use any type of list then like arraylist
        //or any other list
        //but here we cant make object of List ...because List cant be used dierectly ..so we r trying to use arraylist here
        //which already extends the list class
//        List<NewsItems> newsItemsList = new List<NewsItems>() {
//        }

        SearchView searchView = (SearchView)findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);

        buildUri = Uri.parse(JSON_DATA).buildUpon().appendQueryParameter(QUERY,name).appendQueryParameter(APIKEY, KEY).build();

        Log.i(LOG_TAG, "builduri createde with value "+ buildUri.toString());
        ArrayList<NewsItems> newsItemsArrayList = new ArrayList<NewsItems>();
        Log.i(LOG_TAG, "arraylist onject created");

        //here instead of adding a list of newsitems into the araylist in two lines...we have used only once...
//        NewsItems newsItems = new NewsItems("India wins","description of win","the hindu");
//        newsItemsArrayList.add(newsItems);
//       newsItemsArrayList.add(new NewsItems("India wins","description of win","the hindu",R.drawable.bookreading));
//        newsItemsArrayList.add(new NewsItems("i m ","a monle","india times",R.drawable.images));




//        Log.i(LOG_TAG, "list of array list "+newsItemsArrayList.get(0).getNewsHeadline());

        recyclerAdapter = new RecyclerAdapter(new ArrayList<NewsItems>());
        Log.i(LOG_TAG, "object of recycleview is created");
         recycerView = (RecyclerView)findViewById(R.id.recycler_view);
        Log.i(LOG_TAG, "recylceveiw is created");
        recycerView.setLayoutManager(new LinearLayoutManager(this));
        Log.i(LOG_TAG, "setlayout manager");
        recycerView.setHasFixedSize(true);
        Log.i(LOG_TAG,"has fixed size");
//        recycerView.setAdapter(recyclerAdapter);
//        Log.i(LOG_TAG, "setAdapter called");

        getLoaderManager().initLoader(0, null, this);


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

    @Override
    public boolean onQueryTextSubmit(String query) {

        name = query;
        Log.i(LOG_TAG, "the query is "+ name);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
