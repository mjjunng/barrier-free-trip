package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triply.barrierfreetrip.R

class ViewPagerAdapter (imgs: List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>(){
    var item = imgs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        Glide.with(holder.imgs.context)
            .load(item[position])
            .into(holder.imgs)
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_img, parent, false)){
        val imgs = itemView.findViewById<ImageView>(R.id.iv_imgs)
    }
}