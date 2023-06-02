package com.capstoneC23PS274.segar.ui.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.body.LoginBody
import com.capstoneC23PS274.segar.data.remote.response.LoginResponse
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewmodel (private val repository: SegarRepository) : ViewModel() {

    private val _loginResult : MutableStateFlow<UiState<LoginResponse>> = MutableStateFlow(UiState.Loading)
    val loginResult : StateFlow<UiState<LoginResponse>> get() = _loginResult

    private val _email = mutableStateOf("")
    val email : State<String> get() = _email

    private val _password = mutableStateOf("")
    val password : State<String> get() = _password

    private lateinit var job: Job

    fun updateEmail(newString: String){
        _email.value = newString
    }

    fun updatePassword(newString: String){
        _password.value = newString
    }

    fun login(){
        val loginBody = LoginBody(email.value, password.value)
        job = viewModelScope.launch {
            repository.postLogin(loginBody)
                .catch {
                    _loginResult.value = UiState.Error(it.message.toString())
                }
                .cancellable().collect { data ->
                    _loginResult.value =UiState.Success(data)
                }
        }
    }

    fun isFinished(){
        _loginResult.value = UiState.Loading
    }
}