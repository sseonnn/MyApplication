package com.gahee.myapplication.data;

import androidx.lifecycle.MutableLiveData;

import com.gahee.myapplication.model.Movie;
import com.gahee.myapplication.model.MovieResponse;

import java.util.List;

public class RemoteViewModel {

    //아래 레이어에서 정의한 getter 들은 ViewModel 클래스를 통해서 호출합니다.
    //ViewModel 을 상속받은 클래스는 LiveData 를 관찰할 수 있도록 하는 클래스로
    //onCreate() 함수에서 - Main 함수 - ViewModel 에 observe() 메소드를
    //호출하면 데이터가 변경될 때마다 해당 메소드가 호출됩니다.

    //Repository class 에서 정의한 getter 들과 데이터를 요청하는 fetch 함수를
    //받아오기 위한 객체 선언 입니다.
    private final RemoteRepo remoteRepo;

    //ViewModel 클래스가 초기화 되면서 repository 클래스도 초기화 됩니다.
    //연쇄적으로 Repository 클래스 안에 있는 Client 클래스도 초기화 됩니다.
    public RemoteViewModel(){
        remoteRepo = RemoteRepo.getInstance();
    }

    //LiveData 를 가져오는 getter 의 layer 를 한단계 더 만듭니다.
    public MutableLiveData<MovieResponse> getPopularMovies(){
        return remoteRepo.getPopularMoviesRepo();
    }

    //네트워크 처리를 시작시키는 메소드를 호출합니다.
    public void fetchPopularMoviesFromRemote(){
        remoteRepo.fetchPopularMoviesAsync();
    }


}
