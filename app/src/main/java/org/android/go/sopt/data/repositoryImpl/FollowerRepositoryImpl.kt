package org.android.go.sopt.data.repositoryImpl

import org.android.go.sopt.data.model.main.Follower
import org.android.go.sopt.data.service.FollowerApiService
import org.android.go.sopt.domain.FollowerRepository
import org.android.go.sopt.data.model.response.ResponseFollowerDto
import retrofit2.Response
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val apiService: FollowerApiService
) : FollowerRepository {
    override suspend fun getUserList(): Result<List<Follower>> =
       runCatching {
           apiService.getFollowerList().convertToFollower()
       }
}