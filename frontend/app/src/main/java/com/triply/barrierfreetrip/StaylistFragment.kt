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
import androidx.recyclerview.widget.GridLayoutManager
import com.triply.barrierfreetrip.adapter.BFTSpinnerAdapter
import com.triply.barrierfreetrip.adapter.InfoSquareAdapter
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.data.Sido
import com.triply.barrierfreetrip.data.Sigungu
import com.triply.barrierfreetrip.databinding.FragmentStaylistBinding
import retrofit2.Response

class StaylistFragment : Fragment(R.layout.fragment_staylist) {
    var retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    private var _binding: FragmentStaylistBinding? = null
    lateinit var infoSquareAdapter: InfoSquareAdapter
    private val binding get() = _binding!!
    private val TAG = "StayListFragment"
    private var type: String? = null

    // init spinner data
    private val sidoCodes = arrayListOf(Sido(code = "-1", name = "시도 선택"))
    private val sidoNames = arrayListOf("시도 선택")

    // call api to get sigungu code
    private val sigunguCodes = arrayListOf(Sigungu(code = "-1", name = "구군 선택"))
    private val sigunguNames = arrayListOf("구군 선택")

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
        initTitle()

        initSpinner()

        val responseLiveData: LiveData<Response<List<Sido>>> = liveData {
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
            binding.spnBigArea.setSelection(0)
        })

        // click event on sido spinner
        binding.spnBigArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    binding.spnSmallArea.setSelection(0)
                    binding.spnSmallArea.isEnabled = false
                    return
                }
                binding.spnSmallArea.isEnabled = true

                val responseLiveData2: LiveData<Response<List<Sigungu>>> = liveData {
                    val response = retrofit.getSigunguCode(sidoCodes[position].code)
                    emit(response)
                }

                responseLiveData2.observe(viewLifecycleOwner, Observer {
                    val list = it.body()?.listIterator()
                    sigunguCodes.clear()
                    sigunguNames.clear()
                    sigunguCodes.add(Sigungu(code = "-1", name = "구군 선택"))
                    sigunguNames.add("구군 선택")
                    if (list != null) {
                        while (list.hasNext()) {
                            val item = list.next()
                            sigunguCodes.add(item)
                            sigunguNames.add(item.name)
                        }
                    } else {
                        Log.d(TAG, "null sigungu code data")
                    }
                    binding.spnSmallArea.setSelection(0)

                    // click event on sigungu spinner
                    binding.spnSmallArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position2: Int,
                            id: Long
                        ) {
                            if (position2 == 0) return

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
            binding.rvList.layoutManager = GridLayoutManager(context, 2)

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

    fun initTitle() {
        binding.tvMain.text = when {
            type.equals("32") -> "숙박"
            type.equals("12") -> "관광지"
            else -> "음식점"
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
}


