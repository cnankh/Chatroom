package com.example.chatroom.services.signUpService

import com.example.chatroom.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpAPI {
    @Headers("X-Backtory-Authentication-Id:5a1d4b3de4b0afa16474fabd")
    @POST("auth/users")
    fun signUp(
        @Body user: User
    ): Single<Response<User>>
}