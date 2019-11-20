package com.gahee.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gahee.myapplication.model.Movie;

import java.util.ArrayList;

import static com.gahee.myapplication.utils.Constants.POSTER_BASE_URL;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
//정보를 뿌려주는 애

    //TODO 32 : Adapter 클래스를 아래와 같이 수정해 줍니다. 그 전에 rv_main_view_holder 레이아웃을 수정해 줍시다.
    Context context;
    ArrayList<Movie> movies;

    MyRecyclerViewAdapter(Context context, ArrayList<Movie> movies){
        //정보를 생성자로 넘긴다 !
        this.context = context; //Main Activity 에서 넘어오는 것임을 알려줌
        this.movies = movies; //정보
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //액자 레이아웃 만들기
        View view = LayoutInflater.from(context).inflate(R.layout.rv_main_view_holder, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //여기서 뷰홀더에 position 별로 데이터 뿌려줌
        //생성자로 넘겨받은 movie 데이터를 연결해 줍시다.

        final Movie movie = movies.get(position);
        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvMovieReleaseDate.setText(movie.getReleaseDate());
        holder.tvMovieVoteAverage.setText(movie.getVoteAverage());
        holder.tvMovieSynopsis.setText(movie.getOverview());

        //Glide 사용해서 이미지 띄우기
        //https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

        //TODO 34 : Glide 디펜던시를 추가하고, 영화 포스터 base url 을 constants 클래스에 추가한 뒤
        //아래와 같이 이미지를 띄우는 코드를 작성합니다.
        Glide.with(context)
                .load(POSTER_BASE_URL + movie.getPosterPath())
                .placeholder(R.color.colorHalfTransparent)
                .error(R.color.colorAccent)
                .transform(new RoundedCorners(30))
                .into(holder.imgMoviePoster);

        //TODO 36 : 클릭 텍스트를 클릭하면 DetailActivity 로 정보를 넘겨줍니다.
        holder.tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO 37 : Intent 객체를 만들어 줍니다. Main 에서 Detail 로 간다는 것을 명시합니다.
                Intent intent = new Intent(context, DetailActivity.class);

                // TODO 38 : putExtra 메소드 안에 Key 스트링과 movie 객체를 넣어줍니다.
                //Key 는 Detail 화면에서 정보를 회수할 때 쓰입니다.
                intent.putExtra("MOVIE_INFO_KEY", movie);

                //TODO 39 : startActivity 를 호출해 줍니다.
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //넘어오는 정보의 개수
        return movies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
//액자
        ImageView imgMoviePoster;
        TextView tvMovieTitle;
        TextView tvMovieVoteAverage;
        TextView tvMovieReleaseDate;
        TextView tvMovieSynopsis;
        TextView tvClick;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMoviePoster = itemView.findViewById(R.id.img_main_movie_poster);
            tvMovieTitle = itemView.findViewById(R.id.tv_main_movie_title);
            tvMovieVoteAverage = itemView.findViewById(R.id.tv_main_movie_vote_average);
            tvMovieReleaseDate = itemView.findViewById(R.id.tv_main_movie_release_date);
            tvMovieSynopsis = itemView.findViewById(R.id.tv_main_movie_synopsis);

            //TODO 35 : Detail 화면으로 넘어갈 때 클릭할 TextView 를 찾아줍니다.
            tvClick = itemView.findViewById(R.id.tv_main_click_me);
        }
    }
}
