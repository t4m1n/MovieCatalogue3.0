package com.fiqartamin.moviecatalogue4.data;

import android.provider.BaseColumns;

public class FavoriteContract {
    public static final class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_MOVIEID = "movieid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE = "release";
        public static final String COLUMN_USERRATING = "userrating";
        public static final String COLUMN_POSTER_PATH = "posterpath";
        public static final String COLUMN_BACKDROP_PATH = "backdroppath";
        public static final String COLUMN_PLOT_SYNOPSIS = "overview";
        public static final String COLUMN_CATEGORY = "category";
    }
}
