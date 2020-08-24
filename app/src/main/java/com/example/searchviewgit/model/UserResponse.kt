package com.example.searchviewgit.model

data class UserResponse(
    val total_count: Int? = null,
    val incomplete_results: Boolean? = null,
    val items: List<User>? = null
)
