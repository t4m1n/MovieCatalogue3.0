package com.fiqartamin.moviecatalogue4.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fiqartamin.moviecatalogue4.BuildConfig;
import com.fiqartamin.moviecatalogue4.Model.TvResponse;
import com.fiqartamin.moviecatalogue4.R;
import com.fiqartamin.moviecatalogue4.api.TvRepository;

public class TvViewModel extends ViewModel {

    private static final String API_KEY = BuildConfig.TMDB_API_KEY; // diambil dari buil.gradle
    private static final int LANGUAGE = R.string.language_id;

    private MutableLiveData<TvResponse> mutableLiveData;
    private TvRepository tvRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }

        tvRepository = TvRepository.getInstance();
        mutableLiveData = tvRepository.getTv(API_KEY, LANGUAGE);

    }

    public LiveData<TvResponse> getTvRepository () {
        return mutableLiveData;
    }
}
