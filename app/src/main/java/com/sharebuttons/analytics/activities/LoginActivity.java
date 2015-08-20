package com.sharebuttons.analytics.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sharebuttons.analytics.Clicky.Clicky;
import com.sharebuttons.analytics.Clicky.Websites;
import com.sharebuttons.analytics.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id._username)
    EditText mUsername;
    @Bind(R.id._password)
    EditText mPassword;
    @Bind(R.id._username_layout)
    TextInputLayout mUserNameLayout;
    @Bind(R.id._password_layout)
    TextInputLayout mPasswordLayout;
    @Bind(R.id.content_frame)
    RelativeLayout mCoordinatorLayout;
    private SharedPreferences mSharedPreferences;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }


    @OnClick(R.id._login_btn)
    public void login() {
        if (mUsername.getText().toString().trim().length() > 0) {
            mUserNameLayout.setError(null);
            if (mPassword.getText().toString().trim().length() > 0) {
                mPasswordLayout.setError(null);
                String Name = mUsername.getText().toString();
                String Pass = mPassword.getText().toString();

                getWebsites(Name, Pass);

                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("USERNAME", Name);
                editor.putString("PASSWORD", Pass);
                editor.apply();
            } else {
                mUserNameLayout.setError(null);
                mPasswordLayout.setError("Enter Password!");
            }
        } else {
            mUserNameLayout.setError("Enter Username!");
            mPasswordLayout.setError(null);
        }
    }

    private Clicky mClicky;

    private void getWebsites(String username, String password) {
        String clickyLoginUrl = "http://api.clicky.com/api/account/sites?username=" +
                username + "&password=" + password + "&output=json";
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(clickyLoginUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ErrorCaught();
                        }
                    });

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String jsonData = response.body().string();
                    try {
                        mClicky = getWebSitesData(jsonData);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateDisplay();
                            }
                        });
                    } catch (JSONException e) {
                        Log.e("Login Error", e + "");
                    }
                }
            });
        } else {
            networkUnavailable();
        }
    }

    private void networkUnavailable() {
        Snackbar.make(mCoordinatorLayout, R.string.network_unavailable, Snackbar.LENGTH_LONG).show();
    }

    private void ErrorCaught() {
        Snackbar.make(mCoordinatorLayout, R.string.error_text_message, Snackbar.LENGTH_LONG).show();
    }

    private void updateDisplay() {

        Websites[] websites = mClicky.getWebsites();

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for (int i = 0; i < websites.length; i++) {
            editor.putString("ID" + i, websites[i].getSiteId());
            editor.putString("KEY" + i, websites[i].getSiteKey());
            editor.putString("NICKNAME" + i, websites[i].getNickname());
            editor.putInt("LIST", websites.length);
        }
        editor.apply();

        Intent locale = new Intent(this, MainActivity.class);
        locale.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        locale.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(locale);


    }

    private Clicky getWebSitesData(String jsonData) throws JSONException {
        Clicky clicky = new Clicky();
        clicky.setWebsites(getWebsitesList(jsonData));
        return clicky;
    }

    private Websites[] getWebsitesList(String jsonData) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonData);
        Websites[] websites = new Websites[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {

            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.has("error")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), jsonObject.optString("error"), Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Websites website = new Websites();
                website.setNickname(jsonObject.getString("nickname"));
                website.setSiteId(jsonObject.getString("site_id"));
                website.setSiteKey(jsonObject.getString("sitekey"));
                website.setHostname(jsonObject.getString("hostname"));
                websites[i] = website;
            }
        }
        return websites;
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

}
