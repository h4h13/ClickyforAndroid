package com.sharebuttons.analytics.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.sharebuttons.analytics.adapter.ContentAdapter;
import com.sharebuttons.analytics.adapter.EngAdapter;
import com.sharebuttons.analytics.adapter.LinksAdapter;
import com.sharebuttons.analytics.adapter.LocaleAdapter;
import com.sharebuttons.analytics.adapter.OthersAdapter;
import com.sharebuttons.analytics.adapter.PlatformAdapter;
import com.sharebuttons.analytics.adapter.SearchesAdapter;
import com.sharebuttons.analytics.R;


public class ListActivity extends MainActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;

    private ContentAdapter mContentAdapter;
    private LinksAdapter mLinksAdapter;
    private SearchesAdapter mSearchesAdapter;
    private LocaleAdapter mLocaleAdapter;
    private PlatformAdapter mPlatformAdapter;
    private EngAdapter mEngAdapter;
    private OthersAdapter mOthersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list); don't use this one drawer wont work
        getLayoutInflater().inflate(R.layout.activity_list, mRelativeLayout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initAll();
        initViewPager();

    }

    private void initAll() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Init Adapters
        mContentAdapter = new ContentAdapter(this, getSupportFragmentManager());
        mLinksAdapter = new LinksAdapter(this, getSupportFragmentManager());
        mSearchesAdapter = new SearchesAdapter(this, getSupportFragmentManager());
        mLocaleAdapter = new LocaleAdapter(this, getSupportFragmentManager());
        mPlatformAdapter = new PlatformAdapter(this, getSupportFragmentManager());
        mEngAdapter = new EngAdapter(this, getSupportFragmentManager());
        mOthersAdapter = new OthersAdapter(this, getSupportFragmentManager());
    }

    private void initViewPager() {
        selectAdapter();

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void selectAdapter() {

        switch (position) {
            case R.id.item_id_2:
                getSupportActionBar().setTitle(R.string.label_content);
                mViewPager.setAdapter(mContentAdapter);
                mTabLayout.setTabsFromPagerAdapter(mContentAdapter);
                break;
            case R.id.item_id_3:
                getSupportActionBar().setTitle(R.string.label_links);
                mViewPager.setAdapter(mLinksAdapter);
                mTabLayout.setTabsFromPagerAdapter(mLinksAdapter);
                break;
            case R.id.item_id_4:
                getSupportActionBar().setTitle(R.string.label_searches);
                mViewPager.setAdapter(mSearchesAdapter);
                mTabLayout.setTabsFromPagerAdapter(mSearchesAdapter);
                break;
            case R.id.item_id_5:
                getSupportActionBar().setTitle(R.string.label_locale);
                mViewPager.setAdapter(mLocaleAdapter);
                mTabLayout.setTabsFromPagerAdapter(mLocaleAdapter);
                break;
            case R.id.item_id_6:
                getSupportActionBar().setTitle(R.string.label_platforms);
                mViewPager.setAdapter(mPlatformAdapter);
                mTabLayout.setTabsFromPagerAdapter(mPlatformAdapter);
                break;
            case R.id.item_id_7:
                getSupportActionBar().setTitle(R.string.label_eng);
                mViewPager.setAdapter(mEngAdapter);
                mTabLayout.setTabsFromPagerAdapter(mEngAdapter);
                break;
            case R.id.item_id_8:
                getSupportActionBar().setTitle(R.string.label_others);
                mViewPager.setAdapter(mOthersAdapter);
                mTabLayout.setTabsFromPagerAdapter(mOthersAdapter);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}