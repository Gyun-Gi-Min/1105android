package com.koreait.board2;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BoardService {
    @GET("list")
    Call<List<BoardVO>> SelBoardList();

    @POST("ins")
    Call<Map<String,Integer>> insBoard(@Body BoardVO p);
    //순서가 없는 컬렉션?

    @GET("one")
    Call<BoardVO>

}
