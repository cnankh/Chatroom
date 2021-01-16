package com.example.chatroom.services.loginService

import com.example.chatroom.model.Token
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginApiService {
    private val BASE_URL = "https://api.backtory.com/"
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(LoginApi::class.java)

    fun login(username: String, password: String): Single<Response<Token>> {
        return api.login(username, password)
    }


}