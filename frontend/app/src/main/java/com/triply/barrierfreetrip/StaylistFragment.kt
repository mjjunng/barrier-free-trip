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
import com.triply.barrierfreetrip.adapter.InfoSquareAdapter
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.data.Sido
import com.triply.barrierfreetrip.data.Sigungu
import com.triply.barrierfreetrip.databinding.FragmentStaylistBinding
import retrofit2.Response

class StaylistFragment : Fragment(R.layout.fragment_staylist){
    var retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    private var _binding:  FragmentStaylistBinding? = null
    lateinit var infoSquareAdapter: InfoSquareAdapter
    private val binding get() = _binding!!
    private val TAG = "StayListFragment"
    private var type : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        type = arguments?.getString("type")

        // 뒤로 가기 버튼 클릭 시
//        binding.btnBack.setOnClickListener {
//
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        _binding = FragmentStaylistBinding.inflate(inflater, container, false)

        // 목록화면의 타이틀 변경
        if (type.equals("32")) {
            binding.tvMain.text = "숙박"
        } else if (type.equals("12")) {
            binding.tvMain.text = "관광지"
        } else {
            binding.tvMain.text = "음식점"
        }

        // init spinner data
        var sidoCodes = ArrayList<Sido>()
        var sidoNames = ArrayList<String>()

        // set init on sido spinner
        // todo::스피너 초기화 값 세팅해야 함
//        sidoCodes.add(Sido("-1", "시도 선택"))
//        sidoNames.add("시도 선택")

        val responseLiveData : LiveData<Response<List<Sido>>> = liveData {
            val response = retrofit.getSidoCode()

            emit(response)
        }

        responseLiveData.observe(viewLifecycleOwner, Observer {
            val list = it.body()?.listIterator()
            if (list != null) {
                while (list.hasNext()) {
                    val item = list.next()
                    sidoCodes.add(item)
                    sidoNames.add(item.name)
                }
            } else {
                Log.d(TAG, "null sido code data")
            }

            // setting sido data on first spinner
            var adapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_list_item_1, sidoNames
            )

            binding.spnBigArea.adapter = adapter
        })

        // click event on sido spinner
        binding.spnBigArea.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // call api to get sigungu code
                var sigunguCodes = ArrayList<Sigungu>()
                var sigunguNames = ArrayList<String>()

                val responseLiveData2 : LiveData<Response<List<Sigungu>>> = liveData {
                    val response = retrofit.getSigunguCode(sidoCodes[position].code)
                    emit(response)
                }

                responseLiveData2.observe(viewLifecycleOwner, Observer {
                    val list = it.body()?.listIterator()
                    if (list != null) {
                        while (list.hasNext()) {
                            val item = list.next()
                            sigunguCodes.add(item)
                            sigunguNames.add(item.name)
                        }
                    } else {
                        Log.d(TAG, "null sigungu code data")
                    }

                    // setting sigungu data on second spinner
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
                            getListData(sidoCodes[position].code, sigunguCodes[position2].code)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        return binding.root
    }

    fun getListData(sidoCode: String, sigunguCode: String) {
        // get list data
        val infoSquareDtoList = ArrayList<InfoSquareDto>()

        val responseLiveData3: LiveData<Response<List<InfoSquareDto>>> = liveData {
            val response = retrofit.getTourFcltList(type.toString(), sidoCode, sigunguCode)

            emit(response)
        }

        responseLiveData3.observe(viewLifecycleOwner, Observer {
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
            binding.rvList.adapter = infoSquareAdapter
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
    }
}


