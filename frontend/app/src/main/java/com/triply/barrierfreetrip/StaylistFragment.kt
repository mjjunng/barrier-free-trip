package com.triply.barrierfreetrip

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.triply.barrierfreetrip.HomeFragment.Companion.CONTENT_ID
import com.triply.barrierfreetrip.HomeFragment.Companion.CONTENT_TYPE
import com.triply.barrierfreetrip.HomeFragment.Companion.CONTENT_TYPE_STAY
import com.triply.barrierfreetrip.HomeFragment.Companion.CONTENT_TYPE_TOUR
import com.triply.barrierfreetrip.adapter.BFTSpinnerAdapter
import com.triply.barrierfreetrip.adapter.InfoSquareAdapter
import com.triply.barrierfreetrip.adapter.OnItemClickListener
import com.triply.barrierfreetrip.adapter.decoration.StayListItemViewHolderDecoration
import com.triply.barrierfreetrip.data.InfoSquareDto
import com.triply.barrierfreetrip.data.Sido
import com.triply.barrierfreetrip.data.Sigungu
import com.triply.barrierfreetrip.databinding.FragmentStaylistBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel

class StaylistFragment : BaseFragment<FragmentStaylistBinding>(R.layout.fragment_staylist) {
    private val infoSquareAdapter = InfoSquareAdapter()
    private val viewModel: MainViewModel by viewModels()
    private var type: String? = null

    // sido data
    private val sidoCodes = arrayListOf(Sido(code = "-1", name = "시도 선택"))
    private val sidoNames = arrayListOf("시도 선택")
    private var sidoPosition = 0

    // sigungu data
    private val sigunguCodes = arrayListOf(Sigungu(code = "-1", name = "구군 선택"))
    private val sigunguNames = arrayListOf("구군 선택")
    private var sigunguPosition = 0

    // facility data
    private val fcltList = ArrayList<InfoSquareDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        type = arguments?.getString(CONTENT_TYPE)

    }

    override fun initInViewCreated() {
        initTitle()
        initSpinner()

        binding.btnBack.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount > 0) parentFragmentManager.popBackStack()
        }

        viewModel.getSidoCode()
        viewModel.sidoCodes.observe(viewLifecycleOwner) { sidoList ->
            with(sidoCodes) {
                clear()
                add(Sido(code = "-1", "시도 선택"))
                addAll(sidoList)
            }
            with(sidoNames) {
                clear()
                add("시도 선택")
                addAll(sidoList.map { sido -> sido.name })
            }
            binding.spnBigArea.setSelection(0)
        }
        viewModel.sigunguCodes.observe(viewLifecycleOwner) { sigunguList ->
            with(sigunguCodes) {
                clear()
                add(Sigungu(code = "-1", name = "구군 선택"))
                addAll(sigunguList)
            }
            with(sigunguNames) {
                clear()
                add("구군 선택")
                addAll(sigunguList.map { sigungu -> sigungu.name})
            }

            binding.spnSmallArea.setSelection(0)
        }

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
                sidoPosition = position

                fcltList.clear()
                (binding.rvList.adapter as InfoSquareAdapter).setDataList(emptyList())

                viewModel.getSigunguCode(sidoCodes[position].code)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.spnSmallArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position2: Int,
                id: Long
            ) {
                if (position2 == 0) return
                sigunguPosition = position2

                viewModel.getTourFcltList(type ?: "", sidoCodes[sidoPosition].code, sigunguCodes[sigunguPosition].code)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        with(binding.rvList) {
            adapter = infoSquareAdapter
            layoutManager = GridLayoutManager(context, 2)
            if (itemDecorationCount < 1) addItemDecoration(StayListItemViewHolderDecoration())
        }

        infoSquareAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val item = fcltList[position]
                val bundle = Bundle()
                val stayInfoFragment = StayInfoFragment()

                bundle.putString(CONTENT_ID, item.contentId)
                stayInfoFragment.arguments = bundle

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_nav_host_fragment, stayInfoFragment)
                    .commit()
            }
        })

        viewModel.fcltList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                Log.d(TAG, "null near-hotel data")
            }
            fcltList.clear()
            fcltList.addAll(it)
            (binding.rvList.adapter as InfoSquareAdapter).setDataList(fcltList)
        }
    }

    private fun initTitle() {
        binding.tvTitle.text = when {
            type.equals(CONTENT_TYPE_STAY) -> "숙박"
            type.equals(CONTENT_TYPE_TOUR) -> "관광지"
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

    companion object {
        private const val TAG = "StayListFragment"
    }
}


