package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.response.ResponseFollowerDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import retrofit2.Response
import retrofit2.http.GET

interface FollowerApiService {
    @GET("api/users?page=2")
    suspend fun getFollowerList(
    ): Response<ResponseFollowerDto>
}