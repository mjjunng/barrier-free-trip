package com.triply.barrierfreetrip

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.triply.barrierfreetrip.HomeFragment.Companion.CONTENT_TYPE
import com.triply.barrierfreetrip.MainActivity.Companion.CONTENT_ID
import com.triply.barrierfreetrip.adapter.BFTSpinnerAdapter
import com.triply.barrierfreetrip.adapter.InfoListAdapter
import com.triply.barrierfreetrip.adapter.OnItemClickListener
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.databinding.FragmentWishlistBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel

class WishlistFragment : BaseFragment<FragmentWishlistBinding>(R.layout.fragment_wishlist) {
    private val viewModel: MainViewModel by viewModels()

    private var type: String? = null
    private var sidoNames = arrayListOf("시도 선택")
    private var sigunguNames = arrayListOf("구군 선택")
    private val infoList = arrayListOf<InfoListDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getString(CONTENT_TYPE)
    }

    override fun initInViewCreated() {
        when {
            type.equals(TYPE_CARE_TOUR) -> {
                binding.tvTitle.text = "돌봄여행"
                sidoNames.clear()
                sidoNames.addAll(resources.getStringArray(R.array.care_sido_nms))
            }

            type.equals(TYPE_CHARGER) -> {
                binding.tvTitle.text = "충전기"
                sidoNames.clear()
                sidoNames.addAll(resources.getStringArray(R.array.charger_sido_nms))
            }

            else -> {
                binding.tvTitle.text = "렌탈"
                sidoNames.clear()
                sidoNames.addAll(resources.getStringArray(R.array.rental_sido_nms))
            }
        }
        initSpinner()
        binding.spnBigArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                bigAreaPosition: Int,
                id: Long
            ) {
                // setting sigungu data on second spinner
                val sigunguArray = when {
                    sidoNames[bigAreaPosition].equals("강원도") && type.equals("3") -> R.array.charger_sigungu_gw
                    sidoNames[bigAreaPosition].equals("광주광역시") -> {
                        when {
                            type.equals(TYPE_CARE_TOUR) -> R.array.care_sigungu_gj
                            type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_gj
                            else -> R.array.rental_sigungu_gj
                        }
                    }
                    sidoNames[bigAreaPosition].equals("대구광역시") -> {
                        when {
                            type.equals(TYPE_CARE_TOUR) -> R.array.care_sigungu_dg
                            type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_dg
                            else -> R.array.rental_sigungu_dg
                        }
                    }
                    sidoNames[bigAreaPosition].equals("경상북도") -> {
                        when {
                            type.equals(TYPE_CARE_TOUR) -> R.array.care_sigungu_gb
                            type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_gb
                            else -> R.array.rental_sigungu_gb
                        }
                    }
                    sidoNames[bigAreaPosition].equals("충청북도") -> {
                        when {
                            type.equals(TYPE_CARE_TOUR) -> R.array.care_sigungu_cb
                            type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_cb
                            else -> R.array.rental_sigungu_cb
                        }
                    }
                    sidoNames[bigAreaPosition].equals("부산광역시") -> {
                        when {
                            type.equals(TYPE_CARE_TOUR) -> R.array.care_sigungu_bs
                            type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_bs
                            else -> R.array.rental_sigungu_bs
                        }
                    }
                    sidoNames[bigAreaPosition].equals("경상남도") -> {
                        when {
                            type.equals(TYPE_CARE_TOUR) -> R.array.care_sigungu_gn
                            type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_gn
                            else -> R.array.rental_sigungu_gn
                        }
                    }
                    sidoNames[bigAreaPosition].equals("대전광역시") -> {
                        when {
                            type.equals(TYPE_CARE_TOUR) -> R.array.care_sigungu_dj
                            type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_dj
                            else -> R.array.rental_sigungu_dj
                        }
                    }
                    sidoNames[bigAreaPosition].equals("경기도") -> {
                        when {
                            type.equals(TYPE_CHARGER) -> R.array.rental_sigungu_gyeonggi
                            else -> R.array.charger_sigungu_gyeonggi
                        }
                    }
                    sidoNames[bigAreaPosition].equals("서울특별시") -> {
                        when {
                            type.equals(TYPE_CHARGER) -> R.array.rental_sigungu_seoul
                            else -> R.array.charger_sigungu_seoul
                        }
                    }
                    sidoNames[bigAreaPosition].equals("세종특별자치시") && type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_sj
                    sidoNames[bigAreaPosition].equals("울산광역시") && type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_ws
                    sidoNames[bigAreaPosition].equals("인천광역시") && type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_ic
                    sidoNames[bigAreaPosition].equals("전라북도") -> {
                        when {
                            type.equals(TYPE_RENTAL) -> R.array.rental_sigungu_jb
                            else -> R.array.charger_sigungu_jb
                        }
                    }
                    sidoNames[bigAreaPosition].equals("제주특별자치도") && type.equals(TYPE_CHARGER) -> R.array.charger_sigungu_jeju
                    sidoNames[bigAreaPosition].equals("충청남도") -> {
                        when {
                            type.equals(TYPE_RENTAL) -> R.array.rental_sigungu_cn
                            else -> R.array.charger_sigungu_cn
                        }
                    }
                    else -> R.array.care_sigungu_no_selected
                }
                sigunguNames.clear()
                sigunguNames.add("구군 선택")
                sigunguNames.addAll(resources.getStringArray(sigunguArray))
                if (bigAreaPosition == 0) {
                    binding.spnSmallArea.setSelection(0)
                    binding.spnSmallArea.isEnabled = false
                    return
                }
                binding.spnSmallArea.isEnabled = true
                binding.spnSmallArea.setSelection(0)

                // click event on sigungu spinner
                binding.spnSmallArea.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        smallAreaPosition: Int,
                        id: Long
                    ) {
                        getFcltListData(
                            sidoNames.getOrElse(bigAreaPosition) { "" },
                            sigunguNames.getOrElse(smallAreaPosition) { "" }
                        )
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        with(binding.rvList) {
            val infoListAdapter = InfoListAdapter()
            binding.rvList.adapter = infoListAdapter.apply {
                setOnItemClickListener(object: OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val item = infoList.getOrNull(position)
                        item?.addr?.let {
                            val bundle = Bundle()
                            val fragment = when {
                                type.equals(TYPE_CARE_TOUR) || type.equals(TYPE_RENTAL) -> StayInfoFragment()
                                else -> MapFragment()
                            }

                            bundle.putString(CONTENT_ID, it)
                            fragment.arguments = bundle

                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_nav_host_fragment, fragment)
                                .addToBackStack(null)
                                .commit()
                        }

                    }

                })
            }
            binding.rvList.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        viewModel.fcltList.observe(viewLifecycleOwner) {
            (binding.rvList.adapter as InfoListAdapter).setInfoList(it)
        }
    }

    private fun getFcltListData(sidoNm: String?, sigunguNm: String) {
        when (type) {
            TYPE_CARE_TOUR -> viewModel.getCareTourList(sidoNm.toString(), sigunguNm)
            TYPE_CHARGER -> viewModel.getChargerList(sidoNm.toString(), sigunguNm)
            else -> viewModel.getRentalServiceList(sidoNm.toString(), sigunguNm)
        }
    }

    private fun initSpinner() {
        binding.spnBigArea.adapter = BFTSpinnerAdapter(requireContext(), R.layout.item_spinner_tv, sidoNames)
        binding.spnBigArea.setSelection(0)
        binding.spnBigArea.isEnabled = true

        binding.spnSmallArea.adapter = BFTSpinnerAdapter(requireContext(), R.layout.item_spinner_tv, sigunguNames)
        binding.spnSmallArea.setSelection(0)
        binding.spnSmallArea.isEnabled = false
    }

    companion object {
        private const val TAG = "WishlistFragment"
        private const val TYPE_CARE_TOUR = "1"
        private const val TYPE_CHARGER = "2"
        private const val TYPE_RENTAL = "3"
    }
}