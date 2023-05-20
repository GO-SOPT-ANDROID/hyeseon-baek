package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignInDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignApiService {
    @POST("sign-up")
    suspend fun signup(
        @Body requestSignUpDto: RequestSignUpDto
    ): Response<ResponseSignUpDto>

    @POST("sign-in")
    suspend fun signin(
        @Body requestSignInDto: RequestSignInDto
    ): Response<ResponseSignInDto>
}