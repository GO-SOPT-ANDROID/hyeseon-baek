package org.android.go.sopt.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.model.main.HomeItem
import org.android.go.sopt.databinding.HomeItemHeaderBinding
import org.android.go.sopt.databinding.HomeItemRepoBinding

class HomeAdapter(context: Context) : ListAdapter<HomeItem.Repo, RecyclerView.ViewHolder>(diffUtil) {

    private val inflater by lazy {LayoutInflater.from(context)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMultiViewHolder {
        return when (viewType) {
            HEADER_VIEW_TYPE ->
                HomeMultiViewHolder.HeaderViewHolder(binding = HomeItemHeaderBinding.inflate
                    ( inflater,
                    parent,
                    false))
            REPO_VIEW_TYPE ->
                HomeMultiViewHolder.RepoViewHolder(binding = HomeItemRepoBinding.inflate
                    ( inflater,
                    parent,
                    false))
            else -> { throw Exception("type error : not found")}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeMultiViewHolder.RepoViewHolder ->
                holder.bind(getItem(position) as HomeItem.Repo)
            else -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
             0 -> HEADER_VIEW_TYPE
            else -> REPO_VIEW_TYPE
        }
    }

    companion object {
        const val HEADER_VIEW_TYPE = 0
        const val REPO_VIEW_TYPE = 1
        val diffUtil = object:DiffUtil.ItemCallback<HomeItem.Repo>(){
            override fun areItemsTheSame(oldItem: HomeItem.Repo, newItem: HomeItem.Repo): Boolean {
                return oldItem.name == newItem.name && oldItem.author == newItem.author
            }

            override fun areContentsTheSame(oldItem: HomeItem.Repo, newItem: HomeItem.Repo): Boolean {
                return oldItem == newItem && oldItem == newItem
            }
        }
    }
}