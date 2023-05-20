package org.android.go.sopt.domain

import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignInDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface AuthRepository {
    suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto>
    suspend fun postSignIn(requestSignInDto: RequestSignInDto): Response<ResponseSignInDto>
}