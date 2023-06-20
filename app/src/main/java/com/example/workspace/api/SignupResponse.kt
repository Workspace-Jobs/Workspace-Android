package com.example.workspace.api

data class SignupResponse(
    val email: String,
    val password: String,
    val password2: String,
)