package org.android.go.sopt.utiil

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.edit


object UtilObject {
    private val MY_ACCOUNT: String = "account"
    fun setID(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        prefs.edit{
            putString("savedID", input)
        }
    }

    fun getID(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("savedID", "").toString()
    }

    fun getPW(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("savedPW", "").toString()
    }

    fun setPW(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        prefs.edit{
            putString("savedPW", input)
        }
    }
}


