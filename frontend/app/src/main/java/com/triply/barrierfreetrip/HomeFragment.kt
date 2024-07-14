package com.triply.barrierfreetrip

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.triply.barrierfreetrip.MainActivity.Companion.CONTENT_ID
import com.triply.barrierfreetrip.adapter.InfoSquareAdapter
import com.triply.barrierfreetrip.adapter.OnItemClickListener
import com.triply.barrierfreetrip.adapter.decoration.StayListItemViewHolderDecoration
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.databinding.FragmentHomeBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: MainViewModel by viewModels()
    private val nearbyStayListAdapter = InfoSquareAdapter()
    private val nearbyChargerDtoList = ArrayList<InfoSquareDto>()
    private val nearbyStayDtoList = ArrayList<InfoSquareDto>()

    override fun initInViewCreated() {
        with(binding.rvNearHotelList) {
            adapter = nearbyStayListAdapter
            if (itemDecorationCount < 1) {
                addItemDecoration(StayListItemViewHolderDecoration())
            }
            layoutManager = GridLayoutManager(context, 2)
            (adapter as InfoSquareAdapter).setOnItemClickListener(
                object: OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val item = nearbyStayDtoList[position]
                        val bundle = Bundle()
                        val stayInfoFragment = StayInfoFragment()

                        bundle.putString(CONTENT_ID, item.contentId)
                        stayInfoFragment.arguments = bundle

                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .add(R.id.main_nav_host_fragment, stayInfoFragment)
                            .commit()
                    }
                }
            )
        }
        with(binding.rvChargerList) {
//            adapter = nearbyStayListAdapter
//            if (itemDecorationCount < 1) {
//                addItemDecoration(StayListItemViewHolderDecoration())
//            }
//            layoutManager = GridLayoutManager(context, 2)
//            (adapter as InfoSquareAdapter).setOnItemClickListener(
//                object: OnItemClickListener {
//                    override fun onItemClick(position: Int) {
//                        val item = nearbyStayDtoList[position]
//                        val bundle = Bundle()
//                        val stayInfoFragment = StayInfoFragment()
//
//                        bundle.putString(CONTENT_ID, item.contentId)
//                        stayInfoFragment.arguments = bundle
//
//                        requireActivity().supportFragmentManager
//                            .beginTransaction()
//                            .add(R.id.main_nav_host_fragment, stayInfoFragment)
//                            .commit()
//                    }
//                }
//            )
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
//            (binding.rvChargerList.adapter as InfoSquareAdapter).setDataList(it)
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
        const val CONTENT_TYPE_STAY = "32"
        const val CONTENT_TYPE_TOUR = "12"
        const val CONTENT_TYPE_RESTAURANT = "39"
        const val CONTENT_TYPE_CARE = "1"
        const val CONTENT_TYPE_CHARGER = "2"
        const val CONTENT_TYPE_RENTAL = "3"
    }
}


