package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_title) TextView detailtitle;
    @BindView(R.id.detail_date) TextView detaildate;
    @BindView(R.id.detail_vote) TextView detailvote;
    @BindView(R.id.detail_summary) TextView detailsummary;
    @BindView(R.id.detail_movieimage) ImageView movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        detailtitle.setText(intent.getStringExtra("MovieTitle"));
        detaildate.setText(intent.getStringExtra("MovieDate"));

        Double movievote = (intent.getDoubleExtra("Movievote", 0));
        detailvote.setText(movievote.toString());

        detailsummary.setText(intent.getStringExtra("MovieOverview"));

        String thumbnail = intent.getStringExtra("MovieThumbnail");
        Picasso.with(this).load(thumbnail).into(movieImage);
    }
}
