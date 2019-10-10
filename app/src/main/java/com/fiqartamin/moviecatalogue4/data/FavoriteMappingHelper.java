package com.fiqartamin.moviecatalogue4.data;

import android.database.Cursor;

import com.fiqartamin.moviecatalogue4.Model.Favorite;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_BACKDROP_PATH;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_CATEGORY;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_MOVIEID;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_PLOT_SYNOPSIS;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_RELEASE;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_TITLE;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_USERRATING;

public class FavoriteMappingHelper {

    public static ArrayList<Favorite> mapCursorToArrayList (Cursor favoriteCursor) {
        ArrayList<Favorite> favoriteList = new ArrayList<>();

        while (favoriteCursor.moveToNext()) {
            int id = favoriteCursor.getInt(favoriteCursor.getColumnIndexOrThrow(_ID));
            String movieid = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_MOVIEID));
            String title = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String release = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_RELEASE));
            String userrating = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_USERRATING));
            String posterpath = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_POSTER_PATH));
            String backdroppath = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_BACKDROP_PATH));
            String overview = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_PLOT_SYNOPSIS));
            String category = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
        }

        return favoriteList;
    }
}
