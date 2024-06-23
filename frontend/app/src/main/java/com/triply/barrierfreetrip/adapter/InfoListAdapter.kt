package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.databinding.ItemInfoListBinding

// 화면에 표시만 하면 되는 경우 사용하는 기본 Adapter
open class InfoListAdapter : RecyclerView.Adapter<ListViewHolder>() {
    private val infoList: ArrayList<InfoListDto> = arrayListOf()
    fun setInfoList(listDto: List<InfoListDto>) {
        infoList.clear()
        infoList.addAll(listDto)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemInfoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ListViewHolder(binding).apply {
            this.setOnButtonClickListener {
                onItemClickListener?.onItemClick(adapterPosition)
            }
        }
        return viewHolder
    }

    override fun getItemCount() = infoList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(infoList[position])
    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }
}

// 충전기처럼 다른 페이지로 넘어가야 하는 경우 사용하는 Adapter
class InfoListClickAdapter(infoList: ArrayList<InfoListDto>) : InfoListAdapter() {

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
    private var itemClickListener: View.OnClickListener? = null

    fun setOnButtonClickListener(listener: View.OnClickListener) {
        this.itemClickListener = listener
    }

    fun bind(item : InfoListDto) {
        binding.listItem = item
        //binding.tbListLike.isChecked = item.like
    }

    init {
        binding.root.setOnClickListener {
            itemClickListener?.onClick(it)
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}