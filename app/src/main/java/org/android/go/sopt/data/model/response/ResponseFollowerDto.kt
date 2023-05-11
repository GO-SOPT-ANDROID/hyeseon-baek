package org.android.go.sopt.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.go.sopt.data.model.main.Follower

@Serializable
data class ResponseFollowerDto(
    val data: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
) {
    @Serializable
    data class Data(
        val avatar: String,
        val email: String,
        val first_name: String,
        val id: Int,
        val last_name: String
    )
    @Serializable
    data class Support(
        val text: String,
        val url: String
    )

    fun convertToFollower(): List<Follower> {
        val followerList = data.map{
            Follower(
                avatar = it.avatar,
                email = it.email,
                firstName = it.first_name
            )
        }
        return followerList
    }
}