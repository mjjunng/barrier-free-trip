package com.triply.barrierfreetrip

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.triply.barrierfreetrip.MainActivity.Companion.CONTENT_ID
import com.triply.barrierfreetrip.adapter.ConvenienceInfoAdapter
import com.triply.barrierfreetrip.adapter.ViewPagerAdapter
import com.triply.barrierfreetrip.adapter.decoration.ConvenienceInfoViewHolderDecoration
import com.triply.barrierfreetrip.data.ConvenienceInfoDTO
import com.triply.barrierfreetrip.databinding.FragmentStayInfoBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel
import com.triply.barrierfreetrip.util.toUIString

class StayInfoFragment : BaseFragment<FragmentStayInfoBinding>(R.layout.fragment_stay_info) {
    private val viewModel: MainViewModel by viewModels()
    private var contentId : String? = null
    private var isLike = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentId = arguments?.getString(CONTENT_ID)
    }

    override fun initInViewCreated() {
        with(binding.rvConvenienceInfo) {
            adapter = ConvenienceInfoAdapter()
            layoutManager = LinearLayoutManager(requireContext(), androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
            if (itemDecorationCount < 1) addItemDecoration(ConvenienceInfoViewHolderDecoration())
        }

        with(binding.cardToggleLike) {
            setOnClickListener {
                // like 업데이트
                
            }
        }

        binding.btnStayinfoReview.setOnClickListener {
            contentId?.let {
                val bundle = Bundle()
                val reviewFragment = ReviewFragment()

                bundle.putString(CONTENT_ID, it)
                reviewFragment.arguments = bundle

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .add(android.R.id.content, reviewFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        with(binding.vpStayinfo) {
            adapter = ViewPagerAdapter()
            registerOnPageChangeCallback(object: OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.indicatorVp.setCurrentIndicator(position)
                }
            })

            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        // call api
        contentId?.let {
            viewModel.getFcltDetail(it)
        }
        viewModel.fcltDetail.observe(viewLifecycleOwner) { detail ->
            if (detail.contentId.isBlank()) return@observe

            (binding.vpStayinfo.adapter as ViewPagerAdapter).setDataList(detail.imgs)
            binding.indicatorVp.initIndicators(detail.imgs.size)

            binding.tvStayinfoPlaceName.text = detail.title
            binding.tvStayinfoRate.text = detail.rating
            binding.tvStayinfoLocation.text = detail.addr1
            binding.tvStayinfoEnterTime.text = detail.checkInTime
            binding.tvStayinfoLeaveTime.text = detail.checkOutTime
            binding.tvStayinfoIntroduce.text = detail.overview
            binding.ivLike.setImageResource(
                if (detail.like == 0) R.drawable.ic_stay_heart_filled
                else R.drawable.ic_stay_heart_empty
            )

            val convenienceInfos = mutableListOf(
                ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_elevator), content = detail.elevator?.toUIString() ?: ""),
                ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_restroom), content = detail.restroom?.toUIString() ?: ""),
                ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_handicapetc), content = detail.handicapetc?.toUIString() ?: ""),
                ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_braileblock), content = detail.braileblock?.toUIString() ?: ""),
                ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_freeParking), content = detail.freeParking?.toUIString() ?: ""),
                ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_publictransport), content = detail.publictransport?.toUIString() ?: ""),
            ).filter {
                it.content.isNotBlank()
            }
            binding.clStayinfoConvInfo.visibility = if (convenienceInfos.isEmpty()) View.GONE else View.VISIBLE
            (binding.rvConvenienceInfo.adapter as ConvenienceInfoAdapter).setInfoList(convenienceInfos)
        }
    }

    companion object {
        private const val TAG = "StayInfoFragment"
    }
}