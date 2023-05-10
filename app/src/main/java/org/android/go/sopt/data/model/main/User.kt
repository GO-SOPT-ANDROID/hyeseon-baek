package org.android.go.sopt.data.model.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val pw: String = "",
    val name: String = "",
    val special: String = ""
):Parcelable
