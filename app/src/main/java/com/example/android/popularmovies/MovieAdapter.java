package com.example.android.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieData> mMovies;

    public MovieAdapter(List<MovieData> movies) {
        mMovies = movies;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list_layout, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        MovieData movie = mMovies.get(i);
       // String title = movie.getmTitle();
        String thumbnail = movie.getmThmbnai();
        //double voteAvg = movie.getmVoteAverage();
  //      movieViewHolder.titleTextView.setText(title);
        Picasso.with(movieViewHolder.itemView.getContext())
                .load(thumbnail)
                .into(movieViewHolder.thumbnailImageView);
//        movieViewHolder.voteAvgTextView.setText(String.valueOf(voteAvg));
    }

    @Override
    public int getItemCount() {
        if (null == mMovies) return 0;
        return mMovies.size();
    }

    public void setMovies(List<MovieData> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public void clearAll() {
        mMovies.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<MovieData> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thubnail_iv) ImageView thumbnailImageView;



        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
