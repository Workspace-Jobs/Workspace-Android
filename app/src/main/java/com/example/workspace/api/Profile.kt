package com.example.workspace.api

data class Profile(
    val id: Int,
    val title: String,
    val img1: String,
    val user: User,
    val date: String,
    val centent: String
)