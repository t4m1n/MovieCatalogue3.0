package com.fiqartamin.moviecatalogue3.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Tv implements Parcelable {
    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String release;

    @SerializedName("poster_path")
    private String photo;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("vote_average")
    private String average;

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release);
        dest.writeString(this.photo);
        dest.writeString(this.average);
        dest.writeString(this.backdrop);
    }

    public Tv() {
    }

    protected Tv(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.release = in.readString();
        this.photo = in.readString();
        this.average = in.readString();
        this.backdrop = in.readString();
    }

    public static final Parcelable.Creator<Tv> CREATOR = new Parcelable.Creator<Tv>() {
        @Override
        public Tv createFromParcel(Parcel source) {
            return new Tv(source);
        }

        @Override
        public Tv[] newArray(int size) {
            return new Tv[size];
        }
    };
}
