package com.triply.barrierfreetrip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.triply.barrierfreetrip.adapter.PickListAdapter
import com.triply.barrierfreetrip.adapter.decoration.StayListItemViewHolderDecoration
import com.triply.barrierfreetrip.data.FacilityViewType
import com.triply.barrierfreetrip.data.PickType
import com.triply.barrierfreetrip.databinding.FragmentPickListBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel
import com.triply.barrierfreetrip.model.PickListViewModel
import com.triply.barrierfreetrip.util.getFacilityViewType
import kotlinx.coroutines.launch
import kotlin.getValue

class PickListFragment : BaseFragment<FragmentPickListBinding>(R.layout.fragment_pick_list) {
    private val TAG = "PickListFragment"
    private val loadingProgressBar by lazy { BFTLoadingProgressBar(requireContext()) }

    companion object {
        const val CONTENT_ID = "contentId"
        private const val ARG_PAGE_TYPE = "arg_page_type"

        fun newInstance(type: PickType): PickListFragment {
            return PickListFragment().apply {
                arguments = Bundle().apply {
                    // String으로 Enum 전달(이게 덜 귀찮음)
                    putString(ARG_PAGE_TYPE, type.name)
                }
            }
        }
    }

    private lateinit var pageType: PickType
    private val viewModel: MainViewModel by activityViewModels()
    private val pickViewModel: PickListViewModel by viewModels()
    private lateinit var pickListAdapter: PickListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val pageTypeStr = arguments?.getString(ARG_PAGE_TYPE)
            ?: throw IllegalArgumentException("PageType must be provided")
        pageType = PickType.valueOf(pageTypeStr)

        pickViewModel.setPageType(pageType)

        observeViewModel()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initInViewCreated() {
        pickViewModel.getLikes()
        setRecyclerView()
    }

    fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    pickViewModel.pickListUiState.collect {
                        pickListAdapter.submitList(it)
                    }
                }
                launch {
                    viewModel.isDataLoading.observe(viewLifecycleOwner) {
                        if (it.getContentIfNotHandled() == true) {
                            loadingProgressBar.show()
                        } else {
                            loadingProgressBar.dismiss()
                        }
                    }
                }
            }
        }
    }

    private fun setRecyclerView() {
        pickListAdapter = PickListAdapter(pageType,
            {
                if (canNavigateToDetailInfo()) {
                    navigateToDetailPage(it.getContentsId())
                }
            },
            {
                viewModel.postLikes(
                    pageType.typeValue.toString(),
                    it.getContentsId(),
                    if (it.getLikes()) 0 else 1
                )
            }
        )

        binding.rvPickList.run {
            adapter = pickListAdapter
            layoutManager =
                if (getFacilityViewType(pageType) == FacilityViewType.SQUARE_TYPE) {
                    if (itemDecorationCount < 1)
                        addItemDecoration(StayListItemViewHolderDecoration())
                    GridLayoutManager(requireContext(), 2)
                }
                else {
                    LinearLayoutManager(requireContext())
                }
        }
    }

    private fun navigateToDetailPage(contentId: String) {
        val bundle = Bundle()

        bundle.putString(CONTENT_ID, contentId)
        findNavController().navigate(
            resId = R.id.stayInfoFragment,
            args = bundle
        )
    }

    private fun canNavigateToDetailInfo() =
        pageType == PickType.STAY
                || pageType == PickType.TOUR
                || pageType == PickType.RESTAURANT
}