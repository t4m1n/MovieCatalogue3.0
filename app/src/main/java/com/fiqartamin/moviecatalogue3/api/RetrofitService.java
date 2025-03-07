package com.fiqartamin.moviecatalogue3.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S cService (Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
