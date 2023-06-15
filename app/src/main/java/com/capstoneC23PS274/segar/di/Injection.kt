package com.capstoneC23PS274.segar.di

import android.content.Context
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.preference.UserPreference
import com.capstoneC23PS274.segar.data.remote.retrofit.ApiConfig
import com.capstoneC23PS274.segar.network.NetworkObserver

object Injection {
    fun provideRepository(context: Context) : SegarRepository{
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference(context)
        val networkObserver = NetworkObserver(context)
        return SegarRepository(apiService, userPreference, networkObserver)
    }
}