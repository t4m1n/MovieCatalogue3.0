package com.fiqartamin.moviecatalogue3.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fiqartamin.moviecatalogue3.BuildConfig;
import com.fiqartamin.moviecatalogue3.Model.MovieResponse;
import com.fiqartamin.moviecatalogue3.R;
import com.fiqartamin.moviecatalogue3.api.MovieRepository;

public class MovieViewModel extends ViewModel {

    private static final String API_KEY = BuildConfig.TMDB_API_KEY; // diambil dari buil.gradle
    private static final int LANGUAGE = R.string.language_id;

    private MutableLiveData<MovieResponse> mutableLiveData;
    private MovieRepository movieRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }

        movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getMovie(API_KEY, LANGUAGE);

    }

    public LiveData<MovieResponse> getMovieRepository () {
        return mutableLiveData;
    }
}
