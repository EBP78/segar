package com.capstoneC23PS274.segar.data.remote.retrofit

import com.capstoneC23PS274.segar.data.remote.body.LoginBody
import com.capstoneC23PS274.segar.data.remote.body.RegisterBody
import com.capstoneC23PS274.segar.data.remote.response.CheckResponse
import com.capstoneC23PS274.segar.data.remote.response.CommonResponse
import com.capstoneC23PS274.segar.data.remote.response.DictionaryDetailResponse
import com.capstoneC23PS274.segar.data.remote.response.DictionaryResponse
import com.capstoneC23PS274.segar.data.remote.response.HistoryResponse
import com.capstoneC23PS274.segar.data.remote.response.LoginResponse
import com.capstoneC23PS274.segar.data.remote.response.ProfileResponse
import com.capstoneC23PS274.segar.data.remote.response.ResultResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService{
    @POST("auth/login")
    suspend fun postLoginUser(
        @Body loginBody: LoginBody
    ) : Response<LoginResponse>

    @POST("auth/register")
    suspend fun postRegisterUser(
        @Body registerBody: RegisterBody
    ) : Response<CommonResponse>

    @GET("dictionaries")
    suspend fun getAllDict(
        @Header("Authorization") token: String
    ) : Response<DictionaryResponse>

    @GET("dictionaries/{id}")
    suspend fun getDictDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ) : Response<DictionaryDetailResponse>

    @GET("predictions")
    suspend fun getHistory(
        @Header("Authorization") token: String
    ) : Response<HistoryResponse>

    @GET("predictions/{id}")
    suspend fun getResult(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ) : Response<ResultResponse>

    @GET("auth/user")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ) : Response<ProfileResponse>

    @Multipart
    @POST("predictions")
    suspend fun postCheckImage(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part
    ) : Response<CheckResponse>
}