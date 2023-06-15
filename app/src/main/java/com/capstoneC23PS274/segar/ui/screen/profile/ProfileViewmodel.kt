package com.capstoneC23PS274.segar.ui.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.CommonResponse
import com.capstoneC23PS274.segar.data.remote.response.ProfileResponse
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.utils.ConstantValue
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _userData : MutableStateFlow<UiState<ProfileResponse>> = MutableStateFlow(UiState.Loading)
    val userData : StateFlow<UiState<ProfileResponse>> get() = _userData

    private val _logoutResult : MutableStateFlow<UiState<CommonResponse>> = MutableStateFlow(UiState.Loading)
    val logoutResult : StateFlow<UiState<CommonResponse>> get() = _logoutResult

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    private val _errorShow = mutableStateOf(false)
    val errorShow : State<Boolean> get() = _errorShow

    private val _errorMessage = mutableStateOf("")
    val errorMessage : State<String> get() = _errorMessage

    private lateinit var job: Job

    fun getUserData() {
        _loading.value = true
        viewModelScope.launch {
            try {
                repository.getUserDetail()
                    .catch {
                        _userData.value = UiState.Error(it.message.toString())
                    }
                    .collect { data ->
                        _userData.value = UiState.Success(data)
                    }
            } catch (e: Exception) {
                _userData.value = UiState.Error(ConstantValue.UNEXPECTED_ERROR)
            } finally {
                _loading.value = false
            }
        }
    }

    fun showError(message: String){
        viewModelScope.launch {
            _errorMessage.value = message
            _errorShow.value = true
            delay(2000)
            _errorShow.value = false
        }
    }

    fun logout(email: String){
        _loading.value = true
        job = viewModelScope.launch {
            try {
                repository.logout(email)
                    .catch {
                        _logoutResult.value = UiState.Error(it.message.toString())
                    }
                    .collect { data ->
                        _logoutResult.value = UiState.Success(data)
                        delay(25)
                        _logoutResult.value = UiState.Loading
                    }
            } catch (e: Exception) {
                _logoutResult.value = UiState.Error(ConstantValue.UNEXPECTED_ERROR)
            } finally {
                _loading.value = false
            }
        }
    }

    fun isFinished() {
        _logoutResult.value = UiState.Loading
    }
}