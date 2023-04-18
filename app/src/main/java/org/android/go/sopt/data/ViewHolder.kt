package org.android.go.sopt.data

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import org.android.go.sopt.databinding.HomeItemHeaderBinding
import org.android.go.sopt.databinding.HomeItemRepoBinding

sealed class HomeViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    data class HeaderViewHolder(
        private val binding: HomeItemHeaderBinding
    ) : HomeViewHolder(binding) {
        fun bind(item: HomeItem.Header) {
            binding.itemRecyclerHeader.text = item.header
        }
    }

    data class RepoViewHolder(
        private val binding: HomeItemRepoBinding
    ) : HomeViewHolder(binding) {
        fun bind(item: HomeItem.Repo) {
            binding.homeRepoName.text = item.name
            binding.homeRepoAuthor.text = item.author
//            binding.itemRecyclerUserimg = item.image
        }
    }

}