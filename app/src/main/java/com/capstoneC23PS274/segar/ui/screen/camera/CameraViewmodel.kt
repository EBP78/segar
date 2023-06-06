package com.capstoneC23PS274.segar.ui.screen.camera

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.CheckResponse
import com.capstoneC23PS274.segar.data.remote.response.CheckResult
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File

class CameraViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _checkResult : MutableStateFlow<UiState<CheckResponse>> = MutableStateFlow(UiState.Loading)
    val checkResult : StateFlow<UiState<CheckResponse>> get() = _checkResult

    private lateinit var job: Job

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    private val _errorShow = mutableStateOf(false)
    val errorShow : State<Boolean> get() = _errorShow

    private val _errorMessage = mutableStateOf("")
    val errorMessage : State<String> get() = _errorMessage

    fun checkImage(file: File){
        _loading.value = true
        job = viewModelScope.launch {
            try {
                repository.postCheckImage(file)
                    .catch {
                        _checkResult.value = UiState.Error(it.message.toString())
                    }
                    .collect { data ->
                        _checkResult.value = UiState.Success(data)
                    }
            } catch (e: Exception) {
                _checkResult.value = UiState.Error("Unexpected Error")
            } finally {
                _loading.value = false
            }
        }
        _checkResult.value = UiState.Loading
    }

    fun showError(message: String){
        viewModelScope.launch {
            _errorMessage.value = message
            _errorShow.value = true
            delay(2000)
            _errorShow.value = false
        }
    }

    fun isFinished(){
        _checkResult.value = UiState.Loading
    }
}