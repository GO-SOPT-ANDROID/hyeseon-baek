package org.android.go.sopt.domain

import org.android.go.sopt.data.model.main.Follower
import org.android.go.sopt.data.model.response.ResponseFollowerDto
import retrofit2.Response
import javax.inject.Singleton


interface FollowerRepository {
    suspend fun getUserList(): Result<List<Follower>>
}