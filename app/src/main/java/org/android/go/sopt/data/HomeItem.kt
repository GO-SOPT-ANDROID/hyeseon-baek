package org.android.go.sopt.data

import androidx.annotation.DrawableRes

sealed class HomeItem {
    data class Header(
        val header: String
    ) : HomeItem()

    data class Repo(
        @DrawableRes val image: Int,
        val name: String,
        val author: String
    ) : HomeItem()
}