package com.example.senamit.newspaperapps;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senamit on 16/10/17.
 */

public class Fragment_two extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsItems>> {

    public static final String LOG_TAG = Fragment_two.class.getSimpleName();
    public String JSON_DATA = "https://content.guardianapis.com/search";
    RecyclerView recycerView;
    RecyclerAdapter recyclerAdapter;
    Context context;
    private String QUERY = "q";
    private String name = "weather";
    private String APIKEY = "api-key";
    private String KEY = "aef7f093-8dd8-48b2-94c7-4a2f0ec3005c";
    private String ORDER_BY = "order-by";
    private String SHOW_TAGS = "show-tags";
    Uri buildUri = null;
    private int number = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerAdapter = new RecyclerAdapter(new ArrayList<NewsItems>());
        recycerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view2);
        recycerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycerView.setHasFixedSize(true);
        context = getContext();
        loaderRecycleNews();
    }

    @Override
    public Loader<List<NewsItems>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(getContext(), buildUri.toString());
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

    public void loaderRecycleNews() {
        buildUri = Uri.parse(JSON_DATA).buildUpon().appendQueryParameter(QUERY, name).appendQueryParameter(ORDER_BY, "newest").appendQueryParameter(SHOW_TAGS,"contributor").appendQueryParameter(APIKEY, KEY).build();
        number++;
        Log.i(LOG_TAG, "the builduri in fragment is  " + buildUri);
        getLoaderManager().initLoader(number, null, this);
    }
}

