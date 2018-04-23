package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String releaseDate = intent.getStringExtra("releaseDate");
        String voteAverage = intent.getStringExtra("voteAverage");
        String overView = intent.getStringExtra("overView");
        String poster = intent.getStringExtra("poster");

        setTitle(title);

        ImageView posterImageView = (ImageView) findViewById(R.id.poster);

        TextView titleTextView = (TextView) findViewById(R.id.title);

        TextView dateTextView = (TextView) findViewById(R.id.date_released);

        TextView overviewTextView = (TextView) findViewById(R.id.overview);

        TextView ratingTextView = (TextView) findViewById(R.id.vote_average);

        Picasso.with(this)
                .load(poster)
                .into(posterImageView);

        titleTextView.setText(title);

        dateTextView.setText(releaseDate);

        overviewTextView.setText(overView);

        ratingTextView.setText(voteAverage);

    }
}
