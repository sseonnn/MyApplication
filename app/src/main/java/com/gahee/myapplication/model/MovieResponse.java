package com.gahee.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieResponse implements Parcelable{

    //TODO 4 : JSON 객체를 받아와서 Java로 변환하기 위한 클래스를 작성합니다.

    @SerializedName("results")
    List<Movie> results;

    MovieResponse(){

    }

    protected MovieResponse(Parcel in) {
        results = in.createTypedArrayList(Movie.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };

    public List<Movie> getResults() {
            return results;
        }


}
