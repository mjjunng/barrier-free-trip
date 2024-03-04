package com.triply.barrierfreetrip

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
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

        responseLiveData.observe(viewLifecycleOwner, Observer {
            val data = it.body()
            if (data != null) {
                //SquareBind.setImgUrl(binding.ivPlaceImage, data.imgs.get(0))
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

    object SquareBind {
        @JvmStatic
        @BindingAdapter("setImage")
        fun setImgUrl(view: ImageView, img: String) {
            Glide.with(view.context)
                .load(img)
                .into(view)
        }
    }
}