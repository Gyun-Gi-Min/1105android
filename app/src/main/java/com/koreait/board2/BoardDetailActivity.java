package com.koreait.board2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import retrofit2.Retrofit;

public class BoardDetailActivity extends AppCompatActivity {

    private TextView tvIboard;
    private TextView tvTitle;
    private TextView tvCtnt;
    private TextView tvWriter;
    private TextView tvRdt;
    private int iboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        tvIboard =findViewById(R.id.tvIboard);
        tvTitle =findViewById(R.id.tvTitle);
        tvCtnt =findViewById(R.id.tvCtnt);
        tvWriter =findViewById(R.id.tvWriter);
        tvRdt =findViewById(R.id.tvRdt);

        Intent intent = getIntent();
        iboard = intent.getIntExtra("iboard",0);

        BoardVO vo = new BoardVO();

        tvIboard.setText(vo.getIboard());
        tvTitle.setText(vo.getTitle());
        tvCtnt.setText(vo.getCtnt());
        tvWriter.setText(vo.getWriter());
        tvRdt.setText(vo.getRdt());







    }

    @Override
    protected void onStart(){
        super.onStart();
        getBoardDetail();
    }

    private void getBoardDetail(){

    }





}