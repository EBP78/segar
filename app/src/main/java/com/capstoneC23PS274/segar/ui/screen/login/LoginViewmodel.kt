package com.capstoneC23PS274.segar.ui.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.body.LoginBody
import com.capstoneC23PS274.segar.data.remote.response.LoginResponse
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewmodel (private val repository: SegarRepository) : ViewModel() {

    private val _loginResult : MutableStateFlow<UiState<LoginResponse>> = MutableStateFlow(UiState.Loading)
    val loginResult : StateFlow<UiState<LoginResponse>> get() = _loginResult

    private val _email = mutableStateOf("")
    val email : State<String> get() = _email

    private val _password = mutableStateOf("")
    val password : State<String> get() = _password

    fun updateEmail(newString: String){
        _email.value = newString
    }

    fun updatePassword(newString: String){
        _password.value = newString
    }

    fun login(){
        val loginBody = LoginBody(email.value, password.value)
        viewModelScope.launch {
            repository.postLogin(loginBody)
                .catch {
                    _loginResult.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _loginResult.value =UiState.Success(data)
                }
        }
    }

}