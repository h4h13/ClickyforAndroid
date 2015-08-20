package com.sharebuttons.analytics.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sharebuttons.analytics.Clicky.Clicky;
import com.sharebuttons.analytics.Clicky.MoreDetails;
import com.sharebuttons.analytics.R;
import com.sharebuttons.analytics.adapter.MoreDetailsAdapter;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SharedPreferences mSharedPreferences;
    Bundle bundle;
    private ListView mListView;
    private TextView mTextView;
    private Clicky mClicky;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public TypeListFragment() {
        // Required empty public constructor
    }

    public static TypeListFragment getInstance(String type) {
        TypeListFragment mTypeListFragment = new TypeListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        mTypeListFragment.setArguments(bundle);
        return mTypeListFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        bundle = getArguments();
        View layout = inflater.inflate(R.layout.fragment_list, container, false);

        updateDisplay(layout);
        if (bundle != null) {

            //getCurrent("32020", "9a19b1a4d1171193");
            getCurrent(mSharedPreferences.getString("SITE_ID_STRING", null), mSharedPreferences.getString("SITE_KEY_STRING", null));
        }

        setHasOptionsMenu(true);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.accent, R.color.primary, R.color.primary_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        return layout;

    }


    private void getCurrent(String siteID, String siteKey) {
        String outputJSON = "&output=json";
        String types = bundle.getString("type");
        String clickyURL = "http://api.clicky.com/api/stats/4?site_id=" + siteID + "&sitekey=" + siteKey + "&type=" + types + outputJSON + "&limit=all";

        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(clickyURL)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                networkUnavailable();
                            }
                        });
                    }
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String jsonData = response.body().string();
                    try {
                        mClicky = getClickyData(jsonData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setDisplay();
                                toggleRefresh();
                            }

                        });
                    }
                }
            });
        } else {
            networkUnavailable();
        }
    }

    private void networkUnavailable() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toggleRefresh();
                }
            });

        }

    }


    private void toggleRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    private MoreDetails[] getCurrentMoreDetails(String jsonData) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonData);
        MoreDetails[] mDetails = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray datesArray = jsonObject.getJSONArray("dates");
            for (int j = 0; j < datesArray.length(); j++) {
                JSONObject dates = datesArray.getJSONObject(j);
                JSONArray itemsArray = dates.getJSONArray("items");
                mDetails = new MoreDetails[itemsArray.length()];
                for (int k = 0; k < itemsArray.length(); k++) {
                    JSONObject items = itemsArray.getJSONObject(k);
                    MoreDetails details = new MoreDetails();
                    details.setValue(items.optString("value"));
                    details.setValuePercent(items.optString("value_percent"));
                    details.setTitle(items.optString("title"));
                    details.setStatsUrl(items.optString("stats_url"));
                    mDetails[k] = details;
                }
            }

        }
        return mDetails;
    }

    private void setDisplay() {
        final MoreDetails[] moreDetails = mClicky.getDetails();
        MoreDetailsAdapter adapter = new MoreDetailsAdapter(getActivity(), moreDetails);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(mTextView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), moreDetails[position].getStatsUrl(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(moreDetails[position].getStatsUrl())));

            }
        });

    }

    private Clicky getClickyData(String jsonData) throws JSONException {
        Clicky clicky = new Clicky();
        clicky.setDetails(getCurrentMoreDetails(jsonData));
        return clicky;
    }

    private void updateDisplay(View view) {
        mListView = (ListView) view.findViewById(android.R.id.list);
        mTextView = (TextView) view.findViewById(android.R.id.empty);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_user_online);
        menuItem.setVisible(false);
    }


    @Override
    public void onRefresh() {
        getCurrent(mSharedPreferences.getString("SITE_ID_STRING", null), mSharedPreferences.getString("SITE_KEY_STRING", null));
    }
}
