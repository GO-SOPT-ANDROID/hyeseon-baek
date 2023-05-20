package org.android.go.sopt.presentation.main.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.GalleryItemPagerBinding

class GalleryViewPageAdapter(
    _itemList: List<Int> = listOf()
): RecyclerView.Adapter<GalleryViewPageAdapter.GalleryViewHolder>() {
    lateinit var binding: GalleryItemPagerBinding
    private var itemList: List<Int> = _itemList
    class GalleryViewHolder(val binding : GalleryItemPagerBinding):RecyclerView.ViewHolder(binding.root){
        fun getBind(src:Int){
            binding.imgGallery.setImageResource(src)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = GalleryItemPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.getBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItemList(itemList:List<Int>){
        this.itemList = itemList
        notifyDataSetChanged()
    }
}