package com.triply.barrierfreetrip

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.triply.barrierfreetrip.adapter.ConvenienceInfoAdapter
import com.triply.barrierfreetrip.adapter.ViewPagerAdapter
import com.triply.barrierfreetrip.adapter.decoration.ConvenienceInfoViewHolderDecoration
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.ConvenienceInfoDTO
import com.triply.barrierfreetrip.data.TourFacilityDetail
import com.triply.barrierfreetrip.databinding.FragmentStayInfoBinding
import retrofit2.Response
import com.triply.barrierfreetrip.util.toUIString

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
    ): View {
        // view binding
        _binding = FragmentStayInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvConvenienceInfo.adapter = ConvenienceInfoAdapter()
        binding.rvConvenienceInfo.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        if (binding.rvConvenienceInfo.itemDecorationCount < 1) binding.rvConvenienceInfo.addItemDecoration(ConvenienceInfoViewHolderDecoration())

        // call api
        val responseLiveData: LiveData<Response<TourFacilityDetail>> = liveData {
            val response = contentId?.let { retrofit.getTourFcltDetail(it) }

            if (response != null) {
                emit(response)
            }
        }

        //todo::viewpagage 애니메이션 주기
        responseLiveData.observe(viewLifecycleOwner) { infoResponse ->
            val data = infoResponse.body()
            if (data != null) {
                binding.vpStayinfo.adapter = ViewPagerAdapter(data.imgs)
                binding.vpStayinfo.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                binding.tvStayinfoPlaceName.text = data.title
                binding.tvStayinfoRate.text = data.rating
                binding.tvStayinfoLocation.text = data.addr1
                binding.tvStayinfoEnterTime.text = data.checkInTime
                binding.tvStayinfoLeaveTime.text = data.checkOutTime
                binding.tvStayinfoIntroduce.text = data.overview

                val convenienceInfos = mutableListOf(
                    ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_elevator), content = data.elevator?.toUIString() ?: ""),
                    ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_restroom), content = data.restroom?.toUIString() ?: ""),
                    ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_handicapetc), content = data.handicapetc?.toUIString() ?: ""),
                    ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_braileblock), content = data.braileblock?.toUIString() ?: ""),
                    ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_freeParking), content = data.freeParking?.toUIString() ?: ""),
                    ConvenienceInfoDTO(subject = ContextCompat.getString(requireContext(), R.string.stayinfo_publictransport), content = data.publictransport?.toUIString() ?: ""),
                ).filter {
                    it.content.isNotBlank()
                }
                binding.clStayinfoConvInfo.visibility = if (convenienceInfos.isEmpty()) View.GONE else View.VISIBLE
                (binding.rvConvenienceInfo.adapter as ConvenienceInfoAdapter).setInfoList(convenienceInfos)
            } else {
                Log.d(TAG, "null touistfacility-info data")
            }
        }
    }
}