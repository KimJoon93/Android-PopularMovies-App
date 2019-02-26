package com.example.android.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler{
    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    private MovieAdapter movieAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int GRID_SPAN_COUNT = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,GRID_SPAN_COUNT);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        List<MovieData> movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(movies, this);
        recyclerView.setAdapter(movieAdapter);
        loadMovieData();

    }





    @Override
    public void onItemClick(MovieData movie) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("MovieTitle",movie.getmTitle());
        intent.putExtra("MovieDate",movie.getMdata());
        intent.putExtra("Movievote",movie.getmVoteAverage());
        intent.putExtra("MovieOverview",movie.getmOverview());
        intent.putExtra("MovieThumbnail",movie.getmThmbnai());
        startActivity(intent);
    }

    private void loadMovieData() {
        String sort = MoviePreference.getPreferredSortCriteria(this);
        new FetchMovieTask().execute(sort);
    }


    public class FetchMovieTask extends AsyncTask<String, Void, List<MovieData>> {
        @Override
        protected List<MovieData> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            String url = params[0];
            URL movieRequestUrl = NetworkUtils.buildMovieUrl(url);

            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);

                List<MovieData> jsonMovieData = JsonUtils.parseMovieJson(jsonMovieResponse);

                return jsonMovieData;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG, "Problems in JSON results.");
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<MovieData> movies) {
            movieAdapter.clearAll();

            if (movies != null && !movies.isEmpty()) {
                movieAdapter.addAll(movies);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent optionIntent = new Intent(this, SettingActivity.class);
            startActivity(optionIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
