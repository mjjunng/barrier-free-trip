package com.triply.barrierfreetrip

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.triply.barrierfreetrip.adapter.InfoSquareAdapter
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.databinding.FragmentHomeBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import retrofit2.Response

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    var retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    lateinit var infoSquareAdapter: InfoSquareAdapter
    val infoSquareDtoList = ArrayList<InfoSquareDto>()
    private val TAG = "HomeFragment"

    override fun initInViewCreated() {
        val responseLiveData : LiveData<Response<List<InfoSquareDto>>> = liveData {
            val response = retrofit.getStayList(126.838044, 35.14384) // todo::사용자 좌표 값 넣어야 함

            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val list = it.body()?.listIterator()
            if (list != null) {
                while (list.hasNext()) {
                    val item = list.next()
                    infoSquareDtoList.add(item)
                }
            } else {
                Log.d(TAG, "null near-hotel data")
            }
            infoSquareAdapter = InfoSquareAdapter(infoSquareDtoList)
            binding.rvNearHotelList.adapter = infoSquareAdapter
//            binding.rvNearHotelList.addItemDecoration(
//                GridSpacingItemDecoration(spanCount = 2, spacing = 10f.fromDpToPx())
//            )
//            binding.rvNearHotelList.run {
//                adapter = infoSquareAdapter
//                val spanCount = 2
//                val space = 20
//                addItemDecoration(GridSpacingItemDecoration(spanCount, space))
//            }
            binding.rvNearHotelList.layoutManager = LinearLayoutManager(context)

            infoSquareAdapter.setItemClickListener(object : InfoSquareAdapter.OnItemClickListener {
                override fun onClick(view: View, position: Int) {
                    val item = infoSquareDtoList[position]
                    val bundle = Bundle()
                    val stayInfoFragment = StayInfoFragment()

                    bundle.putString("contentId", item.contentId)
                    stayInfoFragment.arguments = bundle

                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_nav_host_fragment, stayInfoFragment)
                        .commit()
                }
            })

        })

        // 아이콘 클릭 시 이동
        // 1. 숙박
        val stayListFragment = StaylistFragment()
        binding.btnHomeStay.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "32")
            stayListFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, stayListFragment)
                .commit()
        }

        // 2. 관광지
        binding.btnHomeDestination.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "12")
            stayListFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, stayListFragment)
                .commit()
        }

        // 3. 음식점
        binding.btnHomeRestaurant.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "39")
            stayListFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, stayListFragment)
                .commit()
        }

        // 4. 돌봄여행
        var wishlistFragment = WishlistFragment()
        binding.btnHomeCaretrip.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "1")
            wishlistFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                .commit()
        }

        // 5. 충전기
        binding.btnHomeCharge.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "2")
            wishlistFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                .commit()
        }

        // 6. 렌탈
        binding.btnHomeRental.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "3")
            wishlistFragment.arguments = bundle

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                .commit()
        }
    }

    fun Float.fromDpToPx(): Int = (
            this * Resources.getSystem().displayMetrics.density
            ).toInt()
}


