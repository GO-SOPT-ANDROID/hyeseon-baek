package org.android.go.sopt.presentation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.main.User
import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.domain.AuthRepository
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
@HiltViewModel
class LoginViewModel @Inject constructor(private val apiAuthRepository: AuthRepository): ViewModel(){

    val _id = MutableLiveData("")
    val id: String
        get() = requireNotNull(_id.value).trim()

    val _password = MutableLiveData("")
    val password: String
        get() = requireNotNull(_password.value).trim()

    fun signIn(requestSignInDto: RequestSignInDto) = viewModelScope.launch {
        val response = apiAuthRepository.postSignIn(requestSignInDto)
        if (response.isSuccessful) {
            //TODO: livedata로 state 적용해보기
            Log.e("signin", "success")
        } else {
            Log.e("signin", "fail")
        }
    }

    fun getUser(): User {
        return User(
            id = id,
            pw = password
        )
    }
}