package com.example.chatroom.services.loginService

import com.example.chatroom.model.Token
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST


interface LoginApi {
    @Headers(
        "X-Backtory-Authentication-Id:5a1d4b3de4b0afa16474fabd",
        "X-Backtory-Authentication-Key:5a1d4b3de4b0ce09cd4655c8"
    )
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<Response<Token>>
}