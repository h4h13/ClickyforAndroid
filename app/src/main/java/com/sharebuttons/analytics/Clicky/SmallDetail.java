package com.sharebuttons.analytics.Clicky;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Monkey D Luffy on 6/5/2015.
 */
public class SmallDetail {
    private long mTime;
    private long mSessionId;
    private String mActionTitle;
    private String mActionUrl;
    private String mStatsUrl;
    private String mDate;
    private String mUsername;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public long getSessionId() {
        return mSessionId;
    }

    public void setSessionId(long sessionId) {
        mSessionId = sessionId;
    }

    public String getActionTitle() {
        return mActionTitle;
    }

    public void setActionTitle(String actionTitle) {
        mActionTitle = actionTitle;
    }

    public String getActionUrl() {
        return mActionUrl;
    }

    public void setActionUrl(String actionUrl) {
        mActionUrl = actionUrl;
    }

    public String getStatsUrl() {
        return mStatsUrl;
    }

    public void setStatsUrl(String statsUrl) {
        mStatsUrl = statsUrl;
    }

    public String getFormattedTime() {
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        format.setTimeZone(TimeZone.getDefault());
        Date date = new Date(getTime() * 1000);
        return format.format(date);
    }
}
