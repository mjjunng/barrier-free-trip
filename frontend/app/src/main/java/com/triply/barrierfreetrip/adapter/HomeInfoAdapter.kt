package com.triply.barrierfreetrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.databinding.ItemHomefragmentMainMenuBinding
import com.triply.barrierfreetrip.databinding.ItemHomefragmentTitleBinding
import com.triply.barrierfreetrip.databinding.ItemInfoListBinding
import com.triply.barrierfreetrip.databinding.ItemInfoSquareBinding

class HomeInfoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val _infoList = arrayListOf<HomeInfoDTO>()
    val infoList: List<HomeInfoDTO>
        get() = _infoList

    fun setDataList(dataList: List<HomeInfoDTO>) {
        _infoList.clear()
        _infoList.addAll(dataList)
        notifyDataSetChanged()
    }

    private var onMenuClickListener: HomeMenuViewHolder.OnClickListener? = null
    private var onInfoSquareClickListener: OnItemClickListener? = null
    private var onInfoListClickListener: OnItemClickListener? = null

    fun setOnClickListener(onMenuClickListener: HomeMenuViewHolder.OnClickListener, onInfoSquareClickListener: OnItemClickListener, onInfoListClickListener: OnItemClickListener) {
        this.onMenuClickListener = onMenuClickListener
        this.onInfoSquareClickListener = onInfoSquareClickListener
        this.onInfoListClickListener = onInfoListClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEWTYPE_MAIN_MENU -> {
                HomeMenuViewHolder(ItemHomefragmentMainMenuBinding.inflate(layoutInflater, parent, false))
                    .apply {
                        setOnClickListener(onMenuClickListener)
                    }
            }
            VIEWTYPE_TITLE -> HomeTitleViewHolder(ItemHomefragmentTitleBinding.inflate(layoutInflater, parent, false))
            VIEWTYPE_NEARBY_STAY -> SquareViewHolder(ItemInfoSquareBinding.inflate(layoutInflater, parent, false))
                .apply {
                    setOnItemClickListener(onInfoSquareClickListener)
                    setLikeVisibility(false)
                }
            else -> ListViewHolder(ItemInfoListBinding.inflate(layoutInflater, parent, false)).apply {
                setOnItemClickListener(onInfoListClickListener)
                setLikeVisibility(false)
                setShowMapVisibility(false)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (_infoList.getOrNull(position)) {
            is HomeInfoDTO.Menu -> VIEWTYPE_MAIN_MENU
            is HomeInfoDTO.InfoSquare -> VIEWTYPE_NEARBY_STAY
            is HomeInfoDTO.InfoList -> VIEWTYPE_NEARBY_CHARGER
            else -> VIEWTYPE_TITLE
        }
    }

    override fun getItemCount() = _infoList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            holder is HomeMenuViewHolder -> {}
            holder is HomeTitleViewHolder -> {
                holder.bind(
                    title = (_infoList.getOrNull(position) as HomeInfoDTO.Title).title
                )
            }
            holder is SquareViewHolder -> {
                holder.bind(
                    item = (_infoList.getOrNull(position) as HomeInfoDTO.InfoSquare).convertAdapterDataintoDTO()
                )
            }
            holder is ListViewHolder -> {
                holder.bind(
                    item = (_infoList.getOrNull(position) as HomeInfoDTO.InfoList).convertAdapterDataintoDTO()
                )
            }
        }
    }

    companion object {
        const val VIEWTYPE_MAIN_MENU = 0
        const val VIEWTYPE_TITLE = 1
        const val VIEWTYPE_NEARBY_STAY = 2
        const val VIEWTYPE_NEARBY_CHARGER = 3
    }

    sealed class HomeInfoDTO {
        object Menu: HomeInfoDTO()

        data class Title(
            val title: String
        ): HomeInfoDTO()

        data class InfoSquare(
            val addr: String,
            val contentId: String,
            val contentTypeId: String,
            val firstimg: String,
            val like: Boolean,
            val rating: String,
            val tel: String,
            val title: String
        ): HomeInfoDTO() {
            fun convertAdapterDataintoDTO(): InfoSquareDto {
                return InfoSquareDto(
                    addr = this.addr,
                    contentId = this.contentId,
                    contentTypeId = this.contentTypeId,
                    firstimg = this.firstimg,
                    like = this.like,
                    rating = this.rating,
                    tel = this.tel,
                    title = this.title
                )
            }
        }

        data class InfoList(
            val id: Int,
            val addr: String,
            val like: Boolean,
            val tel: String,
            val title: String
        ): HomeInfoDTO() {
            fun convertAdapterDataintoDTO(): InfoListDto {
                return InfoListDto(
                    id = this.id,
                    addr = this.addr,
                    like = this.like,
                    tel = this.tel,
                    title = this.title
                )
            }
        }
    }

}

class HomeMenuViewHolder(val binding: ItemHomefragmentMainMenuBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.btnHomeCaretrip.setOnClickListener { onClickListener?.onHomeCareTripClick() }
        binding.btnHomeCharge.setOnClickListener { onClickListener?.onHomeChargeClick() }
        binding.btnHomeDestination.setOnClickListener { onClickListener?.onHomeDestinationClick() }
        binding.btnHomeRental.setOnClickListener { onClickListener?.onHomeRentalClick() }
        binding.btnHomeRestaurant.setOnClickListener { onClickListener?.onHomeRestaurantClick() }
        binding.btnHomeStay.setOnClickListener { onClickListener?.onHomeStayClick() }
    }

    private var onClickListener: OnClickListener? = null
    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    interface OnClickListener {
        fun onHomeCareTripClick()
        fun onHomeChargeClick()
        fun onHomeDestinationClick()
        fun onHomeRentalClick()
        fun onHomeRestaurantClick()
        fun onHomeStayClick()
    }
}

class HomeTitleViewHolder(private val binding: ItemHomefragmentTitleBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(title: String) {
        binding.tvNearbyFcltTitle.text = title
    }

    init {
        binding.btnFcltMore.visibility = View.GONE
    }
}