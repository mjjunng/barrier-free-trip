package com.triply.barrierfreetrip

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.mapwidget.InfoWindowOptions
import com.kakao.vectormap.mapwidget.component.GuiImage
import com.kakao.vectormap.mapwidget.component.GuiLayout
import com.kakao.vectormap.mapwidget.component.GuiText
import com.kakao.vectormap.mapwidget.component.Orientation
import com.triply.barrierfreetrip.MainActivity.Companion.CONTENT_ID
import com.triply.barrierfreetrip.databinding.FragmentWishlistMapBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel
import com.triply.barrierfreetrip.util.CONTENT_TYPE_CHARGER
import com.triply.barrierfreetrip.util.convertDrawableToBitmapIcon
import java.lang.System.currentTimeMillis


class WishlistMapFragment : BaseFragment<FragmentWishlistMapBinding>(R.layout.fragment_wishlist_map) {
    private val viewModel: MainViewModel by viewModels()
    private var contentId: String? = null
    private var _kakaoMap: KakaoMap? = null
    private val kakaoMap: KakaoMap
        get() = _kakaoMap!!
    private var timeOnClickLike = currentTimeMillis()
    private val debouncingInterval = 300L
    private val loadingProgressBar by lazy { BFTLoadingProgressBar(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contentId = it.getInt(CONTENT_ID).toString()
        }
    }

    override fun initInViewCreated() {
        initMap()
        binding.tvTitle.text = "전동휠체어 급속충전기"

        binding.btnBack.setOnClickListener {
            backToPrevFragment()
        }
        viewModel.getChargerInfo(contentId = contentId?.toLong() ?: 0)

        viewModel.chargerInfo.observe(viewLifecycleOwner) { chargerInfo ->
            if (chargerInfo == null) return@observe

            binding.dialogMapInfo.setDialogInfo(
                title = chargerInfo.title,
                officeHour = "Open" + chargerInfo.weekdayOpen + "| Close" + chargerInfo.weekdayClose,
                location = chargerInfo.addr.replace("  ", " "),
                callNumber = chargerInfo.tel,
                multiCharger = chargerInfo.possible,
                airChargerCapability = if (chargerInfo.equals("N")) "불가" else "가능",
                phoneChargerCapability = if (chargerInfo.equals("N")) "불가" else "가능",
                like = chargerInfo.like == 1,
            )
            makeWidget(chargerInfo.title, chargerInfo.latitude, chargerInfo.longitude)
            setCameraPosition(chargerInfo.latitude, chargerInfo.longitude)
            binding.dialogMapInfo.setOnClickListener { _ ->
                setCameraPosition(chargerInfo.latitude, chargerInfo.longitude)
            }
            binding.dialogMapInfo.setOnLikeClick {
                if (currentTimeMillis() - timeOnClickLike < debouncingInterval) {
                    return@setOnLikeClick
                }
                timeOnClickLike = currentTimeMillis()
                contentId?.let {
                    viewModel.postLikes(contentType = CONTENT_TYPE_CHARGER, contentId = it, likes = chargerInfo.like xor 1)
                    binding.dialogMapInfo.updateLike(like = chargerInfo.like xor 1 == 1)
                }
            }
        }

        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                loadingProgressBar.show()
            } else {
                loadingProgressBar.dismiss()
            }
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
            .remove(this@WishlistMapFragment)
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
                contentId?.let {
                    viewModel.getChargerInfo(contentId = it.toLong())
                }

            }
        }
        binding.mapChargerInfo.start(mapLifeCycleCallback, kakaoMapReadyCycleCallback)
    }

    private fun makeWidget(title: String, latitude: Double, longitude: Double): InfoWindowOptions {
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

    private fun setCameraPosition(latitude: Double, longitude: Double) {
        val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(latitude, longitude))
        kakaoMap.moveCamera(cameraUpdate)
    }
}