package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
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
    private var onLikeClickListener: OnLikeClickListener? = null

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
            setLikeClickListener {
                onLikeClickListener?.onLikeClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
        holder.bind(infoList[position], isLikeVisible)
    }
    override fun getItemCount() = infoList.size

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.onItemClickListener = itemClickListener
    }

    fun setOnLikeClickListener(likeClickListener: OnLikeClickListener) {
        this.onLikeClickListener = likeClickListener
    }

    private var isLikeVisible = true
    fun setLikeVisibility(visibility: Boolean) {
        isLikeVisible = visibility
    }
}

class SquareViewHolder(
    private val binding : ItemInfoSquareBinding
) : RecyclerView.ViewHolder(binding.root) {
    private var itemClickListener: OnItemClickListener? = null
    private var likeClickListener: OnClickListener? = null

    init {
        binding.ivPlaceImage.clipToOutline = true
        binding.root.setOnClickListener {
            itemClickListener?.onItemClick(adapterPosition)
        }
        binding.tbSquareLike.setOnClickListener {
            likeClickListener?.onClick(it)
        }
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    fun setLikeClickListener(likeClickListener: OnClickListener) {
        this.likeClickListener = likeClickListener
    }

    fun bind(item: InfoSquareDto, isLikeVisible: Boolean) {
        binding.squareItem = item
        binding.tbSquareLike.visibility = if (isLikeVisible) View.VISIBLE else View.GONE
        binding.tvSquareAddress.text = item.addr.substring(0, 14)
        Glide.with(binding.root.context)
            .load(item.firstimg)
            .centerCrop()
            .into(binding.ivPlaceImage)
    }
}