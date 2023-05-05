package org.android.go.sopt.presentation.main.home

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.data.HomeItem

class HomeViewModel : ViewModel() {
    val fakeRepoList = listOf(
        HomeItem.Repo(
            R.drawable.userpic,
            "git1",
            "author1"),
        HomeItem.Repo(
            R.drawable.userpic,
            "git2",
            "author2"),
        HomeItem.Repo(
            R.drawable.userpic,
            "git3",
            "author3"),
        HomeItem.Repo(
            R.drawable.userpic,
            "git4",
            "author4"),
        HomeItem.Repo(
            R.drawable.userpic,
            "git5",
            "author5"),
        HomeItem.Repo(
            R.drawable.userpic,
            "git6",
            "author6"),
        HomeItem.Repo(
            R.drawable.userpic,
            "git7",
            "author7")
    )
}