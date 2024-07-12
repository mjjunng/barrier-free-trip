package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triply.barrierfreetrip.R
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.databinding.ItemInfoSquareBinding

class InfoSquareAdapter : RecyclerView.Adapter<SquareViewHolder>() {
    private lateinit var binding : ItemInfoSquareBinding
    private val infoList = arrayListOf<InfoSquareDto>()
    private var onItemClickListener: OnItemClickListener? = null

    fun setDataList(infoList: List<InfoSquareDto>) {
        this.infoList.clear()
        this.infoList.addAll(infoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SquareViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_info_square, parent, false
        )
        return SquareViewHolder(binding).apply {
            setItemClickListener(onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
        holder.bind(infoList[position])
    }
    override fun getItemCount() = infoList.size

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.onItemClickListener = itemClickListener
    }
}

class SquareViewHolder(
    private val binding : ItemInfoSquareBinding
) : RecyclerView.ViewHolder(binding.root) {
    private var itemClickListener: OnItemClickListener? = null

    init {
        binding.ivPlaceImage.clipToOutline = true
        binding.root.setOnClickListener {
            itemClickListener?.onItemClick(adapterPosition)
        }
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    fun bind(item : InfoSquareDto) {
        binding.squareItem = item
        Glide.with(binding.root.context)
            .load(item.firstimg)
            .centerCrop()
            .into(binding.ivPlaceImage)
    }
}