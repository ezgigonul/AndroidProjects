package com.example.getapp.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("api/rp/list/?format=json")
    Call<VeriListem> verilerimilistele();

    @FormUrlEncoded
    @POST("api/rp/create/")
    Call<PostListem> at(@Field("isOpen") Boolean isOpen,
                        @Field("closedDate") String closedDate,
                        @Field("created") Integer created,
                        @Field("closed") Integer closed
                        );
}

