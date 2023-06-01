package com.capstoneC23PS274.segar.data.remote.retrofit

import com.capstoneC23PS274.segar.data.remote.body.LoginBody
import com.capstoneC23PS274.segar.data.remote.body.RegisterBody
import com.capstoneC23PS274.segar.data.remote.response.CommonResponse
import com.capstoneC23PS274.segar.data.remote.response.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService{
    @POST
    suspend fun postLoginUser(
        @Body loginBody: LoginBody
    ) : LoginResponse

    @POST
    suspend fun postRegisterUser(
        @Body registerBody: RegisterBody
    ) : CommonResponse
}