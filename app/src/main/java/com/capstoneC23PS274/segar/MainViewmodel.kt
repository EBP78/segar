package com.capstoneC23PS274.segar

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.ViewModel
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.ui.common.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewmodel (private val repository: SegarRepository) : ViewModel() {
    fun networkObserver() : Flow<NetworkState<String>> {
        return repository.observeNetwork()
    }
}