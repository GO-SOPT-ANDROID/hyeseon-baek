package org.android.go.sopt.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.AuthState
import org.android.go.sopt.domain.model.User
import org.android.go.sopt.data.model.remote.request.RequestSignInDto
import org.android.go.sopt.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiAuthRepository: AuthRepository): ViewModel(){

    val _id = MutableLiveData("")
    val id: String
        get() = requireNotNull(_id.value).trim()

    val _password = MutableLiveData("")
    val password: String
        get() = requireNotNull(_password.value).trim()

    private val _signinState = MutableLiveData<AuthState>()
    val signinState: LiveData<AuthState>
        get() = _signinState

    fun signIn(requestSignInDto: RequestSignInDto) = viewModelScope.launch {
        val response = apiAuthRepository.postSignIn(requestSignInDto)
        if (response.isSuccessful) {
            _signinState.value = AuthState.SUCCESS
        } else {
            _signinState.value = AuthState.FAIL
        }
    }

    fun getUser(): User {
        return User(
            id = id,
            pw = password
        )
    }
}