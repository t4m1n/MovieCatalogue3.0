package com.fiqartamin.moviecatalogue3.api;

import com.fiqartamin.moviecatalogue3.Model.DetResponse;
import com.fiqartamin.moviecatalogue3.Model.MovieResponse;
import com.fiqartamin.moviecatalogue3.Model.TvResponse;

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

    @GET ("movie")
    Call<DetResponse> getJSONMovie_det(
            @Query("api_key") String apiKey,
            @Query("language") int language
    );

    @GET ("tv")
    Call<DetResponse> getJSONTv_det (
            @Query("api_key") String apiKey,
            @Query("language") int language
    );
}