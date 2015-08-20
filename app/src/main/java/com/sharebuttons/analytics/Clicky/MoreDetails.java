package com.sharebuttons.analytics.Clicky;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Monkey D Luffy on 5/3/2015.
 */
public class MoreDetails implements Parcelable {
    public static final Creator<MoreDetails> CREATOR = new Creator<MoreDetails>() {
        @Override
        public MoreDetails createFromParcel(Parcel parcel) {
            return new MoreDetails(parcel);
        }

        @Override
        public MoreDetails[] newArray(int i) {
            return new MoreDetails[i];
        }
    };
    private String mValue;
    private String mValuePercent;
    private String mTitle;
    private String mStatsUrl;
    private String mItem;
    private String mDate;

    public MoreDetails() {
    }

    public MoreDetails(Parcel in) {
        mValue = in.readString();
        mValuePercent = in.readString();
        mTitle = in.readString();
        mStatsUrl = in.readString();
    }

    public String getItem() {
        return mItem;
    }

    public void setItem(String item) {
        mItem = item;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValuePercent() {
        return mValuePercent;
    }

    public void setValuePercent(String valuePercent) {
        mValuePercent = valuePercent;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getStatsUrl() {
        return mStatsUrl;
    }

    public void setStatsUrl(String statsUrl) {
        mStatsUrl = statsUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mValue);
        parcel.writeString(mValuePercent);
        parcel.writeString(mTitle);
        parcel.writeString(mStatsUrl);

    }
}
