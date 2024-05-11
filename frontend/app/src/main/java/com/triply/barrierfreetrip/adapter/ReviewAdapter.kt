package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.triply.barrierfreetrip.data.ReviewListDTO
import com.triply.barrierfreetrip.databinding.ItemReviewContentBinding
import com.triply.barrierfreetrip.databinding.ItemReviewMoreBinding
import kotlin.math.roundToInt

class ReviewAdapter : RecyclerView.Adapter<ReviewViewHolder>() {
    private val VIEWTYPE_REVIEW = 0
    private val VIEWTYPE_MORE_LOAD = 1

    private val reviewList = mutableListOf<ReviewListDTO.ReviewDTO>()
    private val preReviewList = mutableListOf<ReviewListDTO.ReviewDTO>()
    private var isFullyLoaded = false

    fun setDataList(list: List<ReviewListDTO.ReviewDTO>) {
        if (list.size <= 3) isFullyLoaded = true

        reviewList.clear()
        preReviewList.clear()
        reviewList.addAll(list)
        preReviewList.addAll(
            list.take(3).toMutableList().apply {
                add(
                    ReviewListDTO.ReviewDTO(
                        "",
                        0.toDouble(),
                        "",
                        ""
                    )
                )
            }
        )
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {

        return when (viewType) {
            VIEWTYPE_REVIEW -> ReviewContentViewHolder(
                ItemReviewContentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ReviewMoreViewHolder(
                ItemReviewMoreBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            ).apply {
                setOnButtonClickListener {
                    isFullyLoaded = true
                    notifyItemRangeInserted(
                        preReviewList.size,
                        reviewList.size - preReviewList.size + 1
                    )
                }
            }
        }
    }

    override fun getItemCount() = if (isFullyLoaded) reviewList.size else preReviewList.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        if (isFullyLoaded) {
            if (holder is ReviewContentViewHolder) holder.bind(reviewList.getOrNull(position))
        } else {
            if (holder is ReviewContentViewHolder) holder.bind(preReviewList.getOrNull(position))
        }

    }

    override fun getItemViewType(position: Int): Int {
        // 모든 리뷰 로드 시 REVIEW 뷰타입만 inflate
        // 3개(이하) 리뷰만 로드 시 마지막 아이템을 제외한 나머지 데이터에 대해 REVIEW 뷰타입 inflate
        if (isFullyLoaded) return VIEWTYPE_REVIEW

        if (position == preReviewList.lastIndex) return VIEWTYPE_MORE_LOAD
        return VIEWTYPE_REVIEW
    }
}

class ReviewContentViewHolder(private val binding: ItemReviewContentBinding) : ReviewViewHolder(binding.root) {

    fun bind(data: ReviewListDTO.ReviewDTO?) {
        data ?: return
        binding.apply {
            rtbarReviewScore.setRating(data.rating.roundToInt())
            tvReviewContent.text = data.content
            tvReviewerName.text = data.nickname
        }
    }
}

class ReviewMoreViewHolder(binding: ItemReviewMoreBinding) : ReviewViewHolder(binding.root) {
    private var btnClickListner: OnClickListener? = null

    fun setOnButtonClickListener(listener: OnClickListener) {
        this.btnClickListner = listener
    }

    init {
        binding.btnLoadMore.setOnClickListener {
            btnClickListner?.onClick(it)
        }
    }
}

abstract class ReviewViewHolder(view: View) : ViewHolder(view)