package org.android.go.sopt.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    val _id = MutableLiveData("")
    val id: String
        get() = requireNotNull(_id.value).trim()

    val _password = MutableLiveData("")
    val password: String
        get() = requireNotNull(_password.value).trim()
}