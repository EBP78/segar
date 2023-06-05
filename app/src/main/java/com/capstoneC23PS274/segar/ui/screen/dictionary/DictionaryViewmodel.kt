package com.capstoneC23PS274.segar.ui.screen.dictionary

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.DictionaryItem
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DictionaryViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _dictionary : MutableStateFlow<UiState<List<DictionaryItem>>> = MutableStateFlow(UiState.Loading)
    val dictionary : StateFlow<UiState<List<DictionaryItem>>> get() = _dictionary

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    fun getAllDictionary(){
        _loading.value = true
        viewModelScope.launch {
            repository.getDictionary()
                .catch {
                    _dictionary.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _dictionary.value = UiState.Success(data)
                }
            _loading.value = false
        }
    }
}