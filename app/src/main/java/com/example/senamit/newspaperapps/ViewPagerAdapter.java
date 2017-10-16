package com.example.senamit.newspaperapps;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senamit on 16/10/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public static final String LOG_TAG = ViewPagerAdapter.class.getSimpleName();


    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String>fragmentTitle = new ArrayList<String>();





    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    public void addFragment(Fragment fragment, String name){

        fragmentList.add(fragment);
        fragmentTitle.add(name);
        Log.i(LOG_TAG, "fragment is added");

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  fragmentTitle.get(position);
    }

}
