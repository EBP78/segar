package com.capstoneC23PS274.segar.ui.screen.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.body.RegisterBody
import com.capstoneC23PS274.segar.data.remote.response.CommonResponse
import com.capstoneC23PS274.segar.data.remote.response.LoginResponse
import com.capstoneC23PS274.segar.ui.common.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewmodel (private val repository: SegarRepository) : ViewModel() {

    private val _registerResult : MutableStateFlow<UiState<CommonResponse>> = MutableStateFlow(UiState.Loading)
    val registerResult : StateFlow<UiState<CommonResponse>> get() = _registerResult

    private val _username = mutableStateOf("")
    val username : State<String> get() = _username

    private val _email = mutableStateOf("")
    val email : State<String> get() = _email

    private val _password = mutableStateOf("")
    val password : State<String> get() = _password

    private val _confirmPassword = mutableStateOf("")
    val confirmPassword : State<String> get() = _confirmPassword

    private val _canClick = mutableStateOf(false)
    val canClick : State<Boolean> get() = _canClick

    private lateinit var job: Job

    fun updateUsername(newString: String){
        _username.value = newString
    }

    fun updateEmail(newString: String){
        _email.value = newString
    }

    fun updatePassword(newString: String){
        _password.value = newString
    }

    fun updateConfirmPassword(newString: String){
        _confirmPassword.value = newString
        _canClick.value = newString.isNotEmpty() && newString == _password.value
    }

    fun registerUser(){
        val registerBody = RegisterBody(username.value, email.value, password.value)
        val job = viewModelScope.launch {
            repository.postRegister(registerBody)
                .catch {
                    _registerResult.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _registerResult.value = UiState.Success(data)
                }
        }
    }

    fun isFinished(){
        _registerResult.value = UiState.Loading
    }

}