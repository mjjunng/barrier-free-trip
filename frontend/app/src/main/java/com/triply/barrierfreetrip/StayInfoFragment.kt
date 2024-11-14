package com.triply.barrierfreetrip

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.viewpager2.widget.ViewPager2
import com.triply.barrierfreetrip.adapter.ViewPagerAdapter
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.TourFacilityDetail
import com.triply.barrierfreetrip.databinding.FragmentStayInfoBinding
import retrofit2.Response

class StayInfoFragment : Fragment(R.layout.fragment_stay_info) {
    private var contentId : String? = null
    var retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    private var _binding: FragmentStayInfoBinding? = null
    private val binding get() = _binding!!
    private val TAG = "StayInfoFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentId = arguments?.getString("contentId")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        _binding = FragmentStayInfoBinding.inflate(inflater, container, false)

        // call api
        val responseLiveData: LiveData<Response<TourFacilityDetail>> = liveData {
            val response = contentId?.let { retrofit.getTourFcltDetail(it) }

            if (response != null) {
                emit(response)
            }
        }
        //todo::viewpagage 애니메이션 주기
        responseLiveData.observe(viewLifecycleOwner, Observer {
            val data = it.body()
            if (data != null) {
                binding.vpStayinfo.adapter = ViewPagerAdapter(data.imgs)
                binding.vpStayinfo.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                binding.tvStayinfoPlaceName.text = data.title
                binding.tvStayinfoRate.text = data.rating.toString()
                binding.tvStayinfoLocation.text = data.addr1
                binding.tvStayinfoEnterTime.text = data.checkInTime
                binding.tvStayinfoLeaveTime.text = data.checkOutTime
                binding.tvStayinfoIntroduce.text = data.overview
            } else {
                Log.d(TAG, "null touistfacility-info data")
            }

        })
        return binding.root
    }
}