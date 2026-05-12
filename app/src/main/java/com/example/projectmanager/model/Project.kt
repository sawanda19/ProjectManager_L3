package com.example.projectmanager.model

data class Project(
    val id: Int,
    val name: String,
    val description: String,
    val progress: Float = 0f
)