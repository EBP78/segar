package com.capstoneC23PS274.segar.data

import com.capstoneC23PS274.segar.data.preference.UserPreference
import com.capstoneC23PS274.segar.data.remote.body.LoginBody
import com.capstoneC23PS274.segar.data.remote.body.RegisterBody
import com.capstoneC23PS274.segar.data.remote.response.CommonResponse
import com.capstoneC23PS274.segar.data.remote.response.DictDetailItem
import com.capstoneC23PS274.segar.data.remote.response.DictionaryItem
import com.capstoneC23PS274.segar.data.remote.response.LoginResponse
import com.capstoneC23PS274.segar.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SegarRepository (private val apiService: ApiService, private val userPreference: UserPreference) {

    private val token = userPreference.getToken()

    suspend fun postLogin(loginBody: LoginBody) : Flow<LoginResponse> {
        val result : LoginResponse = apiService.postLoginUser(loginBody)
        userPreference.login(result.data?.token.toString())
        return flowOf(result)
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
}