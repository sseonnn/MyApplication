package com.gahee.myapplication.data;

import com.gahee.myapplication.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceBuilder {

    //TODO 7 : retrofit 객체를 만들어주고, 이를 MovieService 와 연결시켜 줍니다.

    private static final Retrofit retrofit_popular_movies
            = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final MovieService MOVIE_SERVICE =
            retrofit_popular_movies.create(MovieService.class);

    public static MovieService getMovieService() {
        return MOVIE_SERVICE;
    }

}
