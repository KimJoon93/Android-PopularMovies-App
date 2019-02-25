package com.example.android.popularmovies;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    private static final String KEY_RESULTS = "results";

    private static final String KEY_ORIGINAL_TITLE = "original_title";

    private static final String KEY_POSTER_PATH = "poster_path";

    private static final String KEY_OVERVIEW = "overview";

    private static final String KEY_VOTE_AVERAGE = "vote_average";

    private static final String KEY_RELEASE_DATE = "release_date";

    private static final String KEY_STATUS_CODE = "status_code";

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";

    private static final String IMAGE_FILE_SIZE = "w185";

    public static List<MovieData> parseMovieJson(String movieJsonStr) throws JSONException {

        if (TextUtils.isEmpty(movieJsonStr)) {
            return null;
        }

        List<MovieData> movies = new ArrayList<>();

        JSONObject movieBaseJson = new JSONObject(movieJsonStr);

        if (movieBaseJson.has(KEY_STATUS_CODE)) {
            int errorCode = movieBaseJson.getInt(KEY_STATUS_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:

                    return null;
                default:
                    return null;
            }
        }


        JSONArray resultsArray = movieBaseJson.getJSONArray(KEY_RESULTS);
        for (int i = 0; i < resultsArray.length(); i ++) {

            JSONObject currentMovie = resultsArray.getJSONObject(i);

            String posterPath = null;
            if (currentMovie.has(KEY_POSTER_PATH)) {

                posterPath = currentMovie.getString(KEY_POSTER_PATH);
            }

            String thumbnailUrl = IMAGE_BASE_URL + IMAGE_FILE_SIZE + posterPath;

            /** Title*/
            String originalTitle = null;
            if (currentMovie.has(KEY_ORIGINAL_TITLE)) {

                originalTitle = currentMovie.getString(KEY_ORIGINAL_TITLE);
            }

            /** Overview*/
            String overView = null;
            if (currentMovie.has(KEY_OVERVIEW)) {
                overView = currentMovie.getString(KEY_OVERVIEW);
            }
            /** VoteAverage*/
            double voteAverage = 0;
            if (currentMovie.has(KEY_VOTE_AVERAGE)) {
                voteAverage = currentMovie.getDouble(KEY_VOTE_AVERAGE);
            }
            /** Release Date*/
            String releaseDate = null;
            if (currentMovie.has(KEY_RELEASE_DATE)) {
                releaseDate = currentMovie.getString(KEY_RELEASE_DATE);
            }

            MovieData movie = new MovieData(originalTitle, thumbnailUrl, overView, voteAverage, releaseDate);
            movies.add(movie);
        }

        return movies;
    }

}
