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

    //TODO 6 : MovieService 인터페이스를 정의하고 GET 요청을 어떻게 날릴지 정의합니다.
    //여기서는 api_key 와 다른 쿼리들을 Map으로 정의해서 넘겨주도록 합니다.
    //쿼리별로 인터페이스를 정의하는 방법이 다양하므로 필요할 때 retrofit 페이지를 참고하면 됩니다.

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key, @QueryMap Map<String, String> queries);

}
