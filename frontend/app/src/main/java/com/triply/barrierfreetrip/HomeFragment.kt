package com.triply.barrierfreetrip

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.triply.barrierfreetrip.MainActivity.Companion.CONTENT_ID
import com.triply.barrierfreetrip.adapter.HomeInfoAdapter
import com.triply.barrierfreetrip.adapter.HomeMenuViewHolder
import com.triply.barrierfreetrip.adapter.OnItemClickListener
import com.triply.barrierfreetrip.adapter.decoration.HomeListItemViewHolderDecoration
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
    private val homeInfoList = arrayListOf<HomeInfoAdapter.HomeInfoDTO>(HomeInfoAdapter.HomeInfoDTO.Menu)
    private val loadingProgressBar by lazy { BFTLoadingProgressBar(requireContext()) }
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var currentLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hasPermissionForCoarseLocation = ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val hasPermissionForFineLocation = ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (hasPermissionForCoarseLocation && hasPermissionForFineLocation) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000L).build()
            fusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

            fusedLocationClient?.lastLocation?.addOnSuccessListener { location: Location? ->
                if (location == null) return@addOnSuccessListener

                currentLocation = location
                // 내 주변 숙박시설 API 호출
                viewModel.getNearbyStayList(currentLocation?.longitude ?: 126.9778222, currentLocation?.latitude ?: 37.5664056)
            }
        }
    }

    override fun initInViewCreated() {
        val bundle = Bundle()
        val stayListFragment = StaylistFragment()
        val wishlistFragment = WishlistFragment()
        println("$TAG + 이니시에이트")

        with(binding.rvHome) {
            adapter = HomeInfoAdapter().apply {
                this.setOnClickListener(
                    onMenuClickListener = object : HomeMenuViewHolder.OnClickListener {

                        override fun onHomeCareTripClick() {
                            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_CARE)
                            wishlistFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                                .addToBackStack(null)
                                .commit()
                        }

                        override fun onHomeChargeClick() {
                            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_CHARGER)
                            wishlistFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                                .addToBackStack(null)
                                .commit()
                        }

                        override fun onHomeDestinationClick() {
                            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_TOUR)
                            stayListFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_nav_host_fragment, stayListFragment)
                                .addToBackStack(null)
                                .commit()
                        }

                        override fun onHomeRentalClick() {
                            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_RENTAL)
                            wishlistFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_nav_host_fragment, wishlistFragment)
                                .addToBackStack(null)
                                .commit()
                        }

                        override fun onHomeRestaurantClick() {
                            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_RESTAURANT)
                            stayListFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_nav_host_fragment, stayListFragment)
                                .addToBackStack(null)
                                .commit()
                        }

                        override fun onHomeStayClick() {
                            bundle.putString(CONTENT_TYPE, CONTENT_TYPE_STAY)
                            stayListFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_nav_host_fragment, stayListFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    },
                    onInfoSquareClickListener = object : OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val item = homeInfoList.getOrNull(position) as HomeInfoAdapter.HomeInfoDTO.InfoSquare? ?: return
                            val stayInfoFragment = StayInfoFragment()

                            bundle.putString(CONTENT_ID, item.contentId)
                            stayInfoFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .add(android.R.id.content, stayInfoFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    },
                    onInfoListClickListener = object : OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val item = homeInfoList.getOrNull(position) as HomeInfoAdapter.HomeInfoDTO.InfoList? ?: return
                            val wishlistMapFragment = WishlistMapFragment()

                            bundle.putString(CONTENT_ID, item.id.toString())
                            wishlistMapFragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .add(android.R.id.content, wishlistMapFragment)
                                .commit()
                        }
                    }
                )
            }
            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when ((adapter as HomeInfoAdapter).getItemViewType(position)) {
                            HomeInfoAdapter.VIEWTYPE_NEARBY_STAY -> 1
                            else -> 2
                        }
                    }
                }
            }
            if (itemDecorationCount < 1) addItemDecoration(HomeListItemViewHolderDecoration())
        }


        viewModel.nearbyStayList.observe(viewLifecycleOwner) {
            if (it == null) {
                Log.d(TAG, "null near-hotel data")
                return@observe
            }

            // 내 주변 전동휠체어 충전기 API 호출
            viewModel.getNearbyChargerList(currentLocation?.longitude ?: 126.9778222, currentLocation?.latitude ?: 37.5664056)

            homeInfoList.clear()
            homeInfoList.add(HomeInfoAdapter.HomeInfoDTO.Menu)
            homeInfoList.add(HomeInfoAdapter.HomeInfoDTO.Title(title = "내 주변 숙박시설"))
            homeInfoList.addAll(
                it.map { info ->
                    HomeInfoAdapter.HomeInfoDTO.InfoSquare(
                        addr = info.addr,
                        contentId = info.contentId,
                        contentTypeId = info.contentTypeId,
                        firstimg = info.firstimg,
                        like = info.like,
                        rating = info.rating,
                        tel = info.tel,
                        title = info.title
                    )
                }
            )
            homeInfoList.add(HomeInfoAdapter.HomeInfoDTO.Title(title = "내 주변 전동휠체어 충전기"))
            (binding.rvHome.adapter as HomeInfoAdapter).setDataList(homeInfoList)
        }

        viewModel.nearbyChargerList.observe(viewLifecycleOwner) {
            if (it == null) {
                Log.d(TAG, "null near-charger data")
                return@observe
            }

            homeInfoList.addAll(
                it.map { info ->
                    HomeInfoAdapter.HomeInfoDTO.InfoList(
                        id = info.id,
                        addr = info.addr,
                        like = info.like,
                        tel = info.tel,
                        title = info.title
                    )
                }
            )
            (binding.rvHome.adapter as HomeInfoAdapter).setDataList(homeInfoList)
        }

        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                loadingProgressBar.show()
            } else {
                loadingProgressBar.dismiss()
            }
        }
    }

    private val locationCallback : LocationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            currentLocation = p0.lastLocation
            fusedLocationClient?.removeLocationUpdates(this)
        }
    }

    companion object {
        private const val TAG = "HomeFragment"
        const val CONTENT_TYPE = "type"
    }
}