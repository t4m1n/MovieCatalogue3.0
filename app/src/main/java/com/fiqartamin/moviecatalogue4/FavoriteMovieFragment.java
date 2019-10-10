package com.fiqartamin.moviecatalogue4;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.fiqartamin.moviecatalogue4.Model.Favorite;
import com.fiqartamin.moviecatalogue4.Model.Movie;
import com.fiqartamin.moviecatalogue4.adapter.FavoriteAdapter;
import com.fiqartamin.moviecatalogue4.adapter.MovieAdapter;
import com.fiqartamin.moviecatalogue4.data.FavoriteHelper;
import com.fiqartamin.moviecatalogue4.data.FavoriteMappingHelper;
import com.fiqartamin.moviecatalogue4.viewmodels.MovieViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_PLOT_SYNOPSIS;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_RELEASE;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_TITLE;

public class FavoriteMovieFragment extends Fragment {
    private FavoriteAdapter favoriteAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Favorite> listFavorites = new ArrayList<>();

    private MovieViewModel movieViewModel;
    private ProgressBar progressBar;
    private FavoriteHelper favoriteHelper;

    private static final String EXTRA_STATE = "EXTRA_STATE";

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

        for (int count = 0; count < result.getCount(); count++) {
            result.moveToPosition(count);
            listFavorites.add(new Favorite(
                    result.getString(result.getColumnIndexOrThrow(COLUMN_TITLE)),
                    result.getString(result.getColumnIndexOrThrow(COLUMN_PLOT_SYNOPSIS)),
                    result.getString(result.getColumnIndexOrThrow(COLUMN_RELEASE)),
                    result.getString(result.getColumnIndexOrThrow(COLUMN_POSTER_PATH))
            ));
        }

    }

    private void setupRecyclerView() {
        if (favoriteAdapter == null) {
            favoriteAdapter = new FavoriteAdapter(listFavorites);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(favoriteAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        }else{
            favoriteAdapter.notifyDataSetChanged();
        }
    }



}
