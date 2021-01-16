package com.example.chatroom.services.signUpService

import com.example.chatroom.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SignUpApiService {
    private val BASE_URL = "https://api.backtory.com/"
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(SignUpAPI::class.java)

    fun singUp(user: User): Single<Response<User>> {
        return api.signUp(user)
    }

}