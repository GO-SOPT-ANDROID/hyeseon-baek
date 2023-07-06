package org.android.go.sopt.presentation.signup

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.android.go.sopt.R
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
class SignupViewModel @Inject constructor(private val apiAuthRepository: AuthRepository) :
    ViewModel() {
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


    val _isIdValid = MutableLiveData<Boolean>()
    val _isPasswordValid = MutableLiveData<Boolean>()
    val _isNameValid = MutableLiveData<Boolean>()
    val _isSpecialityValid = MutableLiveData<Boolean>()


    val isValidAndFilled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_isIdValid) { value = validateFields() }
        addSource(_isPasswordValid) { value = validateFields() }
        addSource(_isNameValid) { value = validateFields() }
        addSource(_isSpecialityValid) { value = validateFields() }
    }

    val idErrorMessage: LiveData<Int> = Transformations.map(_isIdValid) { isValid ->
        if (!isValid) R.string.invalid_id_error_message else 0
    }

    val passwordErrorMessage: LiveData<Int> = Transformations.map(_isPasswordValid) { isValid ->
        if (!isValid) R.string.invalid_password_error_message else 0
    }

    val nameErrorMessage: LiveData<Int> = Transformations.map(_isNameValid) { isValid ->
        if (!isValid) R.string.invalid_name_error_message else 0
    }

    val specialityErrorMessage: LiveData<Int> = Transformations.map(_isSpecialityValid) { isValid ->
        if (!isValid) R.string.invalid_speciality_error_message else 0
    }

    private fun validateFields(): Boolean {
        return _isIdValid.value == true && _isPasswordValid.value == true && _isNameValid.value == true && _isSpecialityValid.value == true
    }

    fun validateId(text: String) {
        _isIdValid.value = text.trim().matches(Regex(ID_REGEX))
    }

    fun validatePassword(text: String) {
        _isPasswordValid.value = text.trim().matches(Regex(PW_REGEX))
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
            id = id, pw = password, name = name, special = speciality
        )
    }

    companion object {
        const val ID_REGEX =
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{${ID_MIN_LENGTH},${ID_MAX_LENGTH}}$"
        const val PW_REGEX =
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{${PW_MIN_LENGTH},${PW_MAX_LENGTH}}$"
    }
}