package com.b8labs.retrofitdemo.core;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by badeyemi on 8/27/16.
 */

public interface Interface {
    @FormUrlEncoded
    @POST("/api.php")
    void postData(@Field("method") String method,
                  @Field("username") String username,
                  @Field("password") String password,
                  Callback<ServerResponse> serverResponseCallback);

    @GET("/api.php")
    void getData(@Field("method") String method,
                 @Field("username") String username,
                 @Field("password") String password,
                 Callback<ServerResponse> serverResponseCallback);
}
