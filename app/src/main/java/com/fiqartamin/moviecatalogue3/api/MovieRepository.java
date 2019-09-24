package com.fiqartamin.moviecatalogue3.api;

import android.arch.lifecycle.MutableLiveData;

import com.fiqartamin.moviecatalogue3.Model.MovieResponse;
import com.fiqartamin.moviecatalogue3.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;

    public static MovieRepository getInstance(){
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    private MovieApi movieApi;

    public MovieRepository() {
        movieApi = RetrofitService.cService(MovieApi.class);
    }

    public MutableLiveData<MovieResponse> getMovie(String key, int language_id) {
        final MutableLiveData<MovieResponse> movieData = new MutableLiveData<>();
        movieApi.getJSONMovie(key, language_id).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    movieData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return movieData;
    }

}
