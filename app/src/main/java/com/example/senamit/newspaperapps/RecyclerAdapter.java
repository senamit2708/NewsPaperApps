package com.example.senamit.newspaperapps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by senamit on 7/10/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static final String LOG_TAG = RecyclerAdapter.class.getSimpleName();

    List<NewsItems> newsItemsList;
    Context context;

    public RecyclerAdapter(List<NewsItems> newsItemsList) {
        this.newsItemsList = newsItemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.newsHeadline.setText(newsItemsList.get(position).getNewsHeadline());
        final Uri link = Uri.parse(newsItemsList.get(position).getNewsUrl());
        holder.newsPublishDate.setText(newsItemsList.get(position).getNewsPublishDate());
        holder.sectionName.setText(newsItemsList.get(position).getSectionName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, link);
                context.startActivity(websiteIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsHeadline;
        TextView newsUrl;
        TextView newsPublishDate;
        TextView sectionName;

        public ViewHolder(View itemView) {
            super(itemView);
            newsHeadline = itemView.findViewById(R.id.newsHeadline);
            newsUrl = itemView.findViewById(R.id.newsUrl);
            newsPublishDate = itemView.findViewById(R.id.newsPublishDate);
            sectionName = itemView.findViewById(R.id.sectionName);
            context = itemView.getContext();
            Log.i(LOG_TAG, "Inside viewholder constructo");
        }
    }
}
