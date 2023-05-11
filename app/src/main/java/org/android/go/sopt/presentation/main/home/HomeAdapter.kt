package org.android.go.sopt.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import org.android.go.sopt.data.model.main.Follower
import org.android.go.sopt.data.model.main.HomeItem
import org.android.go.sopt.data.model.response.ResponseFollowerDto
import org.android.go.sopt.data.model.response.ResponseSignInDto
import org.android.go.sopt.databinding.HomeItemFollowerBinding

class HomeAdapter() : ListAdapter<ResponseFollowerDto, HomeAdapter.FollowerViewHolder>(diffUtil) {

    class FollowerViewHolder(
        private val binding: HomeItemFollowerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseFollowerDto) {
            binding.follower = data.convertToFollower().first()
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = HomeItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    companion object {
        val diffUtil = object:DiffUtil.ItemCallback<ResponseFollowerDto>(){
            override fun areItemsTheSame(
                oldItem: ResponseFollowerDto,
                newItem: ResponseFollowerDto
            ): Boolean {
                return newItem.data == oldItem.data
            }

            override fun areContentsTheSame(
                oldItem: ResponseFollowerDto,
                newItem: ResponseFollowerDto
            ): Boolean {
                return newItem == oldItem
            }
        }
    }


}