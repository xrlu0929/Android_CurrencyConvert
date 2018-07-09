package com.example.annaxlu.currencyconvert;

import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;

public class RetroFixData {
    /**
     * parse data from API
     */
    @SerializedName("success")
    private boolean success;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("base")
    private String base;

    @SerializedName("date")
    private String date;

    @SerializedName("rates")
    private Object rate;

    public RetroFixData(boolean success, String timestamp, String base, String date,  Object rate) {
        this.success=success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rate = rate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * getter; no setter yet
     * @return
     */
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public  Object getRate() {

        return rate;
    }

    public void setRate( Object rate) {
        this.rate = rate;
    }
}
