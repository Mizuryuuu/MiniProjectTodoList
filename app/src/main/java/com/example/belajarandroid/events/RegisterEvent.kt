package com.example.belajarandroid.events

sealed interface RegisterEvent {
    data class OnUsernameChange(val username: String): RegisterEvent
    data class OnEmailChange(val email: String): RegisterEvent
    data class OnPasswordChange(val password: String): RegisterEvent
    data class OnConfirmPasswordChange(val confirmPassword: String): RegisterEvent
    data object OnPasswordVisibilityChange: RegisterEvent
    data object OnConfirmPasswordVisibilityChange: RegisterEvent
    data object OnDismissDialog: RegisterEvent
    data object OnRegister: RegisterEvent
}