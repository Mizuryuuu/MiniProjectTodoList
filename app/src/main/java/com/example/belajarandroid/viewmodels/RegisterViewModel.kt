package com.example.belajarandroid.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.belajarandroid.events.RegisterEvent
import com.example.belajarandroid.states.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnUsernameChange -> changeUsername(event.username)
            is RegisterEvent.OnEmailChange -> changeEmail(event.email)
            is RegisterEvent.OnPasswordChange -> changePassword(event.password)
            is RegisterEvent.OnConfirmPasswordChange -> changeConfirmPassword(event.confirmPassword)
            RegisterEvent.OnPasswordVisibilityChange -> changePasswordVisibility()
            RegisterEvent.OnConfirmPasswordVisibilityChange -> changeConfirmPasswordVisibility()
            RegisterEvent.OnDismissDialog -> dismissDialog()
            RegisterEvent.OnRegister -> register()
        }
    }

    private fun dismissDialog() {
        _state.update {
            it.copy(isDialog = false, isStatus = false, message = "")
        }
    }

    private fun register() {

        if (_state.value.username.isBlank() || _state.value.email.isBlank() || _state.value.password.isBlank() || _state.value.confirmPassword.isBlank()) {
            _state.update {
                it.copy(
                    isDialog = true,
                    isStatus = false,
                    message =  "Don't leave any fields empty!"
                )
            }
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(_state.value.email).matches()) {
            _state.update {
                it.copy(
                    isDialog = true,
                    isStatus = false,
                    message = "Oops! That doesn't look like a valid email."
                )
            }
            return
        }

        if (_state.value.password != _state.value.confirmPassword) {
            _state.update {
                it.copy(
                    isDialog = true,
                    isStatus = false,
                    message = "Password and confirm password do not match."
                )
            }
            return
        }

        _state.update {
            it.copy(
                isDialog = true,
                isStatus = true,
                message = "Account's ${_state.value.username} has been successfully created"
            )
        }

    }

    private fun changeUsername(username: String) {
        _state.update {
            it.copy(username = username)
        }
    }

    private fun changeEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    private fun changePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun changeConfirmPassword(newConfirmPassword: String) {
        _state.update {
            it.copy(confirmPassword = newConfirmPassword)
        }
    }

    private fun changePasswordVisibility() {
        _state.update {
            it.copy(passwordVisible = !_state.value.passwordVisible)
        }
    }


    private fun isValidEmail(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(regex)
    }

    private fun changeConfirmPasswordVisibility() {
        _state.update {
            it.copy(confirmPasswordVisible = !_state.value.confirmPasswordVisible)
        }
    }

}





















