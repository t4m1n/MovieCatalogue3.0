package com.fiqartamin.moviecatalogue4.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {
    @SerializedName("total_results")
    @Expose
    private Integer totalResult;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName("results")
    @Expose
    private List<Tv> results = null;

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Tv> getResults() {
        return results;
    }

    public void setResults(List<Tv> results) {
        this.results = results;
    }
}
