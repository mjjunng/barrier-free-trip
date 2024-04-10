package com.triply.barrierfreetrip.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.triply.barrierfreetrip.R
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.data.SearchRsltItem
import com.triply.barrierfreetrip.databinding.ItemInfoListBinding
import com.triply.barrierfreetrip.databinding.ItemInfoSquareBinding

class SearchMultiviewAdapter (var searchList : ArrayList<SearchRsltItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: SearchMultiviewAdapter.OnItemClickListener

    override fun getItemViewType(position: Int): Int {
        return searchList[position].type
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                var binding : ItemInfoSquareBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_info_square, parent, false
                )
                return MultiViewHolder1(binding)
            }
            else -> {
                var binding : ItemInfoListBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_info_list, parent, false
                )
                return MultiViewHolder2(binding)
            }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(searchList[position].type) {
            1 -> {
                var item = InfoSquareDto(searchList[position].addr, searchList[position].id.toString(),
                    "1", searchList[position].firstImage, searchList[position].like,
                    searchList[position].rating, searchList[position].tel, searchList[position].title)

                (holder as MultiViewHolder1).bind(item)
                //holder.setIsRecyclable(false)
                holder.itemView.setOnClickListener {
                    itemClickListener.onClick(it, position)
                }
            }
            2, 3, 4 -> {
                var item = InfoListDto(searchList[position].id, searchList[position].addr,
                                        searchList[position].like, searchList[position].tel,
                                        searchList[position].title)
                (holder as MultiViewHolder2).bind(item)
                holder.setIsRecyclable(false)
            }
//            else -> {
//                (holder as MultiViewHolder3).bind(datas[position])
//                holder.setIsRecyclable(false)
//            }
        }

    }

    override fun getItemCount() = searchList.size

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}

class MultiViewHolder1(
    private val binding : ItemInfoSquareBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : InfoSquareDto) {
        binding.squareItem = item
    }
}

class MultiViewHolder2(
    private val binding : ItemInfoListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : InfoListDto) {
        binding.listItem = item
        //binding.tbListLike.isChecked = item.like
    }
}