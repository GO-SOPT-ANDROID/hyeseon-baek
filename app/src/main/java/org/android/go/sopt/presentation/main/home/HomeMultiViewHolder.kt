package org.android.go.sopt.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import org.android.go.sopt.data.model.main.HomeItem
import org.android.go.sopt.databinding.HomeItemHeaderBinding
import org.android.go.sopt.databinding.HomeItemRepoBinding

sealed class HomeMultiViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    data class HeaderViewHolder(
        private val binding: HomeItemHeaderBinding
    ) : HomeMultiViewHolder(binding) {
    }

    data class RepoViewHolder(
        private val binding: HomeItemRepoBinding
    ) : HomeMultiViewHolder(binding) {
        fun bind(item: HomeItem.Repo) {
            with(binding){
                homeRepoName.text = item.name
                homeRepoAuthor.text = item.author
                homeRepoImg.setImageResource(item.image)
            }
        }
    }

}