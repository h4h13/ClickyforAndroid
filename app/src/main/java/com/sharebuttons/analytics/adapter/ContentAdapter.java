package com.sharebuttons.analytics.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sharebuttons.analytics.fragment.TypeListFragment;
import com.sharebuttons.analytics.R;


/**
 * Created by Monkey D Luffy on 6/6/2015.
 */
public class ContentAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    public ContentAdapter(Context context, FragmentManager manager) {
        super(manager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = TypeListFragment.getInstance("pages");
                break;
            case 1:
                fragment = TypeListFragment.getInstance("pages-entrance");
                break;
            case 2:
                fragment = TypeListFragment.getInstance("pages-exit");
                break;
            case 3:
                fragment = TypeListFragment.getInstance("downloads");
                break;
            case 4:
                fragment = TypeListFragment.getInstance("events");
                break;
            case 5:
                fragment = TypeListFragment.getInstance("video");
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.content).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.content)[position];
    }
}
