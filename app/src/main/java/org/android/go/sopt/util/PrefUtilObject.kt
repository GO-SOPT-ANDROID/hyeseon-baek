package org.android.go.sopt.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


object PrefUtilObject {
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



