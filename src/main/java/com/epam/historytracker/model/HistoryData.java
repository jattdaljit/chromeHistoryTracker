package com.epam.historytracker.model;

import java.util.Date;

public class HistoryData implements Comparable<HistoryData> {
    String url;
    Date lastVisitTime;
    String duration;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public int compareTo(HistoryData data) {
        return data.getLastVisitTime().compareTo(this.getLastVisitTime());
    }
}