package com.example.workspace.api

import com.example.workspace.component.Profile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @POST("user/login/") // 실제 API의 엔드포인트에 맞게 수정해야 합니다.
    fun login(
        @Body body: LoginRequest
//        @Field("email") email: String,
//        @Field("password") password: String
    ): Call<LoginResponse>


    @POST("user/registration/") // 실제 API의 엔드포인트에 맞게 수정해야 합니다.
    fun signup(
        @Body body: SignupRequest
//        @Field("email") email: String,
//        @Field("password") password: String,
//        @Field("password2") password2: String
        ): Call<SignupResponse>

    @GET("NB/mark")
    fun getProfiles(): Call<List<Profile>>


}