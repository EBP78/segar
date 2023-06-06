package com.capstoneC23PS274.segar.ui.screen.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import kotlinx.coroutines.launch

class SplashViewmodel (private val repository: SegarRepository) : ViewModel() {
    private val _logoVisible = mutableStateOf(false)
    val logoVisible : State<Boolean> get() = _logoVisible

    fun checkIsLogin() : Boolean {
        return repository.isLogin()
    }

    fun changeVisible(){
        _logoVisible.value = !_logoVisible.value
    }
}