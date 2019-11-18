package com.gahee.myapplication.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.gahee.myapplication.model.Movie;
import com.gahee.myapplication.model.MovieResponse;
import com.gahee.myapplication.utils.Config;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gahee.myapplication.utils.Constants.EN;
import static com.gahee.myapplication.utils.Constants.KEY_LANGUAGE;
import static com.gahee.myapplication.utils.Constants.KEY_PAGE;
import static com.gahee.myapplication.utils.Constants.KEY_SORT_BY;
import static com.gahee.myapplication.utils.Constants.PAGE;
import static com.gahee.myapplication.utils.Constants.POPULARITY;

public class MovieClient {

    public static final String DEBUG_RETROFIT = "retrofit";
    private static final String TAG = "MovieClient";

    //MutableLiveData 는 일종의 Wrapper class 로, List<Movie> 라는
    //데이터에 변화가 생기면 이 변화를 UI에 바로 반영할 수 있도록 해줍니다.
    private MutableLiveData<MovieResponse> listMutableLiveData
        = new MutableLiveData<>();

    //MovieService 에서 인자값으로 Map<String, String> 을 지정한 것을 위한 HashMap 입니다.
    //HashMap 은 Map 인터페이스를 구현한 자료구조 입니다.
    private HashMap<String, String> queries = new HashMap<>();

    //이 클래스는 네트워크에서 정보를 가져오는 클래스로, 객체가 여러개 생기는 것을
    //방지해야 합니다. 이를 위해 Singleton pattern 으로 객체를 생성해 줍니다.
    private static MovieClient instance;

    //singleton pattern
    public static MovieClient getInstance(){
        if(instance == null){
            //객체가 null 일 경우에만 초기화 -> 객체가 한개만 생기도록 !
            instance = new MovieClient();
        }
        return instance;
    }

    //Service 인터페이스를 이용해 정보를 가져올 것입니다.
    private MovieService movieService = RetrofitInstanceBuilder.getMovieService();

    //영화 정보를 가져오는 메소드를 정의합니다.
    public void fetchPopularMoviesClient(){

        //쿼리가 HashMap 에 담겨있지 않다면 쿼리를 append 해줍니다.
        if(queries == null || queries.size() == 0){
            appendQueries();
        }

        //Service 인터페이스에서 정의한 get 메소드를 사용해 Call 객체를 반환받습니다.
        //Call 객체에 우리가 필요한 Movie객체의 List가 담겨져 있습니다.
        final Call<MovieResponse> popularMoviesCall
                = movieService.getPopularMovies(Config.API_KEY, queries);


        Callback<MovieResponse> popularMoviesCallback
                = new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                //응답이 성공적이었을 때 반응하는 함수입니다.
                //위에서 정의한 LiveData 에 response 로 받아온 것을 setValue 해줍니다.
                listMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                //응답이 실패했을 때 반응하는 함수입니다.
                //에러 메시지를 확인하기 위해 Log 를 남겨둡시다.
                Log.d(DEBUG_RETROFIT, "On Failure : " + t.getMessage());
            }
        };

        //Call 객체에다가 위의 Callback 을 넣어주면
        //Service 에서 정의한 get 메소드가 Http GET 요청을 정의된 주소로 날리면서
        //응답을 받아오고, 해당 응답이 성공적일 경우 위의 onResponse 함수가 반응하면서
        //우리에게 필요한 정보를 넘겨줍니다.
        popularMoviesCall.enqueue(popularMoviesCallback);
    }

    //위의 Call 에서 받아올 데이터를 리턴해주는 getter 를 작성합니다.
    public MutableLiveData<MovieResponse> getListMutableLiveData() {
        return listMutableLiveData;
    }

    //쿼리를 append 하는 메소드를 따로 정의하였습니다.
    //Constants 클래스 에서 필요한 String 값들을 정의해두고 import 해서 사용합니다.
    private HashMap<String, String> appendQueries() {
        queries.put(KEY_LANGUAGE, EN);
        queries.put(KEY_PAGE, PAGE);
        return queries;
    }

}
