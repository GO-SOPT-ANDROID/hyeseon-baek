package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("sign-up")
    fun login(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}