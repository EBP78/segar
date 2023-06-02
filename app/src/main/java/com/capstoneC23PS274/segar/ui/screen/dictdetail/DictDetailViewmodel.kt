package com.capstoneC23PS274.segar.ui.screen.dictdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.response.DictDetailItem
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DictDetailViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _dictionaryData : MutableStateFlow<UiState<DictDetailItem>> = MutableStateFlow(UiState.Loading)
    val dictionaryData : StateFlow<UiState<DictDetailItem>> get() = _dictionaryData

    fun getDictionaryDetail(id: String){
        viewModelScope.launch {
            repository.getDictionaryDetail(id)
                .catch {
                    _dictionaryData.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _dictionaryData.value = UiState.Success(data)
                }
        }
    }
}