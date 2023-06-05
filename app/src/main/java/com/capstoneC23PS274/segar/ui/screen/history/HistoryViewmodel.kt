package com.capstoneC23PS274.segar.ui.screen.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.HistoryItem
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HistoryViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _history : MutableStateFlow<UiState<List<HistoryItem>>> = MutableStateFlow(UiState.Loading)
    val history : StateFlow<UiState<List<HistoryItem>>> get() = _history

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    fun getHistory(){
        _loading.value = true
        viewModelScope.launch {
            repository.getHistory()
                .catch {
                    _history.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _history.value = UiState.Success(data)
                }
            _loading.value = false
        }
    }
}