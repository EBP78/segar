package com.capstoneC23PS274.segar.di

import android.content.Context
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.preference.UserPreference
import com.capstoneC23PS274.segar.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context) : SegarRepository{
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference(context)
        return SegarRepository(apiService, userPreference)
    }
}