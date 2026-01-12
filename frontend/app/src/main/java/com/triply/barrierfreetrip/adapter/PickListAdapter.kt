package com.triply.barrierfreetrip.adapter

import android.icu.text.IDNA
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triply.barrierfreetrip.data.FacilityViewType
import com.triply.barrierfreetrip.data.InfoSquareListDto.InfoSquareItemDto
import com.triply.barrierfreetrip.data.InfoListDto.InfoListItemDto
import com.triply.barrierfreetrip.data.PickStatusModel
import com.triply.barrierfreetrip.data.PickType
import com.triply.barrierfreetrip.databinding.FragmentPickListBinding
import com.triply.barrierfreetrip.databinding.ItemInfoListBinding
import com.triply.barrierfreetrip.databinding.ItemInfoSquareBinding
import com.triply.barrierfreetrip.util.getFacilityViewType

class PickListAdapter(
    private val pageType: PickType,
    private val onItemClick: (PickStatusModel) -> Unit,
    private val onLikeClick: (PickStatusModel) -> Unit
) : ListAdapter<PickStatusModel, RecyclerView.ViewHolder>(
    PickListDiffCalback()
) {
    private val SQUARE_TYPE = 1
    private val LIST_TYPE = 2

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SQUARE_TYPE -> {
                val binding = ItemInfoSquareBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                PickSquareViewHolder(binding, onItemClick, onLikeClick)
            }
            LIST_TYPE -> {
                val binding = ItemInfoListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                PickListViewHolder(binding, onItemClick, onLikeClick)
            }
            else -> throw IllegalArgumentException("Unknown view type")

        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is PickSquareViewHolder ->
                holder.bind(getItem(position) as InfoSquareItemDto)
            is PickListViewHolder ->
                holder.bind(getItem(position) as InfoListItemDto)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getFacilityViewType(pageType) == FacilityViewType.SQUARE_TYPE)
            SQUARE_TYPE
        else LIST_TYPE
    }

    class PickListDiffCalback: DiffUtil.ItemCallback<PickStatusModel>() {
        override fun areItemsTheSame(oldItem: PickStatusModel,
                                     newItem: PickStatusModel
        ): Boolean {
            if (oldItem::class != newItem::class)
                return false
            return oldItem.uniqueId == newItem.uniqueId
        }

        override fun areContentsTheSame(oldItem: PickStatusModel,
                                        newItem: PickStatusModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    class PickListViewHolder(
        private val binding: ItemInfoListBinding,
        private val onItemClick: (PickStatusModel) -> Unit,
        private val onLikeClick: (PickStatusModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InfoListItemDto) {
            binding.run {
                listItem = item
                root.setOnClickListener {
                    onItemClick(item)
                }
                tbListLike.setOnClickListener {
                    onLikeClick(item)
                }

                tvListLocation.text = item.addr.substring(0, 14)
            }

        }
    }

    class PickSquareViewHolder(
        private val binding: ItemInfoSquareBinding,
        private val onItemClick: (PickStatusModel) -> Unit,
        private val onLikeClick: (PickStatusModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivPlaceImage.clipToOutline = true
        }
        fun bind(item: InfoSquareItemDto) {
            binding.run {
                squareItem = item
                root.setOnClickListener { onItemClick(item) }
                tbSquareLike.setOnClickListener { onLikeClick(item) }

                tvSquareAddress.text = item.addr.take(14)
//                tvSquarePlaceName.text = item.title
//                tvSquareCallNumber.text = item.tel

                tbSquareLike.isChecked = item.like

                Glide.with(root.context)
                    .load(item.firstimg)
                    .centerCrop()
                    .into(ivPlaceImage)
            }
        }
    }
}