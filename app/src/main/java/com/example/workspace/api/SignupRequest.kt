package com.example.workspace.api

data class SignupRequest(
    val email: String,
    val password1: String,
    val password2: String
)