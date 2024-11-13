package com.triply.barrierfreetrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.triply.barrierfreetrip.R
import com.triply.barrierfreetrip.databinding.ItemSpinnerTvBinding

class BFTSpinnerAdapter <T: Any>(context: Context, @LayoutRes resource: Int, val list: List<T>): ArrayAdapter<T>(context, resource, list) {
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvSpinnerContent.background = ContextCompat.getDrawable(parent.context, when {
            list.size == 1 -> R.drawable.bg_spinner_inside_single
            position == 0 -> R.drawable.bg_spinner_inside_first
            position == list.lastIndex -> R.drawable.bg_spinner_inside_last
            else -> R.drawable.bg_spinner_inside
        })
        binding.tvSpinnerContent.text = list.getOrNull(position).toString()
        binding.tvSpinnerContent.clipToOutline = true
        return binding.tvSpinnerContent
    }
}