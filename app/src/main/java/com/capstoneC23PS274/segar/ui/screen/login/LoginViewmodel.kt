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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
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

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    private val _errorShow = mutableStateOf(false)
    val errorShow : State<Boolean> get() = _errorShow

    private val _errorMessage = mutableStateOf("")
    val errorMessage : State<String> get() = _errorMessage

    private lateinit var job: Job

    fun updateEmail(newString: String){
        _email.value = newString
    }

    fun updatePassword(newString: String){
        _password.value = newString
    }

    fun login(){
        _loading.value = true
        val loginBody = LoginBody(email.value, password.value)
        job = viewModelScope.launch {
            repository.postLogin(loginBody)
                .catch {
                    _loginResult.value = UiState.Error(it.message.toString())
                }
                .cancellable().collect { data ->
                    _loginResult.value = UiState.Success(data)
                }
            _loading.value = false
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

    fun isFinished(){
        _loginResult.value = UiState.Loading
    }
}