package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triply.barrierfreetrip.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    private val item = arrayListOf<String>()
    fun setDataList(list: List<String>) {
        item.clear()
        item.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_img, parent, false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(item.getOrNull(position))
    }

    inner class PagerViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {

        private val imageView = itemView.findViewById<ImageView>(R.id.iv_imgs)

        init {
            imageView.clipToOutline = true
        }

        fun bind(img: String?) {
            Glide.with(imageView.context)
                .load(img)
                .into(imageView)
        }
    }
}