package com.capstoneC23PS274.segar.ui.screen.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.HistoryItem
import com.capstoneC23PS274.segar.data.remote.response.HistoryResponse
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.utils.ConstantValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HistoryViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _history : MutableStateFlow<UiState<HistoryResponse>> = MutableStateFlow(UiState.Loading)
    val history : StateFlow<UiState<HistoryResponse>> get() = _history

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    private val _errorShow = mutableStateOf(false)
    val errorShow : State<Boolean> get() = _errorShow

    private val _errorMessage = mutableStateOf("")
    val errorMessage : State<String> get() = _errorMessage

    fun getHistory(){
        _loading.value = true
        viewModelScope.launch {
            try {
                repository.getHistory()
                    .catch {
                        _history.value = UiState.Error(it.message.toString())
                    }
                    .collect { data ->
                        _history.value = UiState.Success(data)
                    }
            } catch (e: Exception) {
                _history.value = UiState.Error(ConstantValue.UNEXPECTED_ERROR)
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