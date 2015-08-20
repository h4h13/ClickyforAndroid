package com.sharebuttons.analytics.Clicky;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Monkey D Luffy on 5/24/2015.
 */
public class BigMoreDetails {
    private String mOrganization;
    private String mStats_URL;
    private String mUId;
    private long mTime;
    private long mTimeTotal;
    private String mActions;

    public String getActions() {
        return mActions;
    }

    public void setActions(String actions) {
        mActions = actions;
    }

    public long getTimeTotal() {
        return mTimeTotal;
    }

    public void setTimeTotal(long timeTotal) {
        mTimeTotal = timeTotal;
    }

    public String getUId() {
        return mUId;
    }

    public void setUId(String UId) {
        mUId = UId;
    }

    public String getStats_URL() {
        return mStats_URL;
    }

    public void setStats_URL(String stats_URL) {
        mStats_URL = stats_URL;
    }

    public String getOrganization() {
        return mOrganization;
    }

    public void setOrganization(String organization) {
        mOrganization = organization;
    }


    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getYerMonth() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getDefault());
        Date date = new Date(getTime() * 1000);
        return format.format(date);
    }

    public String getFormattedTime() {
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        format.setTimeZone(TimeZone.getDefault());
        Date date = new Date(getTime() * 1000);
        return format.format(date);
    }

    public String setMins() {
        long time = mTimeTotal;
        String mins = String.valueOf(time / 60);
        String secs = String.valueOf(time % 60);
        if ((time / 60) == 0) {
            return secs + "s";
        } else if ((time / 60) == 0) {
            return mins + "m";
        } else {
            return mins + "m " + secs + "s";
        }
    }


}
