package org.android.go.sopt.presentation.join

import androidx.lifecycle.ViewModel

class JoinViewModel : ViewModel() {

    fun isValid(id: String? , pw: String?): Boolean{
        return !id.isNullOrBlank() && id.length in 6..10 && !pw.isNullOrBlank() && pw.length in 8..12
    }


}