package com.example.belajarandroid.events

sealed interface LoginEvent {
    data class OnUsernameChange(val username: String): LoginEvent
    data class OnPasswordChange(val password: String): LoginEvent
    data object OnPasswordVisibilityChanged: LoginEvent
    data object OnDismissDialog: LoginEvent
    data object OnLogin: LoginEvent
}