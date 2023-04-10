package org.android.go.sopt.presentation.join

import androidx.lifecycle.ViewModel
import org.android.go.sopt.utiil.Constants.ID_MAX_LENGTH
import org.android.go.sopt.utiil.Constants.ID_MIN_LENGTH
import org.android.go.sopt.utiil.Constants.PW_MAX_LENGTH
import org.android.go.sopt.utiil.Constants.PW_MIN_LENGTH

class JoinViewModel : ViewModel() {

    fun isValid(id: String? , pw: String?): Boolean{
        return !id.isNullOrBlank() && id.length in ID_MIN_LENGTH..ID_MAX_LENGTH && !pw.isNullOrBlank() && pw.length in PW_MIN_LENGTH..PW_MAX_LENGTH
    }


}