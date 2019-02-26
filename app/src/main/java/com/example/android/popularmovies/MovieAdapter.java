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

    private final MovieAdapterOnClickHandler mOnClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onItemClick(MovieData movie);
    }

    public MovieAdapter(List<MovieData> movies, MovieAdapterOnClickHandler onClickHandler) {
        mMovies = movies;
        mOnClickHandler = onClickHandler;

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
        String thumbnail = movie.getmThmbnai();
        Picasso.with(movieViewHolder.itemView.getContext())
                .load(thumbnail)
                .into(movieViewHolder.thumbnailImageView);
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

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.thubnail_iv) ImageView thumbnailImageView;



        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            MovieData movie = mMovies.get(adapterPosition);
            mOnClickHandler.onItemClick(movie);
        }
    }

}
