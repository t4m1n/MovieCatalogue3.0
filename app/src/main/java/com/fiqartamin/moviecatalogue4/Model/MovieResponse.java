package com.fiqartamin.moviecatalogue4.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("total_results")
    @Expose
    private Integer totalResult;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName("results")
    @Expose
    private List<Movie> results = null;

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

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
