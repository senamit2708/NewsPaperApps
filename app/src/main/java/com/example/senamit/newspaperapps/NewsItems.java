package com.example.senamit.newspaperapps;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by senamit on 7/10/17.
 */

public class NewsItems {

    private String NewsHeadline;
    private String newsDescription;
    private String NewsSource;
    //her for image i use int for sometime ..because R.drwablw is int...whatever R holds..it hold in int formate
    private int newsThumbnail;


    public NewsItems(String newsHeadline, String newsDescription, String newsSource, int newsThumbnail) {
        NewsHeadline = newsHeadline;
        this.newsDescription = newsDescription;
        NewsSource = newsSource;
        this.newsThumbnail = newsThumbnail;
    }

    public String getNewsHeadline() {
        return NewsHeadline;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public String getNewsSource() {
        return NewsSource;
    }


    public int getNewsThumbnail() {
        return newsThumbnail;
    }
}
