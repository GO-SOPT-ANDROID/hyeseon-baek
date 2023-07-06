package org.android.go.sopt.domain.repository

import org.android.go.sopt.data.model.remote.request.RequestSignInDto
import org.android.go.sopt.data.model.remote.request.RequestSignUpDto
import org.android.go.sopt.data.model.remote.response.ResponseSignInDto
import org.android.go.sopt.data.model.remote.response.ResponseSignUpDto
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface AuthRepository {
    suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto>
    suspend fun postSignIn(requestSignInDto: RequestSignInDto): Response<ResponseSignInDto>
}