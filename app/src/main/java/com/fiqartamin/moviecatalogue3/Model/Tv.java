package com.fiqartamin.moviecatalogue3.Model;

import com.google.gson.annotations.SerializedName;

public class Tv {
    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String release;

    @SerializedName("poster_path")
    private String photo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
