package com.example.android.popularmovies;

/**
 * Created by akashs on 1/8/17.
 */

public class Movie {

    private String mTitle;

    private String mReleaseDate;

    private String mPoster;

    private String mVoteAverage;

    private String mPlotSynopsis;

    public Movie(String title, String releaseDate, String poster, String voteAverage, String plotSynopsis) {
        mTitle = title;
        mReleaseDate = releaseDate;
        mPoster = poster;
        mVoteAverage = voteAverage;
        mPlotSynopsis = plotSynopsis;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPoster() {
        return mPoster;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

}
