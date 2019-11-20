package com.gahee.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    //TODO 5 : JSON으로 받은 results 라는 리스트 안의 요소들을 Java로 변환하기 위한 클래스를 작성합니다.

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    protected Movie(Parcel in) {
        posterPath = in.readString();
        id = in.readInt();
        title = in.readString();
        voteAverage = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(voteAverage);
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPosterPath() {
        return posterPath;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
