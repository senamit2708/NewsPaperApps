package com.example.senamit.newspaperapps;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by senamit on 7/10/17.
 */

public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static URL CreateUrl(String urlString) throws MalformedURLException {
        URL url = null;
        if (urlString != null || !urlString.isEmpty()) {
            url = new URL(urlString);
        }
        return url;
    }

    public static String httpJsonArrayCreater(URL url) throws IOException {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        String jsonResponse = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Connection is not able to stablish");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = bufferReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferReader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<NewsItems> extractFeatureFromJson(String jsonResponse) throws JSONException {
        String newsHeadline = null;
        String newsUrl = null;
        String newsPublishDate = null;
        String sectionName = null;
        ArrayList<NewsItems> newsItemsArrayList = new ArrayList<NewsItems>();
        JSONObject basejsonObject = new JSONObject(jsonResponse);
        JSONObject jsonObjectResponse = basejsonObject.optJSONObject("response");
        JSONArray jsonArrayResults = jsonObjectResponse.optJSONArray("results");
        for (int i = 0; i < jsonArrayResults.length(); i++) {
            JSONObject jsonItemObject = jsonArrayResults.optJSONObject(i);
            newsHeadline = jsonItemObject.optString("webTitle");
            newsUrl = jsonItemObject.optString("webUrl");
            newsPublishDate = jsonItemObject.optString("webPublicationDate");
            sectionName = jsonItemObject.optString("sectionName");
            newsItemsArrayList.add(new NewsItems(newsHeadline, newsUrl, newsPublishDate, sectionName));
        }
        return newsItemsArrayList;
    }

    public static ArrayList<NewsItems> fetchNewsRequest(String requestUrl) throws IOException, JSONException {
        if (requestUrl == null) {
            return null;
        }
        URL url = CreateUrl(requestUrl);
        String jsonResponse = httpJsonArrayCreater(url);
        if (jsonResponse==null){
            return null;
        }
        ArrayList<NewsItems> newsItemsArrayList = extractFeatureFromJson(jsonResponse);
        return newsItemsArrayList;
    }
}
