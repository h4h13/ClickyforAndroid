package com.sharebuttons.analytics.activities;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sharebuttons.analytics.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @Bind(R.id.versionTextView)
    TextView mVersionTextView;
    @Bind(R.id.clickyLabel)
    TextView mClickyLabel;
    @Bind(R.id.copyRights)
    TextView mCompany;
    Typeface mTypeface;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setTypeFace();

        PackageInfo pInfo;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            mVersionTextView.setText("Version " + pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Toast.makeText(getApplicationContext(), mSharedPreferences.getString("JSONDATA", null), Toast.LENGTH_LONG).show();
    }

    private void setTypeFace() {
        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        mVersionTextView.setTypeface(mTypeface);
        mClickyLabel.setTypeface(mTypeface);
        mCompany.setTypeface(mTypeface);
    }
}

