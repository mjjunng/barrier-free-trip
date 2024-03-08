package com.triply.barrierfreetrip

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.triply.barrierfreetrip.adapter.InfoListAdapter
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.InfoListDto
import com.triply.barrierfreetrip.databinding.FragmentWishlistBinding
import retrofit2.Response

class WishlistFragment : Fragment(R.layout.fragment_wishlist) {
    var retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    private var _binding:  FragmentWishlistBinding? = null
    lateinit var infoListAapter: InfoListAdapter
    private val binding get() = _binding!!
    private val TAG = "WishlistFragment"
    private var type : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        type = arguments?.getString("type")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)

        // 목록화면의 타이틀 변경
        if (type.equals("1")) {
            binding.tvMain.text = "돌봄여행"
        } else if (type.equals("2")) {
            binding.tvMain.text = "충전기"
        } else {
            binding.tvMain.text = "렌탈"
        }

        // set init on sido spinner
        var sidoNames = resources.getStringArray(R.array.care_sido_nms)

        // setting sido data on first spinner
        var adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1, sidoNames
        )

        binding.spnBigArea.adapter = adapter

        // click event on sido spinner
        binding.spnBigArea.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // setting sigungu data on second spinner
                    var sigunguArray = 0

                    if (sidoNames[position].equals("광주광역시")) {
                        sigunguArray = R.array.care_sigungu_gj
                    } else if (sidoNames[position].equals("대구광역시")) {
                        sigunguArray = R.array.care_sigungu_dg
                    } else if (sidoNames[position].equals("경상북도")) {
                        sigunguArray = R.array.care_sigungu_gb
                    } else if (sidoNames[position].equals("충청북도")) {
                        sigunguArray = R.array.care_sigungu_cb
                    } else if (sidoNames[position].equals("부산광역시")) {
                        sigunguArray = R.array.care_sigungu_bs
                    } else if (sidoNames[position].equals("경상남도")) {
                        sigunguArray = R.array.care_sigungu_gn
                    } else if (sidoNames[position].equals("대전광역시")) {
                        sigunguArray = R.array.care_sigungu_dj
                    } else {
                        sigunguArray = R.array.care_sigungu_no_seleected
                    }
                    var sigunguNames = resources.getStringArray(sigunguArray)

                    var adapter2 = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_list_item_1, sigunguNames
                    )

                    binding.spnSmallArea.adapter = adapter2

                    // click event on sigungu spinner
                    binding.spnSmallArea.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position2: Int,
                                id: Long
                            ) {
                                // get list data
                                getListData(sidoNames[position], sigunguNames[position2])
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                            }
                        }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        return binding.root
    }

    fun getListData(sidoNm: String, sigunguNm: String) {
        // get list data
        val infoListDtoList = ArrayList<InfoListDto>()

        val responseLiveData3: LiveData<Response<List<InfoListDto>>> = liveData {
            val response = retrofit.getCareTourList(sidoNm, sigunguNm)

            emit(response)
        }

        responseLiveData3.observe(viewLifecycleOwner, Observer {
            val list = it.body()?.listIterator()
            if (list != null) {
                while (list.hasNext()) {
                    val item = list.next()
                    infoListDtoList.add(item)
                }
            } else {
                Log.d(TAG, "null wishlist data")
            }
            infoListAapter = InfoListAdapter(infoListDtoList)
            binding.rvList.adapter = infoListAapter
//            binding.rvNearHotelList.addItemDecoration(
//                GridSpacingItemDecoration(spanCount = 2, spacing = 10f.fromDpToPx())
//            )
//            binding.rvNearHotelList.run {
//                adapter = infoSquareAdapter
//                val spanCount = 2
//                val space = 20
//                addItemDecoration(GridSpacingItemDecoration(spanCount, space))
//            }
            binding.rvList.layoutManager = LinearLayoutManager(context)

//            infoListAapter.setItemClickListener(object : InfoSquareAdapter.OnItemClickListener {
//                override fun onClick(view: View, position: Int) {
//                    val item = infoListDtoList[position]
//                    val bundle = Bundle()
//                    val stayInfoFragment = StayInfoFragment()
//
//                    bundle.putString("contentId", item.contentId)
//                    stayInfoFragment.arguments = bundle
//
//                    requireActivity().supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.main_nav_host_fragment, stayInfoFragment)
//                        .commit()
//                }
//            })
        })
    }
}