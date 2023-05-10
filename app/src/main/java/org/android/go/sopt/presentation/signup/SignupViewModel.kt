package org.android.go.sopt.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.model.main.User
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.util.Constants.ID_MAX_LENGTH
import org.android.go.sopt.util.Constants.ID_MIN_LENGTH
import org.android.go.sopt.util.Constants.PW_MAX_LENGTH
import org.android.go.sopt.util.Constants.PW_MIN_LENGTH

class SignupViewModel : ViewModel() {
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

    fun isValid(id: String? , pw: String?): Boolean{
        return !id.isNullOrBlank() && id.length in ID_MIN_LENGTH..ID_MAX_LENGTH && !pw.isNullOrBlank() && pw.length in PW_MIN_LENGTH..PW_MAX_LENGTH
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