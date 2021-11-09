package com.koreait.board2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private BoardListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

        rvList = findViewById(R.id.rvList);
        adapter = new BoardListAdapter();
        rvList.setAdapter(adapter);

    }
    @Override
    protected void onStart(){
        super.onStart();;
        getBoardList();
    }

    public void clkWrite(View v) {  //누르면 글쓰기란으로 이동~
        Intent intent = new Intent(this, BoardWriteActivity.class);
        startActivity(intent);
    }

    public void clkdetail(View v) {  //누르면 detail로
        Intent intent = new Intent(this, BoardDetailActivity.class);
        startActivity(intent);
    }



    private void getBoardList(){
        Call<List<BoardVO>> call = Network.getService().SelBoardList();
        call.enqueue(new Callback<List<BoardVO>>() {
            @Override
            public void onResponse(Call<List<BoardVO>> call, Response<List<BoardVO>> response) {
                if(response.isSuccessful()){
                    List<BoardVO> list = response.body();
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.i("myLog","aaaaa");
                }
            }
            @Override
            public void onFailure(Call<List<BoardVO>> call, Throwable t) {
                Log.e("myLog","dddd");
            }
        });
    }
}

class BoardListAdapter extends RecyclerView.Adapter<BoardListAdapter.MyViewholder>{
    private List<BoardVO> list;


    public void setList(List<BoardVO> list) { this.list = list; }
        //외부에서 주소값 바꾸게해주는거?
    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board,parent,false); //한줄로 넣은거임
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardListAdapter.MyViewholder holder, int position) {
        BoardVO vo = list.get(position);
        holder.setItem(vo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), BoardDetailActivity.class);
                    intent.putExtra("iboard",vo.getIboard());
                    v.getContext().startActivity(intent);
            }
//            public void clkdetail(View v) {  //누르면 detail로
//                Intent intent = new Intent(this, BoardDetailActivity.class);
//                startActivity(intent);
//            }

        });
    }

    @Override
    public int getItemCount() { return list == null ? 0 : list.size(); }


    static class MyViewholder extends RecyclerView.ViewHolder{
        private TextView tvIboard;
        private TextView tvTitle;
        private TextView tvWriter;
        private TextView tvRdt;

        public MyViewholder(View v){
            super(v);
            tvIboard=v.findViewById(R.id.tvIboard);
            tvTitle=v.findViewById(R.id.tvTitle);
            tvWriter=v.findViewById(R.id.tvWriter);
            tvRdt=v.findViewById(R.id.tvRdt);
        }
        public void setItem(BoardVO param){
            tvIboard.setText(String.valueOf(param.getIboard()));  // 만약 setText()안에 int값 쓰고 싶으면 strings에서 관리하는 int값만 써줘야한다.
            tvTitle.setText(param.getTitle());
            tvWriter.setText(param.getWriter());
            tvRdt.setText(param.getRdt());
        }

    }

}

