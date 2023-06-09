package org.android.go.sopt.util.extension

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent.getParcelized(key: String): T? = when {
    SDK_INT >= TIRAMISU -> getParcelableExtra(key, T::class.java)
    else -> getParcelableExtra(key) as? T
}