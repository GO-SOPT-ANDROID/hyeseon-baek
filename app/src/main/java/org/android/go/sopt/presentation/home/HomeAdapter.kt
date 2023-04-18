package org.android.go.sopt.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.R
import org.android.go.sopt.data.HomeItem
import org.android.go.sopt.data.HomeViewHolder
import org.android.go.sopt.databinding.HomeItemHeaderBinding
import org.android.go.sopt.databinding.HomeItemRepoBinding

class HomeAdapter(context: Context) : RecyclerView.Adapter<HomeViewHolder>() {

    private val inflater by lazy {LayoutInflater.from(context)}
    private val item: List<Any> = listOf(
        HomeItem.Repo(R.drawable.userpic, "git1","author1"),
        HomeItem.Repo(R.drawable.userpic, "git2","author2"),
        HomeItem.Repo(R.drawable.userpic, "git3","author3"),
        HomeItem.Repo(R.drawable.userpic, "git4","author4"),
        HomeItem.Repo(R.drawable.userpic, "git5","author5"),
        HomeItem.Repo(R.drawable.userpic, "git6","author6"),
        HomeItem.Repo(R.drawable.userpic, "git7","author7")

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return when (viewType) {
            HEADER_VIEW_TYPE ->
                HomeViewHolder.HeaderViewHolder(binding = HomeItemHeaderBinding.inflate
                    ( inflater,
                    parent,
                    false))
            REPO_VIEW_TYPE ->
                HomeViewHolder.RepoViewHolder(binding = HomeItemRepoBinding.inflate
                    ( inflater,
                    parent,
                    false))
            else -> { throw Exception("type error : not found")}
        }
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder.RepoViewHolder ->
                holder.bind(item[position-1] as HomeItem.Repo)
            else -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
             0 -> HEADER_VIEW_TYPE
            else -> REPO_VIEW_TYPE
        }
    }
    override fun getItemCount(): Int {
        return item.size + 1
    }

    companion object {
        const val HEADER_VIEW_TYPE = 0
        const val REPO_VIEW_TYPE = 1
    }
}