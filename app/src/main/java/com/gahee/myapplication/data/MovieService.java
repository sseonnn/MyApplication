package com.gahee.myapplication.data;

import com.gahee.myapplication.model.MovieResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MovieService {

    //https://api.themoviedb.org/3/movie/popular?api_key=2c702e667781c19eb48ec3b2193c97c8&language=en-US&page=1

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key, @QueryMap Map<String, String> queries);

}
