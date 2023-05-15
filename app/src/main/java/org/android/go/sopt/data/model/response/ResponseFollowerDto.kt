package org.android.go.sopt.data.model.response

import kotlinx.serialization.Serializable
import org.android.go.sopt.data.model.main.Follower

@Serializable
data class ResponseFollowerDto(
    val data: List<Data>,
    val page: Int,
    val perPage: Int,
    val support: Support,
    val total: Int,
    val totalPages: Int
) {
    @Serializable
    data class Data(
        val avatar: String,
        val email: String,
        val firstName: String,
        val id: Int,
        val lastName: String
    )
    @Serializable
    data class Support(
        val text: String,
        val url: String
    )

    fun convertToFollower() = data.map {
        Follower(
            avatar = it.avatar,
            email = it.email,
            firstName = it.firstName
        )
    }
}