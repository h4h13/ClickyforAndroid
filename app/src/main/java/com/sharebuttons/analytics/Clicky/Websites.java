package com.sharebuttons.analytics.Clicky;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Monkey D Luffy on 5/2/2015.
 */
public class Websites implements Parcelable {
    public static final Creator<Websites> CREATOR = new Creator<Websites>() {
        @Override
        public Websites createFromParcel(Parcel parcel) {
            return new Websites(parcel);
        }

        @Override
        public Websites[] newArray(int i) {
            return new Websites[i];
        }
    };
    private String mSiteId;
    private String mSiteKey;
    private String mHostname;
    private String mNickname;

    public Websites() {
    }

    private Websites(Parcel in) {
        mSiteId = in.readString();
        mSiteKey = in.readString();
        mHostname = in.readString();
        mNickname = in.readString();
    }

    public String getSiteId() {
        return mSiteId;
    }

    public void setSiteId(String siteId) {
        mSiteId = siteId;
    }

    public String getSiteKey() {
        return mSiteKey;
    }

    public void setSiteKey(String siteKey) {
        mSiteKey = siteKey;
    }

    public String getHostname() {
        return mHostname;
    }

    public void setHostname(String hostname) {
        mHostname = hostname;
    }

    public String getNickname() {
        return mNickname;
    }

    public void setNickname(String nickname) {
        mNickname = nickname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mSiteId);
        parcel.writeString(mSiteKey);
        parcel.writeString(mHostname);
        parcel.writeString(mNickname);
    }
}
