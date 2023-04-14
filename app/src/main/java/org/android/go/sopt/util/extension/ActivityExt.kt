package org.android.go.sopt.util.extension

import android.app.Activity
import android.view.View

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}