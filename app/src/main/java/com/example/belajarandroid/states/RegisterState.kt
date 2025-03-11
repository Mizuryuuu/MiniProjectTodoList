package com.example.belajarandroid.states

data class RegisterState (
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
    val isDialog: Boolean = false,
    val isStatus: Boolean = false,
    val message: String = ""
)