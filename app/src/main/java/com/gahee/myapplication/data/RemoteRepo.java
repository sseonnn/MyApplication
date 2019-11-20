package com.gahee.myapplication.data;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import com.gahee.myapplication.model.MovieResponse;


public class RemoteRepo {

    //Repository 의 목적 : 백그라운드에서 네트워크 처리를 해줍니다.

    //TODO 19 : client 객체를 선언해 줍니다.
    private MovieClient movieClient;

    //TODO 20 : Repository 클레스도 Singleton 으로 만들어 주세요.
    private static RemoteRepo instance;
    public static RemoteRepo getInstance(){
        if(instance == null){
            instance = new RemoteRepo();
        }
        return instance;
    }

    //TODO 21 : client 객체를 초기화 합니다.
    public RemoteRepo(){
        //위의 RemoteRepo 인스턴스가 만들어 지면서 client 클래스의 객체도
        //동시에 생성됩니다.
        movieClient = MovieClient.getInstance();
    }

    //TODO 23 : AsyncTask 를 execute 할 메소드를 작성합니다.
    public void fetchPopularMoviesAsync(){
        // AsyncTask 클래스 객체를 만들어서 execute() 시켜줍니다.
        new PopularMoviesAsync(movieClient).execute();
    }


    // TODO 24 : Client 에서 정의된 getter 를 호출합니다.
    public MutableLiveData<MovieResponse> getPopularMoviesRepo(){
        return movieClient.getListMutableLiveData();
    }



    // TODO 22 : AsyncTask 클래스를 작성합니다.
    //Background thread 로 영화 데이터를 가져오는 함수를 실행합니다.
    static class PopularMoviesAsync extends AsyncTask<Void, Void, Void> {

        MovieClient movieClient;

        private PopularMoviesAsync(MovieClient m){
            //Client 객체를 생성자를 통해서 받아옵니다.
            movieClient = m;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Client 클래스에서 작성한 fetch 메소드 입니다.
            movieClient.fetchPopularMoviesClient();
            return null;
        }
    }

}
