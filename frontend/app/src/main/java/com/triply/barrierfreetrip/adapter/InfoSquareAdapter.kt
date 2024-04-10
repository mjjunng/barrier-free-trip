package com.triply.barrierfreetrip.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.triply.barrierfreetrip.R
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.databinding.ItemInfoSquareBinding

class InfoSquareAdapter(var infoList : ArrayList<InfoSquareDto>) : RecyclerView.Adapter<SquareViewHolder>() {
    private lateinit var binding : ItemInfoSquareBinding
    private lateinit var itemClickListener: InfoSquareAdapter.OnItemClickListener
    //var infoList = ArrayList<InfoSquareDto>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SquareViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_info_square, parent, false
        )
        return SquareViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
        holder.bind(infoList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }
    override fun getItemCount() = infoList.size

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}

class SquareViewHolder(
    private val binding : ItemInfoSquareBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : InfoSquareDto) {
        binding.squareItem = item
    }
}