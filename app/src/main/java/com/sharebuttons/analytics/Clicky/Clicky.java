package com.sharebuttons.analytics.Clicky;

/**
 * Created by Monkey D Luffy on 5/2/2015.
 */
public class Clicky {
    private Current mCurrent;
    private MoreDetails[] mDetails;
    private Websites[] mWebsites;
    private BigMoreDetails[] mBigMoreDetails;
    private SmallDetail[] mSmallDetails;
    private Hourly[] mHourly;

    public Hourly[] getmHourly() {
        return mHourly;
    }

    public void setmHourly(Hourly[] mHourly) {
        this.mHourly = mHourly;
    }

    public SmallDetail[] getSmallDetails() {
        return mSmallDetails;
    }

    public void setSmallDetails(SmallDetail[] smallDetails) {
        mSmallDetails = smallDetails;
    }

    public BigMoreDetails[] getBigMoreDetails() {
        return mBigMoreDetails;
    }

    public void setBigMoreDetails(BigMoreDetails[] bigMoreDetails) {
        mBigMoreDetails = bigMoreDetails;
    }

    public Websites[] getWebsites() {
        return mWebsites;
    }

    public void setWebsites(Websites[] websites) {
        mWebsites = websites;
    }

    public Current getCurrent() {
        return mCurrent;
    }

    public void setCurrent(Current current) {
        mCurrent = current;
    }

    public MoreDetails[] getDetails() {
        return mDetails;
    }

    public void setDetails(MoreDetails[] details) {
        mDetails = details;
    }
}
