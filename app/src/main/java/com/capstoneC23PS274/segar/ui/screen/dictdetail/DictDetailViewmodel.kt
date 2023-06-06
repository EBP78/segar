package com.capstoneC23PS274.segar.ui.screen.dictdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.DictDetailItem
import com.capstoneC23PS274.segar.data.remote.response.DictionaryDetailResponse
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DictDetailViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _dictionaryData : MutableStateFlow<UiState<DictionaryDetailResponse>> = MutableStateFlow(UiState.Loading)
    val dictionaryData : StateFlow<UiState<DictionaryDetailResponse>> get() = _dictionaryData

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    private val _errorShow = mutableStateOf(false)
    val errorShow : State<Boolean> get() = _errorShow

    private val _errorMessage = mutableStateOf("")
    val errorMessage : State<String> get() = _errorMessage

    fun getDictionaryDetail(id: String){
        _loading.value = true
        viewModelScope.launch {
            try {
                repository.getDictionaryDetail(id)
                    .catch {
                        _dictionaryData.value = UiState.Error(it.message.toString())
                    }
                    .collect { data ->
                        _dictionaryData.value = UiState.Success(data)
                    }
            } catch (e: Exception) {
                _dictionaryData.value = UiState.Error("Unexpected Error")
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