package com.gahee.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
//정보를 뿌려주는 애

    Context context;
    ArrayList<String> dummyTextList;

    MyRecyclerViewAdapter(Context context, ArrayList<String> dummyText){
        //정보를 생성자로 넘긴다 !
        this.context = context; //Main Activity 에서 넘어오는 것임을 알려줌
        this.dummyTextList = dummyText; //정보
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
        holder.textView.setText(dummyTextList.get(position)
            + "\nView Holder position : " + position);
    }

    @Override
    public int getItemCount() {
        //넘어오는 정보의 개수
        return dummyTextList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
//액자
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_main_view_holder_text);
        }
    }
}
