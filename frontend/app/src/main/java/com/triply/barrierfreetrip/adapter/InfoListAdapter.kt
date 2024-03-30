package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.triply.barrierfreetrip.R
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.databinding.ItemInfoListBinding

// 화면에 표시만 하면 되는 경우 사용하는 기본 Adapter
open class InfoListAdapter(var infoList : ArrayList<InfoListDto>) : RecyclerView.Adapter<ListViewHolder>() {
    private lateinit var binding : ItemInfoListBinding
    //var infoList = ArrayList<InfoListDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_info_list, parent, false
        )
        return ListViewHolder(binding)
    }

    override fun getItemCount() = infoList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(infoList[position])
    }
}

// 충전기처럼 다른 페이지로 넘어가야 하는 경우 사용하는 Adapter
class InfoListClickAdapter(infoList: ArrayList<InfoListDto>) : InfoListAdapter(infoList) {
    private lateinit var itemClickListener: InfoListClickAdapter.OnItemClickListener

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        //holder.bind(infoList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}

class ListViewHolder(
    private val binding : ItemInfoListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : InfoListDto) {
        binding.listItem = item
        //binding.tbListLike.isChecked = item.like
    }
}