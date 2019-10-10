package com.fiqartamin.moviecatalogue4.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {
    private int Id;
    private int MovieId;
    private String Title;
    private String Release;
    private String UserRating;
    private String PosterPath;
    private String BackdropPath;
    private String Category;
    private String Overview;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int movieId) {
        MovieId = movieId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRelease() {
        return Release;
    }

    public void setRelease(String release) {
        Release = release;
    }

    public String getUserRating() {
        return UserRating;
    }

    public void setUserRating(String userRating) {
        UserRating = userRating;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getBackdropPath() {
        return BackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        BackdropPath = backdropPath;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.MovieId);
        dest.writeString(this.Title);
        dest.writeString(this.Release);
        dest.writeString(this.UserRating);
        dest.writeString(this.PosterPath);
        dest.writeString(this.BackdropPath);
        dest.writeString(this.Category);
        dest.writeString(this.Overview);
    }

    public Favorite(String Title, String Overview, String Release, String PosterPath) {
        this.Title = Title;
        this.Overview = Overview;
        this.Release = Release;
        this.PosterPath = PosterPath;
    }

    public Favorite(Parcel in) {
        this.Id = in.readInt();
        this.MovieId = in.readInt();
        this.Title = in.readString();
        this.Release = in.readString();
        this.UserRating = in.readString();
        this.PosterPath = in.readString();
        this.BackdropPath = in.readString();
        this.Category = in.readString();
        this.Overview = in.readString();
    }

    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
