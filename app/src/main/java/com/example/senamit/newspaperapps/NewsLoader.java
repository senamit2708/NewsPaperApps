package com.example.senamit.newspaperapps;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by senamit on 8/10/17.
 */

public class NewsLoader extends AsyncTaskLoader {

    public static final String LOG_TAG = NewsLoader.class.getSimpleName();
    String url = null;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsItems> loadInBackground() {
        if (url == null) {
            Log.i(LOG_TAG, "url is null");
            return null;
        }
        List<NewsItems> newsItemsList = null;
        try {
            Log.i(LOG_TAG, "inside load in background");
            newsItemsList = QueryUtils.fetchNewsRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItemsList;
    }
}

