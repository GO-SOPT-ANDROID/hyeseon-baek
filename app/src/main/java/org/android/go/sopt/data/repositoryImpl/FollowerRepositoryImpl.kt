package org.android.go.sopt.data.repositoryImpl

import org.android.go.sopt.data.datasource.FollowerDataSource
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource
) : FollowerRepository {
    override suspend fun getUserList(): Result<List<Follower>> =
       runCatching {
           followerDataSource.getFollowerList().convertToFollower()
       }
}