package com.capstoneC23PS274.segar.ui.screen.result

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.DictionaryResponse
import com.capstoneC23PS274.segar.data.remote.response.ResultResponse
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ResultViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _result : MutableStateFlow<UiState<ResultResponse>> = MutableStateFlow(
        UiState.Loading)
    val result : StateFlow<UiState<ResultResponse>> get() = _result

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    private val _errorShow = mutableStateOf(false)
    val errorShow : State<Boolean> get() = _errorShow

    private val _errorMessage = mutableStateOf("")
    val errorMessage : State<String> get() = _errorMessage

    fun getResult(id: String){
        _loading.value = true
        viewModelScope.launch {
            try {
                repository.getResult(id)
                    .catch {
                        _result.value = UiState.Error(it.message.toString())
                    }
                    .collect { data ->
                        _result.value = UiState.Success(data)
                    }
            } catch (e: Exception) {
                _result.value = UiState.Error("Unexpected Error")
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
}