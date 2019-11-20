package com.gahee.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.gahee.myapplication.data.RemoteViewModel;
import com.gahee.myapplication.model.Movie;
import com.gahee.myapplication.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;

    //ViewModel 클래스 객체를 선언합니다.
    RemoteViewModel remoteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_main);
        adapter = new MyRecyclerViewAdapter(this, generateDummy());

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        //onCreate() 안에서 remoteViewModel 객체를 초기화 해줍니다.
        remoteViewModel = ViewModelProviders.of(this).get(RemoteViewModel.class);

        //remoteViewModel 안의 네트워크 코드를 호출합니다.
        //Client 인 우리가 영화 정보를 받아오게 되었습니다 !
        remoteViewModel.fetchPopularMoviesFromRemote();

        //이제 원격에서 fetch 를 했으니 getter 로 정보를 받아와서 observer
        //로 관찰합니다.
        remoteViewModel.getPopularMovies().observe(this, new Observer<MovieResponse>() {
            @Override
            public void onChanged(MovieResponse response) {
                //데이터가 처음으로 불려 왔을 때, 네트워크 콜이 다시 이루어 졌을 때
                //onChanged 함수가 호출됩니다.

                //정보가 제대로 불려졌는지 확인 차 로그로 출력해 봅시다.
                List<Movie> movies = response.getResults();

                for(int i = 0; i < movies.size(); i++){
                    Log.d(TAG, "onChanged: "
                    + "\nMovie Id : " + movies.get(i).getId()
                    + "\nMovie Title : " + movies.get(i).getTitle()
                    + "\nPoster Path : " + movies.get(i).getPosterPath()
                    + "\nOverview : " + movies.get(i).getOverview()
                    + "\nRelease Date : " + movies.get(i).getReleaseDate()
                    + "\nVote Average : " + movies.get(i).getVoteAverage()
                    );
                }
            }
        });
    }

    private ArrayList<String> generateDummy(){
        ArrayList<String> dummy = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            dummy.add("dummy" + i);
        }

        return dummy;
    }
}
