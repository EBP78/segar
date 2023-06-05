package com.capstoneC23PS274.segar.ui.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.UserData
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _userData : MutableStateFlow<UiState<UserData>> = MutableStateFlow(UiState.Loading)
    val userData : StateFlow<UiState<UserData>> get() = _userData

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    fun getUserData() {
        _loading.value = true
        viewModelScope.launch {
            repository.getUserDetail()
                .catch {
                    _userData.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _userData.value = UiState.Success(data)
                }
            _loading.value = false
        }
    }

    fun logout(){
        repository.logout()
    }
}