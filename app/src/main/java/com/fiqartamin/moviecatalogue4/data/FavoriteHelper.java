package com.fiqartamin.moviecatalogue4.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_CATEGORY;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_MOVIEID;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.TABLE_NAME;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static FavoriteDbHelper favoriteDbHelper;
    private static FavoriteHelper INSTANCE;

    private static SQLiteDatabase database;

    private FavoriteHelper(Context context) {
        favoriteDbHelper = new FavoriteDbHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }


    public void open() throws SQLException {
        database = favoriteDbHelper.getReadableDatabase();
    }

    public void close() {
        favoriteDbHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll(String category) {
        return database.query(
                DATABASE_TABLE,
               null,
               COLUMN_CATEGORY + " = ?",
               new String[]{category},
               null,
               null,
               _ID + " ASC");
    }

    public Cursor queryById (String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                COLUMN_MOVIEID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    public long insert (ContentValues values) {
        return database.insert(
                DATABASE_TABLE,
                null,
                values);
    }

    public int update(String id, ContentValues values) {
        return database.update(
                DATABASE_TABLE,
                values, _ID + " = ?",
                new String[]{id});
    }

    public int deleteById (String id) {
        return database.delete(
                DATABASE_TABLE,
                COLUMN_MOVIEID + " = ?",
                new String[]{id});
    }
}
