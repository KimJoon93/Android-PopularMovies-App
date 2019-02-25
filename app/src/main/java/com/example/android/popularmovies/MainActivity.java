package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    private MovieAdapter movieAdapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int GRID_SPAN_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,GRID_SPAN_COUNT);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        List<MovieData> movies = new ArrayList<>();

        movieAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(movieAdapter);

    }

}
