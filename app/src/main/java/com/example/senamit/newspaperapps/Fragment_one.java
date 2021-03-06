package com.example.senamit.newspaperapps;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class Fragment_one extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsItems>> {

    private static final String LOG_TAG = Fragment_one.class.getSimpleName();
    private String JSON_DATA = "https://content.guardianapis.com/search";
    RecyclerView recycerView;
    RecyclerAdapter recyclerAdapter;
    Context context;
    private String QUERY = "q";
    private String name = "sports";
    private String APIKEY = "api-key";
    private String KEY = "aef7f093-8dd8-48b2-94c7-4a2f0ec3005c";
    private String ORDER_BY = "order-by";
    private String SHOW_TAGS = "show-tags";
    private Uri buildUri = null;
    private int number = 0;
    Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerAdapter = new RecyclerAdapter(new ArrayList<NewsItems>());
        recycerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recycerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycerView.setHasFixedSize(true);
        context = getContext();
        dialogShow();
        loaderRecycleNews();
    }

    private void dialogShow() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_progress);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    @Override
    public Loader<List<NewsItems>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(getContext(), buildUri.toString());
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

    public void loaderRecycleNews() {
        buildUri = Uri.parse(JSON_DATA).buildUpon().appendQueryParameter(QUERY, name).appendQueryParameter(ORDER_BY, "newest").appendQueryParameter(SHOW_TAGS,"contributor").appendQueryParameter(APIKEY, KEY).build();
        number++;
        getLoaderManager().initLoader(number, null, this);
    }
}

