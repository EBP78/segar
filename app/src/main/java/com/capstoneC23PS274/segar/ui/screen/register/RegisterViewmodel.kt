package com.capstoneC23PS274.segar.ui.screen.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.data.remote.body.RegisterBody
import com.capstoneC23PS274.segar.data.remote.response.CommonResponse
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.utils.ConstantValue
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
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

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> get() = _loading

    private val _errorShow = mutableStateOf(false)
    val errorShow : State<Boolean> get() = _errorShow

    private val _errorMessage = mutableStateOf("")
    val errorMessage : State<String> get() = _errorMessage

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
        _loading.value = true
        val registerBody = RegisterBody(username.value, email.value, password.value)
        job = viewModelScope.launch {
            try {
                repository.postRegister(registerBody)
                    .catch {
                        _registerResult.value = UiState.Error(it.message.toString())
                    }
                    .collect { data ->
                        if (data.message == ConstantValue.REGISTER_SUCCESS) {
                            _registerResult.value = UiState.Success(data)
                        } else {
                            _registerResult.value = UiState.Error(data.message.toString())
                            delay(25)
                            _registerResult.value = UiState.Loading
                        }
                    }
            } catch (e: Exception) {
                _registerResult.value = UiState.Error(ConstantValue.UNEXPECTED_ERROR)
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

    fun isFinished(){
        _registerResult.value = UiState.Loading
    }

}