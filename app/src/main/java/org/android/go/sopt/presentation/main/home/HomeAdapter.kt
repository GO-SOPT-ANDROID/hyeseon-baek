package org.android.go.sopt.presentation.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.databinding.HomeItemFollowerBinding

class HomeAdapter() : ListAdapter<Follower, HomeAdapter.FollowerViewHolder>(diffUtil) {

    class FollowerViewHolder(
        private val binding: HomeItemFollowerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Follower) {
            binding.follower = data
            Log.e("binding", data.toString())
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            HomeItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Follower>() {
            override fun areItemsTheSame(
                oldItem: Follower,
                newItem: Follower
            ): Boolean {
                return newItem.email == oldItem.email
            }

            override fun areContentsTheSame(
                oldItem: Follower,
                newItem: Follower
            ): Boolean {
                return newItem == oldItem
            }
        }
    }
}