package com.example.android.popularmovies.utilities;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by akashs on 30/7/17.
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    final static String PARAM_QUERY = "movie";

    final static String PARAM_API = "api_key";
    final static String apiKey = "07cfd52fa59964cfeed47d91018f450c";

    public static URL buildUrl(String MOVIE_BASE_URL) {
        // TODO (1) Fill in this method to build the proper Github query URL
        Uri buildUri = Uri.parse(MOVIE_BASE_URL).buildUpon().appendQueryParameter(PARAM_API, apiKey).build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<Movie> extractFeatureFromJson(String movieJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(movieJSON)) {
            return null;
        }
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(movieJSON);
            JSONArray results = baseJsonResponse.getJSONArray("results");

            Log.v(LOG_TAG, "" + results.length());

            for (int i = 0; i < results.length(); i++) {
                JSONObject f = results.getJSONObject(i);

                String posterPath = f.getString("poster_path");
                String basePath = "http://image.tmdb.org/t/p/w185";
                String poster = basePath + posterPath;
                String title = f.getString("title");
                String releaseDate = f.getString("release_date");
                String voteAverage = f.getString("vote_average");
                String overview = f.getString("overview");
                movies.add(new Movie(title, releaseDate, poster, voteAverage, overview));
            }
            return movies;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the book JSON results", e);
        }
        return null;
    }

}
