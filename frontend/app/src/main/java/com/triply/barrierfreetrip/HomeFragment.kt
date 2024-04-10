package com.triply.barrierfreetrip

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.GridLayoutManager
import com.triply.barrierfreetrip.adapter.InfoSquareAdapter
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.databinding.FragmentHomeBinding
import retrofit2.Response


class HomeFragment : Fragment(R.layout.fragment_home) {
    var retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    lateinit var infoSquareAdapter: InfoSquareAdapter
    val infoSquareDtoList = ArrayList<InfoSquareDto>()
    private var _binding:  FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 내 주변 숙박시설 API 호출
        val responseLiveData : LiveData<Response<List<InfoSquareDto>>> = liveData {
            val response = retrofit.getStayList(126.838044, 35.14384) // todo::사용자 좌표 값 넣어야 함

            emit(response)
        }

        responseLiveData.observe(viewLifecycleOwner, Observer {
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

            binding.rvNearHotelList.layoutManager = GridLayoutManager(context, 2)

            infoSquareAdapter.setItemClickListener(object : InfoSquareAdapter.OnItemClickListener {
                override fun onClick(view: View, position: Int) {
                    val item = infoSquareDtoList[position]
                    val bundle = Bundle()
                    val stayInfoFragment = StayInfoFragment()

                    bundle.putString("contentId", item.contentId)
                    stayInfoFragment.arguments = bundle

                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .add(R.id.main_nav_host_fragment, stayInfoFragment)
                        .commit()
                }
            })

        })

        // 내 주변 전동휠체어 충전기 API 호출
        val responseLiveDataWheelchairList : LiveData<Response<List<InfoSquareDto>>> = liveData {
            val response = retrofit.getStayList(126.838044, 35.14384) // todo::사용자 좌표 값 넣어야 함

            emit(response)
        }

        responseLiveData.observe(viewLifecycleOwner, Observer {
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

            binding.rvNearHotelList.layoutManager = GridLayoutManager(context, 2)

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
                        .addToBackStack(null)
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
                .addToBackStack(null)
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
                .addToBackStack(null)
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
                .addToBackStack(null)
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
                .addToBackStack(null)
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
                .addToBackStack(null)
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
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }

    fun Float.fromDpToPx(): Int = (
            this * Resources.getSystem().displayMetrics.density
            ).toInt()
}


