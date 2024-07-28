package com.triply.barrierfreetrip

import android.content.Intent
import android.net.Uri
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
import com.triply.barrierfreetrip.util.convertHomepageToURL
import com.triply.barrierfreetrip.util.toUIString

class StayInfoFragment : BaseFragment<FragmentStayInfoBinding>(R.layout.fragment_stay_info) {
    private val viewModel: MainViewModel by viewModels()
    private var contentId: String? = null
    private val loadingProgressBar by lazy { BFTLoadingProgressBar(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentId = arguments?.getString(CONTENT_ID)
    }

    override fun initInViewCreated() {
        with(binding.rvConvenienceInfo) {
            adapter = ConvenienceInfoAdapter()
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            if (itemDecorationCount < 1) addItemDecoration(ConvenienceInfoViewHolderDecoration())
        }

        binding.imgbtnBack.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount > 0) parentFragmentManager.popBackStack()
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
            viewModel.getReviews(it)
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

            binding.tbToggleLike.setOnClickListener {
                viewModel.postLikes(
                    contentType = detail.contentTypeId,
                    contentId = detail.contentId,
                    likes = detail.like xor 1
                )
            }

            binding.btnStayinfoCall.setOnClickListener {
                val phoneNumber = if (detail.tel.contains(',')) detail.tel.split(',').getOrElse(0) { "" } else detail.tel
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $phoneNumber"))
                startActivity(intent)
            }
            binding.btnStayinfoMap.setOnClickListener {

            }
            binding.btnStayinfoPage.setOnClickListener {
                try {
                    val homepageUrl = if (detail.homepage.first() == '<') convertHomepageToURL(detail.homepage) else detail.homepage
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(homepageUrl)
                    )
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        viewModel.reviews.observe(viewLifecycleOwner) {
            binding.btnStayinfoReview.text = getString(R.string.show_reviews, it.totalCnt)
        }

        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                loadingProgressBar.show()
            } else {
                loadingProgressBar.dismiss()
            }
        }
    }

    companion object {
        private const val TAG = "StayInfoFragment"
    }
}