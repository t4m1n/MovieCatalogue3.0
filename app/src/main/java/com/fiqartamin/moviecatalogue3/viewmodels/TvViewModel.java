package com.fiqartamin.moviecatalogue3.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fiqartamin.moviecatalogue3.Model.TvResponse;
import com.fiqartamin.moviecatalogue3.R;
import com.fiqartamin.moviecatalogue3.api.TvRepository;

public class TvViewModel extends ViewModel {

    private static final String API_KEY = "0cbd025093b0e759240fe77ca65ccc99";
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
