package com.capstoneC23PS274.segar.ui.screen.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.CheckResult
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File

class CameraViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _checkResult : MutableStateFlow<UiState<CheckResult>> = MutableStateFlow(UiState.Loading)
    val checkResult : StateFlow<UiState<CheckResult>> get() = _checkResult

    private lateinit var job: Job

    fun checkImage(file: File){
        job = viewModelScope.launch {
            repository.postCheckImage(file)
                .catch {
                    _checkResult.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _checkResult.value = UiState.Success(data)
                }
        }
    }

    fun isFinished(){
        _checkResult.value = UiState.Loading
    }
}