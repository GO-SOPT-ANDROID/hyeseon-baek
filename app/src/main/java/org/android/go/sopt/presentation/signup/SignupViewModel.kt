package org.android.go.sopt.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.model.main.User
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

    val _isValidAndFilled = MutableLiveData<Boolean>()

    fun getIsValidAndFilled(): LiveData<Boolean> {
        _isValidAndFilled.value = isValidAndFilled()
        return _isValidAndFilled
    }

    fun isValidAndFilled(): Boolean{
        return id.length in ID_MIN_LENGTH..ID_MAX_LENGTH && password.length in PW_MIN_LENGTH..PW_MAX_LENGTH
                && name.isNotBlank() && speciality.isNotBlank()
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