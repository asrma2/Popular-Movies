package com.example.android.popularmovies;

//https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=07cfd52fa59964cfeed47d91018f450c

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter mAdapter;

    private GridView gridView;

    private String[] mUrls = new String[30000];

    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new fetchMovieTask().execute("https://api.themoviedb.org/3/movie/popular");

        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

                String title = movies.get(position).getTitle();

                String releaseDate = movies.get(position).getReleaseDate();

                String voteAverage = movies.get(position).getVoteAverage();

                String overView = movies.get(position).getPlotSynopsis();

                String poster = movies.get(position).getPoster();

                intent.putExtra("Title", title);
                intent.putExtra("releaseDate", releaseDate);
                intent.putExtra("voteAverage", voteAverage);
                intent.putExtra("overView", overView);
                intent.putExtra("poster", poster);

                // Launch the {@link EditorActivity} to display the data for the current pet.
                startActivity(intent);
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.popular) {
            new fetchMovieTask().execute("https://api.themoviedb.org/3/movie/popular");
            return true;
        } else if(item.getItemId() == R.id.rated) {
            new fetchMovieTask().execute("https://api.themoviedb.org/3/movie/top_rated");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public class fetchMovieTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(url);
            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);
                return jsonWeatherResponse;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            movies = NetworkUtils.extractFeatureFromJson(s);
            for(int i = 0; i < movies.size(); i++) {
                mUrls[i] = movies.get(i).getPoster();
            }
            mAdapter = new MovieAdapter(MainActivity.this, mUrls);

            // Get a reference to the ListView, and attach this adapter to it.
            gridView = (GridView) findViewById(R.id.movies_grid);
            gridView.setAdapter(mAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // Create new intent to go to {@link EditorActivity}
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

                    String title = movies.get(position).getTitle();

                    String releaseDate = movies.get(position).getReleaseDate();

                    String voteAverage = movies.get(position).getVoteAverage();

                    String overView = movies.get(position).getPlotSynopsis();

                    String poster = movies.get(position).getPoster();

                    intent.putExtra("Title", title);
                    intent.putExtra("releaseDate", releaseDate);
                    intent.putExtra("voteAverage", voteAverage);
                    intent.putExtra("overView", overView);
                    intent.putExtra("poster", poster);

                    // Launch the {@link EditorActivity} to display the data for the current pet.
                    startActivity(intent);
                }
            });
        }

    }

}
