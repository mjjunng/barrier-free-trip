package com.triply.barrierfreetrip

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.triply.barrierfreetrip.MainActivity.Companion.CONTENT_ID
import com.triply.barrierfreetrip.adapter.InfoListAdapter
import com.triply.barrierfreetrip.adapter.InfoSquareAdapter
import com.triply.barrierfreetrip.adapter.OnItemClickListener
import com.triply.barrierfreetrip.adapter.OnLikeClickListener
import com.triply.barrierfreetrip.adapter.decoration.StayListItemViewHolderDecoration
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.databinding.FragmentHomeBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel
import com.triply.barrierfreetrip.util.CONTENT_TYPE_CARE
import com.triply.barrierfreetrip.util.CONTENT_TYPE_CHARGER
import com.triply.barrierfreetrip.util.CONTENT_TYPE_RENTAL
import com.triply.barrierfreetrip.util.CONTENT_TYPE_RESTAURANT
import com.triply.barrierfreetrip.util.CONTENT_TYPE_STAY
import com.triply.barrierfreetrip.util.CONTENT_TYPE_TOUR

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: MainViewModel by viewModels()
    private val nearbyChargerDtoList = ArrayList<InfoListDto>()
    private val nearbyStayDtoList = ArrayList<InfoSquareDto>()
    private val loadingProgressBar by lazy { BFTLoadingProgressBar(requireContext()) }

    override fun initInViewCreated() {
        with(binding.rvNearHotelList) {
            adapter = InfoSquareAdapter().apply {
                setOnItemClickListener(
                    object: OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val item = nearbyStayDtoList.getOrNull(position) ?: return
                            val bundle = Bundle()
                            val stayInfoFragment = StayInfoFragment()

                            bundle.putString(CONTENT_ID, item.contentId)
                            stayInfoFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .add(android.R.id.content, stayInfoFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                )
                setLikeVisibility(false)
            }
            if (itemDecorationCount < 1) {
                addItemDecoration(StayListItemViewHolderDecoration())
            }
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        with(binding.rvChargerList) {
            adapter = InfoListAdapter().apply {
                setOnItemClickListener(
                    object : OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val item = nearbyStayDtoList.getOrNull(position) ?: return

                            val bundle = Bundle()
                            val stayInfoFragment = StayInfoFragment()

                            bundle.putString(CONTENT_ID, item.contentId)
                            stayInfoFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .add(android.R.id.content, stayInfoFragment)
                                .commit()
                        }
                    }
                )
                setLikeVisibility(false)
            }
            layoutManager = LinearLayoutManager(this.context)
        }

        // 내 주변 숙박시설 API 호출
        viewModel.getNearbyStayList(126.838044, 35.14384)

        viewModel.nearbyStayList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                Log.d(TAG, "null near-hotel data")
                return@observe
            }

            nearbyStayDtoList.clear()
            nearbyStayDtoList.addAll(it)
            (binding.rvNearHotelList.adapter as InfoSquareAdapter).setDataList(it)
        }

        // 내 주변 전동휠체어 충전기 API 호출
        viewModel.getNearbyChargerList(126.838044, 35.14384)

        viewModel.nearbyChargerList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                Log.d(TAG, "null near-charger data")
                return@observe
            }

            nearbyChargerDtoList.clear()
            nearbyChargerDtoList.addAll(it)
            (binding.rvChargerList.adapter as InfoListAdapter).setInfoList(it)
        }

        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                loadingProgressBar.show()
            } else {
                loadingProgressBar.dismiss()
            }
        }


        // 아이콘 클릭 시 이동
        val bundle = Bundle()
        // 1. 숙박
        val stayListFragment = StaylistFragment()
        binding.btnHomeStay.setOnClickListener {
            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_STAY)
            stayListFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, stayListFragment)
                .addToBackStack(null)
                .commit()
        }

        // 2. 관광지
        binding.btnHomeDestination.setOnClickListener {
            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_TOUR)
            stayListFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, stayListFragment)
                .addToBackStack(null)
                .commit()
        }

        // 3. 음식점
        binding.btnHomeRestaurant.setOnClickListener {
            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_RESTAURANT)
            stayListFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, stayListFragment)
                .addToBackStack(null)
                .commit()
        }

        // 4. 돌봄여행
        val wishlistFragment = WishlistFragment()
        binding.btnHomeCaretrip.setOnClickListener {
            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_CARE)
            wishlistFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                .addToBackStack(null)
                .commit()
        }

        // 5. 충전기
        binding.btnHomeCharge.setOnClickListener {
            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_CHARGER)
            wishlistFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                .addToBackStack(null)
                .commit()
        }

        // 6. 렌탈
        binding.btnHomeRental.setOnClickListener {
            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_RENTAL)
            wishlistFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        private const val TAG = "HomeFragment"
        const val CONTENT_TYPE = "type"
    }
}