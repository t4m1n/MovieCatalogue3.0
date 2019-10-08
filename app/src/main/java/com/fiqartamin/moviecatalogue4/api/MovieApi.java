package com.fiqartamin.moviecatalogue4.api;

import com.fiqartamin.moviecatalogue4.Model.MovieResponse;
import com.fiqartamin.moviecatalogue4.Model.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET ("discover/movie")
    Call<MovieResponse> getJSONMovie(
            @Query("api_key") String apiKey,
            @Query("language") int language
    );

    @GET ("discover/tv")
    Call<TvResponse> getJSONTv(
            @Query("api_key") String apiKey,
            @Query("language") int language
    );
}