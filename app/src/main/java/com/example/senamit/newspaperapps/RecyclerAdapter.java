package com.example.senamit.newspaperapps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by senamit on 7/10/17.
 */
//first of all here we r trying to create a recylerview Adapter..so lets start the process
    //class name as custom recyclerAdapter - here RecyclerAdapter
    //obviously it will extend the RecylerView adapter
    //now as we have selected the RecyclerView.Adapter<VH>  ...so we have to use the viewholder....
    //so..for using viewholder we have to call it....and it will be called using RecycleAdapter..
    //https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder.html
    //now all the override methods of RecyclerApater to be implemented
    //viewholder abstract class is autommatically created which will extend the RecyclerView.ViewHolder

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static final String LOG_TAG = RecyclerAdapter.class.getSimpleName();

    List<NewsItems>newsItemsList;
    Context context;

    //constructoe is created so that we can get the array list from the calling class arraylist..to populate it into view...
    //as we know adapter is used to be a bridge between data(list) and View ...so here araylist is taken
    public RecyclerAdapter(List<NewsItems> newsItemsList) {
        this.newsItemsList = newsItemsList;
        Log.i(LOG_TAG, "construstoc loaded of adapter");
    }


    //this method is used to inflate the layout...
    //first we know its a layoutinflator becuase we have to inflate an layout here
    //then where to populate it....so on parent context
    //which layout to populate...so ...where we have all the items for that recylceview
    //return type should be custome view holder because custome view holder holds the findviewbyid of items
    //then return type of this method is used as an input parameter of onBindViewHOlder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news_items, parent, false);

        //so here the return typw is viewholder....
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;

        Log.i(LOG_TAG,"inside first method of oncreate");
        return new ViewHolder(view);

    }

    //here it is used for binding the arraylist with the viewitems
    //so holder already contains the viewitems names
    //position is the row number or arraylist[position]
    //now we will join both of them to get the populaetd result on screen
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.newsHeadline.setText(newsItemsList.get(position).getNewsHeadline());
        //here i dont want to show link of news so i put it into comment section
//        holder.newsDescription.setText(newsItemsList.get(position).getNewsDescription());
        final  Uri link = Uri.parse(newsItemsList.get(position).getNewsDescription());
        holder.newsSource.setText(newsItemsList.get(position).getNewsSource());
        //setimage resource accept int value..thats why its used
        holder.newsThumbnail.setImageResource(newsItemsList.get(position).getNewsThumbnail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, link);

                context.startActivity(websiteIntent);
            }
        });

        Log.i(LOG_TAG, "inside onbindviewholder");
    }

    //size of arraylist ....total number ...
    @Override
    public int getItemCount() {
        return newsItemsList.size();
    }
    //so....first question why r we using viewholder
    //its just because if we r using simple adpaters like array adpaters and others....we have to call findViewById everytime for each
    //variable and also for population each item list....so its memory wastage to calll again and agian
    //so here we make once abstract class of viewholder and stores all the varaible and there find view by is...
    //so that we can diurectly call this class and implement it as required in onBindViewHolder methods


    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView newsHeadline;
        TextView newsDescription;
        TextView newsSource;
        ImageView newsThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);


            newsHeadline= itemView.findViewById(R.id.newsHeadline);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsSource = itemView.findViewById(R.id.newsSource);
            newsThumbnail=itemView.findViewById(R.id.newsThumbnail);

            context = itemView.getContext();

            Log.i(LOG_TAG, "Inside viewholder constructo");
        }



    }
}
