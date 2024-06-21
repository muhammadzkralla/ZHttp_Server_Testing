package com.zkrallah.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int? = null,
    val userId: Int? = null,
    val title: String? = null,
    val body: String? = null
)
