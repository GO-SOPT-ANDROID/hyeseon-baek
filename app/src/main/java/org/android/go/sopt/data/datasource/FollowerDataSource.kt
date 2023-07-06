package org.android.go.sopt.data.datasource

import org.android.go.sopt.data.model.remote.response.ResponseFollowerDto
import org.android.go.sopt.data.service.FollowerApiService
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val followerService: FollowerApiService,
) {
    suspend fun getFollowerList(): ResponseFollowerDto =
        followerService.getFollowerList()
}