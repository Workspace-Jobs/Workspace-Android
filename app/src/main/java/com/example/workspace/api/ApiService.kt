package com.example.workspace.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("EM/")
    fun getProfiles(@Query("page") page: Int): Call<List<Profile>>

    @GET("EM/{pk}")
    fun getProfile(@Path("pk") page: Int): Call<Profile>

    @GET("NB/mark")
    fun getProfiles(): Call<List<Profile>>

}