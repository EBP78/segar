package com.capstoneC23PS274.segar.data.remote.retrofit

import com.capstoneC23PS274.segar.data.remote.body.LoginBody
import com.capstoneC23PS274.segar.data.remote.body.RegisterBody
import com.capstoneC23PS274.segar.data.remote.response.CommonResponse
import com.capstoneC23PS274.segar.data.remote.response.DictionaryDetailResponse
import com.capstoneC23PS274.segar.data.remote.response.DictionaryResponse
import com.capstoneC23PS274.segar.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService{
    @POST("auth/login")
    suspend fun postLoginUser(
        @Body loginBody: LoginBody
    ) : LoginResponse

    @POST("auth/register")
    suspend fun postRegisterUser(
        @Body registerBody: RegisterBody
    ) : CommonResponse

    @GET("dictionaries")
    suspend fun getAllDict(
        @Header("Authorization") token: String
    ) : DictionaryResponse

    @GET("dictionaries/{id}")
    suspend fun getDictDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ) : DictionaryDetailResponse
}