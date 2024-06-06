package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.triply.barrierfreetrip.data.ConvenienceInfoDTO
import com.triply.barrierfreetrip.databinding.ItemConvenienceInfoBinding

class ConvenienceInfoAdapter: RecyclerView.Adapter<ConvenienceInfoViewHolder>() {
    private val infoList: MutableList<ConvenienceInfoDTO> = mutableListOf()
    fun setInfoList(list: List<ConvenienceInfoDTO>) {
        infoList.clear()
        infoList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvenienceInfoViewHolder {
        return ConvenienceInfoViewHolder(ItemConvenienceInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = infoList.size

    override fun onBindViewHolder(holder: ConvenienceInfoViewHolder, position: Int) {
        holder.bind(infoList.getOrNull(position)?.subject, infoList.getOrNull(position)?.content)
    }
}

class ConvenienceInfoViewHolder(val binding: ItemConvenienceInfoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subject: String?, content: String?) {
        binding.tvConvenienceInfoSubject.text = subject
        binding.tvConvenienceInfoContent.text = content
    }
}