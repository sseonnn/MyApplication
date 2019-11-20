package com.gahee.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gahee.myapplication.model.Movie;

import static com.gahee.myapplication.utils.Constants.POSTER_BASE_URL;

public class DetailActivity extends AppCompatActivity {

    //TODO 40 : Detail layout 에 있는 뷰들을 선언, 초기화 해 줍니다.

    ImageView imgDetailMoviePoster;
    TextView tvDetailMovieTitle;
    TextView tvDetailMovieReleaseDate;
    TextView tvDetailMovieVoteAverage;
    TextView tvDetailMovieSynopsis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgDetailMoviePoster = findViewById(R.id.img_detail_movie_poster);
        tvDetailMovieTitle = findViewById(R.id.tv_detail_movie_title);
        tvDetailMovieReleaseDate = findViewById(R.id.tv_detail_movie_date);
        tvDetailMovieVoteAverage = findViewById(R.id.tv_detail_movie_vote_avg);
        tvDetailMovieSynopsis = findViewById(R.id.tv_detail_synopsis);

        //TODO 41 : 넘겨받은 Movie 객체를 회수합니다.
        Movie movie = getIntent().getParcelableExtra("MOVIE_INFO_KEY");

        //TODO 42 : 넘겨받은 Movie 객체를 이용해서 View 에 정보를 띄웁니다.
        //Adapter 클래스에서 했던 것과 거의 동일합니다.

        Glide.with(this)
                .load(POSTER_BASE_URL + movie.getPosterPath())
                .placeholder(R.color.colorHalfTransparent)
                .error(R.color.colorAccent)
                .transform(new RoundedCorners(30))
                .into(imgDetailMoviePoster);

        tvDetailMovieTitle.setText(movie.getTitle());
        tvDetailMovieReleaseDate.setText(movie.getReleaseDate());
        tvDetailMovieVoteAverage.setText(movie.getVoteAverage());
        tvDetailMovieSynopsis.setText(movie.getOverview());

    }
}
