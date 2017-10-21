package com.example.senamit.newspaperapps;

/**
 * Created by senamit on 7/10/17.
 */

public class NewsItems {

    private String newsHeadline;
    private String newsUrl;
    private String newsPublishDate;
    private String sectionName;
    private String authorName;


    public NewsItems(String newsHeadline, String newsUrl, String newsPublishDate, String sectionName) {
        this.newsHeadline = newsHeadline;
        this.newsUrl = newsUrl;
        this.newsPublishDate = newsPublishDate;
        this.sectionName = sectionName;
    }

    public NewsItems(String newsHeadline, String newsUrl, String newsPublishDate, String sectionName, String authorName) {
        this.newsHeadline = newsHeadline;
        this.newsUrl = newsUrl;
        this.newsPublishDate = newsPublishDate;
        this.sectionName = sectionName;
        this.authorName = authorName;
    }

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getNewsPublishDate() {
        return newsPublishDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthorName() {
        return authorName;
    }
}


