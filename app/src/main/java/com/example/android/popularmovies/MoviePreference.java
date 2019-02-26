package com.example.android.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MoviePreference {

    public static String getPreferredSortCriteria(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String keyForSortBy = context.getString(R.string.pref_sort_by_key);

        String defaultSortBy = context.getString(R.string.pref_sort_by_default);

        return prefs.getString(keyForSortBy, defaultSortBy);
    }
}
