package com.sharebuttons.analytics.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sharebuttons.analytics.Clicky.Clicky;
import com.sharebuttons.analytics.Clicky.Current;
import com.sharebuttons.analytics.R;
import com.sharebuttons.analytics.adapter.SelectWebsiteAdapter;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    protected static int position;
    protected Toolbar mToolbar;
    protected NavigationView mDrawer;
    protected RelativeLayout mRelativeLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    @Bind(R.id.tvVisitor)
    TextView mVisitorsView;
    @Bind(R.id.tvVisitorNew)
    TextView mVisitorsNewView;
    @Bind(R.id.tvUniqueVisitors)
    TextView mVisitorsUniqueView;
    @Bind(R.id.tvActions)
    TextView mActionsView;
    @Bind(R.id.tvActionsPageViews)
    TextView mActionsPageView;
    @Bind(R.id.tvActionsDownload)
    TextView mActionsDownloadView;
    @Bind(R.id.tvActionsOutboundLinks)
    TextView mActionsOutboundLinksView;
    @Bind(R.id.tvActionsEvent)
    TextView mActionsEvent;
    @Bind(R.id.tvAvgActions)
    TextView mAvgActionsView;
    @Bind(R.id.tvAvgTimePerVisit)
    TextView mAvgActionsPerVisitView;
    @Bind(R.id.tvTotalTime)
    TextView mTotalTimeView;
    @Bind(R.id.tvBounceRate)
    TextView mBounceRateView;
    @Bind(R.id.websites)
    Spinner mSpinner;

    SharedPreferences sharedPreferences;

    private Clicky mClicky;
    private String mUserOnlineCount = String.valueOf(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initAllVariables();
        setSupportActionBar(mToolbar);
        setUpDrawer();

        loadPrefs();

    }

    private void loadPrefs() {

        ArrayList<String> websites = new ArrayList<>();
        if (sharedPreferences.contains("LIST")) {
            for (int i = 0; i < sharedPreferences.getInt("LIST", 0); i++) {
                websites.add(sharedPreferences.getString("NICKNAME" + i, null));
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        mSpinner.setAdapter(new SelectWebsiteAdapter(getApplicationContext(), websites));
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        getCurrent(sharedPreferences.getString("ID" + position, null), sharedPreferences.getString("KEY" + position, null));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SITE_ID_STRING", sharedPreferences.getString("ID" + position, null));
        editor.putString("SITE_KEY_STRING", sharedPreferences.getString("KEY" + position, null));
        editor.apply();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initAllVariables() {
        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.content_frame);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    private void setUpDrawer() {
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        position = menuItem.getItemId();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        menuItem.setChecked(true);

        Intent intent;
        switch (position) {
            case R.id.item_id_1:
                //Today
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.item_id_2:
                //Content
                gotoListActivity();
                break;
            case R.id.item_id_3:
                //Links
                gotoListActivity();
                break;
            case R.id.item_id_4:
                //Searches
                gotoListActivity();
                break;
            case R.id.item_id_5:
                //Locale
                gotoListActivity();
                break;
            case R.id.item_id_6:
                //Platforms
                gotoListActivity();
                break;
            case R.id.item_id_7:
                //Engg
                gotoListActivity();
                break;
            case R.id.item_id_8:
                //Others
                gotoListActivity();
                break;
            case R.id.item_id_9:
                this.finish();
                break;
            case R.id.item_id_10:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.item_id_11:
                Toast.makeText(getApplicationContext(), "Clear all Login", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }

        return false;
    }

    private void gotoListActivity() {
        Intent intent;
        intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private Clicky getClickyData(String jsonData) throws JSONException {
        Clicky clicky = new Clicky();
        clicky.setCurrent(getCurrentData(jsonData));
        return clicky;
    }

    private Current getCurrentData(String jsonData) throws JSONException {
        ArrayList<String> values = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonData);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray datesArray = jsonObject.getJSONArray("dates");
            for (int j = 0; j < datesArray.length(); j++) {
                JSONObject dates = datesArray.getJSONObject(j);
                JSONArray itemsArray = dates.getJSONArray("items");
                for (int k = 0; k < itemsArray.length(); k++) {
                    JSONObject items = itemsArray.getJSONObject(k);
                    values.add(items.getString("value"));

                }
            }

        }

        Current current = new Current();
        current.setVisitors(values.get(0));
        current.setActions(values.get(1));
        current.setAvgActions(values.get(2));
        current.setAvgTimePerVisit(values.get(3));
        current.setTotalTime(values.get(4));
        current.setBounceRate(values.get(5));
        current.setUserOnline(values.get(6));
        current.setVisitorsNew(values.get(7));
        current.setVisitorsUnique(values.get(8));
        current.setActionsPageView(values.get(9));
        current.setActionsDownload(values.get(10));
        current.setActionsOutboundLinks(values.get(11));
        current.setActionsEvent(values.get(12));
        return current;
    }

    private void networkUnavailable() {

        Snackbar.make(mRelativeLayout,
                R.string.network_unavailable,
                Snackbar.LENGTH_LONG).setAction(android.R.string.ok,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }

    private void setDisplay() {
        Current current = mClicky.getCurrent();

        mVisitorsView.setText(current.getVisitors());
        mVisitorsNewView.setText(current.getVisitorsNew());
        mVisitorsUniqueView.setText(current.getVisitorsUnique());
        setNotificationCount(current.getUserOnline());
        mActionsView.setText(current.getActions());
        mActionsPageView.setText(current.getActionsPageView());
        mActionsDownloadView.setText(current.getActionsDownload());
        mActionsOutboundLinksView.setText(current.getActionsOutboundLinks());
        mActionsEvent.setText(current.getActionsEvent());
        mAvgActionsView.setText(current.getAvgActions());
        mAvgActionsPerVisitView.setText(current.getAvgTimePerVisit());
        mTotalTimeView.setText(current.getTotalTime());
        mBounceRateView.setText(current.getBounceRate() + '%');

    }

    private void getCurrent(String siteID, String siteKey) {
        String outputJSON = "&output=json";
        String types = "visitors,actions,actions-average,time-average-pretty,time-total-pretty,bounce-rate,visitors-online,visitors-new,visitors-unique,actions-pageviews,actions-downloads,actions-outbounds,actions-clicks";
        String clickyURL = "http://api.clicky.com/api/stats/4?site_id=" + siteID + "&sitekey=" + siteKey + "&type=" + types + outputJSON;
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(clickyURL)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            networkUnavailable();
                        }
                    });

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String jsonData = response.body().string();
                    try {
                        mClicky = getClickyData(jsonData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setDisplay();
                        }
                    });
                }

            });
        } else {
            networkUnavailable();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        View count = menu.findItem(R.id.action_user_online).getActionView();
        TextView displayCount = (TextView) count.findViewById(R.id.notif_count);
        displayCount.setText(mUserOnlineCount);
        return super.onCreateOptionsMenu(menu);
    }

    private void setNotificationCount(String count) {
        mUserOnlineCount = count;
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_donate) {
            Toast.makeText(this, "Donate", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);

    }


}


