package org.android.go.sopt.presentation.signup

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.AuthState
import org.android.go.sopt.data.model.main.User
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.domain.AuthRepository
import org.android.go.sopt.util.Constants.ID_MAX_LENGTH
import org.android.go.sopt.util.Constants.ID_MIN_LENGTH
import org.android.go.sopt.util.Constants.PW_MAX_LENGTH
import org.android.go.sopt.util.Constants.PW_MIN_LENGTH
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class SignupViewModel @Inject constructor(private val apiAuthRepository: AuthRepository): ViewModel()  {
    val _id = MutableLiveData("")
    val id: String
        get() = requireNotNull(_id.value).trim()

    val _password = MutableLiveData("")
    val password: String
        get() = requireNotNull(_password.value).trim()

    val _name = MutableLiveData("")
    val name: String
        get() = _name.value?.trim() ?: ""

    val _speciality = MutableLiveData("")
    val speciality: String
        get() = _speciality.value?.trim() ?: ""

    private val _signupState = MutableLiveData<AuthState>()
    val signupState: LiveData<AuthState>
        get() = _signupState


    private val _isIdValid = MutableLiveData<Boolean>()
    private val _isPasswordValid = MutableLiveData<Boolean>()
    private val _isNameValid = MutableLiveData<Boolean>()
    private val _isSpecialityValid = MutableLiveData<Boolean>()


    val isValidAndFilled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_isIdValid) { value = validateFields() }
        addSource(_isPasswordValid) { value = validateFields() }
        addSource(_isNameValid) { value = validateFields() }
        addSource(_isSpecialityValid) { value = validateFields() }
    }

    init {
        _isIdValid.value = false
        _isPasswordValid.value = false
        _isNameValid.value = false
        _isSpecialityValid.value = false
    }
    private fun validateFields(): Boolean {
        return _isIdValid.value == true
                && _isPasswordValid.value == true
                && _isNameValid.value == true
                && _isSpecialityValid.value == true
    }

    fun validateId(text: String) {
        _isIdValid.value = text.trim().length in ID_MIN_LENGTH..ID_MAX_LENGTH
    }

    fun validatePassword(text: String) {
        _isPasswordValid.value = text.trim().length in PW_MIN_LENGTH..PW_MAX_LENGTH
    }

    fun validateName(text: String) {
        _isNameValid.value = text.trim().isNotBlank()
    }

    fun validateSpeciality(text: String) {
        _isSpecialityValid.value = text.trim().isNotBlank()
    }

    fun signUp(requestSignUpDto: RequestSignUpDto) = viewModelScope.launch {
        val response = apiAuthRepository.postSignUp(requestSignUpDto)
        if (response.isSuccessful) {
            _signupState.value = AuthState.SUCCESS
        } else {
            _signupState.value = AuthState.FAIL
        }
    }
    fun getUser(): User {
        return User(
            id = id,
            pw = password,
            name = name,
            special = speciality
        )
    }
}