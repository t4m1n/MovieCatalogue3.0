package com.fiqartamin.moviecatalogue4;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.fiqartamin.moviecatalogue4.Model.Movie;
import com.fiqartamin.moviecatalogue4.adapter.MovieAdapter;
import com.fiqartamin.moviecatalogue4.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private MovieViewModel movieViewModel;
    private ProgressBar progressBar;

    View v;


    public MovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_movie, container, false);

        progressBar = v.findViewById(R.id.progressBar);
        recyclerView = v.findViewById(R.id.movie_rv);

        showLoading(true);
        setupRecyclerView();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.init();
        movieViewModel.getMovieRepository().observe(this, movieResponse ->{
            List<Movie> movies = movieResponse.getResults();
            movieArrayList.addAll(movies);
            movieAdapter.notifyDataSetChanged();
            showLoading(false);
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setupRecyclerView() {
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(movieArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(movieAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        }else{
            movieAdapter.notifyDataSetChanged();
        }
    }
}
