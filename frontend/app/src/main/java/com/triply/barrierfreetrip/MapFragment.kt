package com.triply.barrierfreetrip

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.mapwidget.InfoWindowOptions
import com.kakao.vectormap.mapwidget.component.GuiImage
import com.kakao.vectormap.mapwidget.component.GuiLayout
import com.kakao.vectormap.mapwidget.component.GuiText
import com.kakao.vectormap.mapwidget.component.Orientation
import com.triply.barrierfreetrip.databinding.FragmentMapBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel
import com.triply.barrierfreetrip.util.convertDrawableToBitmapIcon


class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {
    private val viewModel: MainViewModel by viewModels()
    private var contentId: String? = null
    private var _kakaoMap: KakaoMap? = null
    private val kakaoMap: KakaoMap
        get() = _kakaoMap!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contentId = it.getString(WishlistFragment.CONTENT_ID)
        }
    }

    override fun initInViewCreated() {
        initMap()

        binding.btnBack.setOnClickListener {
            backToPrevFragment()
        }

        viewModel.getChargerInfo(contentId = "0")
        viewModel.chargerInfo.observe(viewLifecycleOwner) {
            setChargerWidget(it.title, it.longitude, it.latitude)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapChargerInfo.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapChargerInfo.pause()
    }

    private fun backToPrevFragment() {
        val frgManager = parentFragmentManager
        frgManager.beginTransaction()
            .remove(this@MapFragment)
            .commit()
        frgManager.popBackStack()
    }

    private fun initMap() {
        val mapLifeCycleCallback = object : MapLifeCycleCallback() {
            override fun onMapDestroy() {

            }

            override fun onMapError(e: Exception?) {
                e?.printStackTrace()
            }
        }
        val kakaoMapReadyCycleCallback = object: KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {
                _kakaoMap = kakaoMap
            }
        }
        binding.mapChargerInfo.start(mapLifeCycleCallback, kakaoMapReadyCycleCallback)
    }

    private fun setChargerWidget(title: String, longitude: Double, latitude: Double): InfoWindowOptions {
        val body = GuiLayout(Orientation.Horizontal)
        body.setPadding(10, 8, 10, 10)
        val background = GuiImage(R.drawable.shape_oval_white_stroke1_main_pink_widget, true)
        body.setBackground(background)

        val chargerIcon = GuiImage(convertDrawableToBitmapIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_charge_with_background)!!))
        body.addView(chargerIcon)

        val text = GuiText(" $title")
        text.setTextSize(24)
        body.addView(text)

        val options = InfoWindowOptions.from(LatLng.from(latitude, longitude))
        options.setBody(body)
        options.setBodyOffset(0f, -4F)
        return options
    }
}