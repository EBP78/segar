package com.capstoneC23PS274.segar.data

import com.capstoneC23PS274.segar.data.preference.UserPreference
import com.capstoneC23PS274.segar.data.remote.body.LoginBody
import com.capstoneC23PS274.segar.data.remote.body.RegisterBody
import com.capstoneC23PS274.segar.data.remote.response.CheckResponse
import com.capstoneC23PS274.segar.data.remote.response.CheckResult
import com.capstoneC23PS274.segar.data.remote.response.CommonResponse
import com.capstoneC23PS274.segar.data.remote.response.DictDetailItem
import com.capstoneC23PS274.segar.data.remote.response.DictionaryItem
import com.capstoneC23PS274.segar.data.remote.response.HistoryItem
import com.capstoneC23PS274.segar.data.remote.response.LoginResponse
import com.capstoneC23PS274.segar.data.remote.response.UserData
import com.capstoneC23PS274.segar.data.remote.retrofit.ApiService
import com.capstoneC23PS274.segar.ui.screen.camera.reduceFileImage
import com.capstoneC23PS274.segar.utils.ConstantValue
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class SegarRepository (private val apiService: ApiService, private val userPreference: UserPreference) {

    private val token = ConstantValue.AUTH + userPreference.getToken()

    suspend fun postLogin(loginBody: LoginBody) : Flow<LoginResponse> {
        val response = apiService.postLoginUser(loginBody)
        if (response.isSuccessful && response.body() != null && response.code() != 401){
            val result : LoginResponse = response.body()!!
            userPreference.login(result.data?.token.toString())
            return flowOf(result)
        } else {
            val errResponse = Gson().fromJson(response.errorBody()?.string(), CommonResponse::class.java)
            val result : LoginResponse = LoginResponse(
                data = null,
                error = errResponse.error,
                message = errResponse.message
            )
            return flowOf(result)
        }
    }

    suspend fun postRegister(registerBody: RegisterBody) : Flow<CommonResponse> {
        val result : CommonResponse = apiService.postRegisterUser(registerBody)
        return  flowOf(result)
    }

    suspend fun getDictionary() : Flow<List<DictionaryItem>>{
        val result : List<DictionaryItem> = apiService.getAllDict(token).data
        return flowOf(result)
    }

    suspend fun getDictionaryDetail(id: String) : Flow<DictDetailItem>{
        val result : DictDetailItem = apiService.getDictDetail(token, id).data
        return flowOf(result)
    }

    suspend fun getHistory() : Flow<List<HistoryItem>>{
        val result : List<HistoryItem> = apiService.getHistory(token).data
        return flowOf(result)
    }

    suspend fun getUserDetail() : Flow<UserData> {
        val result : UserData = apiService.getUserProfile(token).data
        return flowOf(result)
    }

    suspend fun postCheckImage(file: File) : Flow<CheckResult>{
        val uploadFile = reduceFileImage(file)
        val requestImageFile = uploadFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart : MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            uploadFile.name,
            requestImageFile
        )
        val result : CheckResult = apiService.postCheckImage(token, imageMultipart).data
        return flowOf(result)
    }

    fun logout(){
        userPreference.logout()
    }
}