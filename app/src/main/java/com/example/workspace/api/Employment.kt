package com.example.workspace.api

import com.example.workspace.component.Userlist

data class Employment(
    val id: Int,
    val title: String,
    val img1: String,
    val user: User,
    val date: Any
)