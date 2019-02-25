package com.example.android.popularmovies;

public class MovieData {
    private String mTitle;
    private String mThmbnai;
    private String mOverview;
    private double mVoteAverage;
    private String mdata;

    public MovieData(String mTitle, String mThmbnai, String mOverview, double mVoteAverage, String mdata) {
        this.mTitle = mTitle;
        this.mThmbnai = mThmbnai;
        this.mOverview = mOverview;
        this.mVoteAverage = mVoteAverage;
        this.mdata = mdata;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmThmbnai() {
        return mThmbnai;
    }

    public String getmOverview() {
        return mOverview;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public String getMdata() {
        return mdata;
    }
}
