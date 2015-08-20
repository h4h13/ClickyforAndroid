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
public class OthersAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    public OthersAdapter(Context context, FragmentManager manager) {
        super(manager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = TypeListFragment.getInstance("tweets");
                break;
            case 1:
                fragment = TypeListFragment.getInstance("traffic-sources");
                break;
            case 2:
                fragment = TypeListFragment.getInstance("visitors-most-active");
                break;
            case 3:
                fragment = TypeListFragment.getInstance("goals");
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.others).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.others)[position];
    }
}
