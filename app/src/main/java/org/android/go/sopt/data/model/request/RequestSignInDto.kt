package org.android.go.sopt.data.model.request

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RequestSignInDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String
)
