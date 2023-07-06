package org.android.go.sopt.data.repositoryImpl

import org.android.go.sopt.data.model.remote.request.RequestSignInDto
import org.android.go.sopt.data.model.remote.request.RequestSignUpDto
import org.android.go.sopt.data.model.remote.response.ResponseSignInDto
import org.android.go.sopt.data.model.remote.response.ResponseSignUpDto
import org.android.go.sopt.data.service.SignApiService
import org.android.go.sopt.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: SignApiService
) : AuthRepository {
    override suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto> =
        apiService.signup(requestSignUpDto)

    override suspend fun postSignIn(requestSignInDto: RequestSignInDto): Response<ResponseSignInDto> =
        apiService.signin(requestSignInDto)
}