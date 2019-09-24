package com.fiqartamin.moviecatalogue3.api;

import android.arch.lifecycle.MutableLiveData;
import com.fiqartamin.moviecatalogue3.Model.TvResponse;
import com.fiqartamin.moviecatalogue3.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvRepository {
    private static TvRepository tvRepository;

    public static TvRepository getInstance(){
        if (tvRepository == null) {
            tvRepository = new TvRepository();
        }
        return tvRepository;
    }

    private MovieApi movieApi;

    public TvRepository() {
        movieApi = RetrofitService.cService(MovieApi.class);
    }

    public MutableLiveData<TvResponse> getTv (String key, int language_id) {
        final MutableLiveData<TvResponse> tvData = new MutableLiveData<>();
        movieApi.getJSONTv(key, language_id).enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                if (response.isSuccessful()) {
                    tvData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {

            }
        });
        return tvData;
    }
}
