package com.fiqartamin.moviecatalogue4;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fiqartamin.moviecatalogue4.Model.Favorite;
import com.fiqartamin.moviecatalogue4.Model.Movie;
import com.fiqartamin.moviecatalogue4.adapter.FavoriteMovieAdapter;
import com.fiqartamin.moviecatalogue4.adapter.FavoriteMovieAdapter;
import com.fiqartamin.moviecatalogue4.adapter.MovieAdapter;
import com.fiqartamin.moviecatalogue4.data.FavoriteHelper;
import com.fiqartamin.moviecatalogue4.data.FavoriteMappingHelper;
import com.fiqartamin.moviecatalogue4.viewmodels.MovieViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_BACKDROP_PATH;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_CATEGORY;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_MOVIEID;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_PLOT_SYNOPSIS;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_RELEASE;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_TITLE;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_USERRATING;

public class FavoriteMovieFragment extends Fragment {
    private FavoriteMovieAdapter favoriteAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Favorite> listFavorites = new ArrayList<>();

    private FavoriteHelper favoriteHelper;

    View v;

    public FavoriteMovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        recyclerView = v.findViewById(R.id.favorite_movie_rv);
        favoriteHelper = FavoriteHelper.getInstance(getActivity());

        setupRecyclerView();
        getData();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        favoriteHelper = FavoriteHelper.getInstance(getActivity());

    }

    @SuppressLint("Favorite")
    protected void getData() {
        Cursor result = favoriteHelper.queryAll("1"); // 1 movie, 2 tv
        result.moveToFirst();

        if (result.getCount() > 0) {
            for (int count = 0; count < result.getCount(); count++) {
                result.moveToPosition(count);
                listFavorites.add(new Favorite(
                        result.getInt(result.getColumnIndexOrThrow(COLUMN_MOVIEID)),
                        result.getString(result.getColumnIndexOrThrow(COLUMN_TITLE)),
                        result.getString(result.getColumnIndexOrThrow(COLUMN_RELEASE)),
                        result.getString(result.getColumnIndexOrThrow(COLUMN_PLOT_SYNOPSIS)),
                        result.getString(result.getColumnIndexOrThrow(COLUMN_USERRATING)),
                        result.getString(result.getColumnIndexOrThrow(COLUMN_POSTER_PATH)),
                        result.getString(result.getColumnIndexOrThrow(COLUMN_BACKDROP_PATH)),
                        result.getString(result.getColumnIndexOrThrow(COLUMN_CATEGORY))
                ));
            }
        } else {
            Toast.makeText(getContext().getApplicationContext(), "Data Favorite Tidak Ada", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupRecyclerView() {
        if (favoriteAdapter == null) {
            favoriteAdapter = new FavoriteMovieAdapter(listFavorites);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(favoriteAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        }else{
            favoriteAdapter.notifyDataSetChanged();
        }
    }

}
