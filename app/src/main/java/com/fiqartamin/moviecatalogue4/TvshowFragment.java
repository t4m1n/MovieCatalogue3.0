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

import com.fiqartamin.moviecatalogue4.Model.Tv;
import com.fiqartamin.moviecatalogue4.adapter.TvAdapter;
import com.fiqartamin.moviecatalogue4.viewmodels.TvViewModel;

import java.util.ArrayList;
import java.util.List;

public class TvshowFragment extends Fragment {

    private ArrayList<Tv> tvArrayList = new ArrayList<>();
    private TvAdapter tvAdapter;
    private RecyclerView recyclerView;
    private TvViewModel tvViewModel;
    private ProgressBar progressBar;

    View v;


    public TvshowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tvshow, container, false);

        progressBar = v.findViewById(R.id.progressBar);
        recyclerView = v.findViewById(R.id.tv_rv);

        showLoading(true);
        setupRecyclerView();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        tvViewModel.init();
        tvViewModel.getTvRepository().observe(this, tvResponse ->{
            List<Tv> tvs = tvResponse.getResults();
            tvArrayList.addAll(tvs);
            tvAdapter.notifyDataSetChanged();
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

    private void setupRecyclerView () {
        if (tvAdapter == null) {
            tvAdapter = new TvAdapter(tvArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(tvAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        }else {
            tvAdapter.notifyDataSetChanged();
        }
    }
}
