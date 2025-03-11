package com.example.belajarandroid.viewmodels

import androidx.lifecycle.ViewModel
import com.example.belajarandroid.events.LoginEvent
import com.example.belajarandroid.states.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnUsernameChange -> changeUsername(event.username)
            is LoginEvent.OnPasswordChange -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibilityChanged -> changePasswordVisibility()
            LoginEvent.OnDismissDialog -> dismissDialog()
            LoginEvent.OnLogin -> login()
        }
    }

    private fun dismissDialog() {
        _state.update {
            it.copy(isDialog = false, isStatus = false, message = "")
        }
    }

    private  fun login() {
        if (_state.value.username.isBlank()) {
            _state.update {
                it.copy(isDialog = true, isStatus = false, message = "Username tidak boleh kosong")
            }
            return
        }
        if (_state.value.password.isBlank()) {
            _state.update {
                it.copy(isDialog = true, isStatus = false, message = "Password tidak boleh kosong")
            }
            return
        }
        _state.update {
            it.copy(
                isDialog = true,
                isStatus = true,
                message = "${_state.value.username} berhasil login"
            )
        }
    }

    private fun changeUsername(username: String) {
        _state.update {
            it.copy(username = username)
        }
    }

    private fun changePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun changePasswordVisibility() {
        _state.update {
            it.copy(isPasswordVisible = !_state.value.isPasswordVisible)
        }
    }

}