package com.example.belajarandroid.states

data class LoginState (
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isDialog: Boolean = false,
    val isMessage: Boolean = false,
    val isStatus: Boolean = false,
    val message: String = ""
)