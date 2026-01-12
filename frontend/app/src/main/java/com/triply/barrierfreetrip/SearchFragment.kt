package com.triply.barrierfreetrip

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.triply.barrierfreetrip.MainActivity.Companion.CONTENT_ID
import com.triply.barrierfreetrip.adapter.InfoSquareAdapter
import com.triply.barrierfreetrip.adapter.OnItemClickListener
import com.triply.barrierfreetrip.adapter.SearchHistoryAdapter
import com.triply.barrierfreetrip.adapter.decoration.StayListItemViewHolderDecoration
import com.triply.barrierfreetrip.databinding.FragmentSearchBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel
import com.triply.barrierfreetrip.model.SearchViewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val loadingProgressBar by lazy { BFTLoadingProgressBar(requireContext()) }
    private val viewModel: MainViewModel by activityViewModels()
    private val searchViewModel: SearchViewModel by activityViewModels()

    private val TAG = "SearchFragment"

    override fun initInViewCreated() {
        val navController = findNavController()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            }
        )

        // 검색결과 리사이클러뷰 세팅
        with(binding) {
            rvSearchList.adapter = InfoSquareAdapter()
            rvSearchList.layoutManager = GridLayoutManager(context, 2)
            if (rvSearchList.itemDecorationCount < 1)
                rvSearchList.addItemDecoration(StayListItemViewHolderDecoration())
        }

        // 검색키워드 리사이클러뷰 세팅
        with(binding) {
            rvKeywordList.adapter = SearchHistoryAdapter().apply {
                setOnDeleteClickListener(object : OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        searchViewModel.deleteSearchKeyword(infoList.getOrElse(position) { "" })
                    }
                })
                setOnItemClickListener(object: OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        binding.rvSearchList.scrollToPosition(0)
                        binding.etSearch.setText(infoList.getOrElse(position) { "" })
                        binding.etSearch.setSelection(binding.etSearch.length())
                        viewModel.getSearchResult(binding.etSearch.text.toString())
                        searchViewModel.addSearchKeyword(binding.etSearch.text.toString())
                    }
                })
            }
            rvKeywordList.layoutManager = LinearLayoutManager(context)
        }

        binding.btnDeletionOfAllSearchHistory.setOnClickListener {
            searchViewModel.deleteAllSearchKeyword()
        }

        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                loadingProgressBar.show()
            } else {
                loadingProgressBar.dismiss()
            }
        }

        viewModel.searchResult.observe(viewLifecycleOwner) { result ->
            if (result == null) {
                // 어떤 동작도 하지 않았을 때(제일 처음 진입) : 키워드만 보이기
                binding.rvSearchList.visibility = View.GONE
                binding.clSearchHistoryContainer.visibility = View.VISIBLE
                binding.ivNoneData.visibility = View.GONE
            } else if (result.isEmpty()) {
                // 검색했으나 검색결과가 없는 경우
                binding.rvSearchList.visibility = View.GONE
                binding.clSearchHistoryContainer.visibility = View.GONE
                binding.ivNoneData.visibility = View.VISIBLE
                Log.d(TAG, "no data from search api")
            } else {
                // 검색 결과가 있는 경우
                binding.rvSearchList.visibility = View.VISIBLE
                binding.clSearchHistoryContainer.visibility = View.GONE
                binding.ivNoneData.visibility = View.GONE
                (binding.rvSearchList.adapter as InfoSquareAdapter).setDataList(result)
            }
            (binding.rvSearchList.adapter as InfoSquareAdapter).setOnItemClickListener(
                object : OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val item = viewModel.searchResult.value?.getOrNull(position)
                        item?.contentId?.let {
                            val bundle = Bundle()

                            bundle.putString(CONTENT_ID, it)

                            if (item.contentTypeId == "1") {
                                navController.navigate(
                                    resId = R.id.stayInfoFragment,
                                    args = bundle
                                )
                            }
                        }
                    }
                }
            )
        }

        searchViewModel.searchKeywordList.observe(viewLifecycleOwner) { history ->
            if (history.isNullOrEmpty()) {
                binding.tvNoneRecentlySearchKeyword.visibility = View.VISIBLE
                binding.btnDeletionOfAllSearchHistory.visibility = View.GONE
            } else {
                binding.tvNoneRecentlySearchKeyword.visibility = View.GONE
                binding.btnDeletionOfAllSearchHistory.visibility = View.VISIBLE
            }
            (binding.rvKeywordList.adapter as SearchHistoryAdapter).setSearchHistory(history = history ?: emptyList())
        }

        binding.etSearch.setOnKeyListener { _, keyCode, event ->
            if (keyCode in listOf(KeyEvent.KEYCODE_ENTER, KeyEvent.KEYCODE_SEARCH) && event.action == KeyEvent.ACTION_DOWN) {
                binding.rvSearchList.scrollToPosition(0)
                if (binding.etSearch.text.toString().isNotEmpty()) {
                    viewModel.getSearchResult(binding.etSearch.text.toString())
                    searchViewModel.addSearchKeyword(binding.etSearch.text.toString())
                }
            }
            true
        }
        binding.imgbtnSearchBack.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.etSearch.text?.clear()
            val imeService = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imeService.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
            binding.etSearch.clearFocus()
            viewModel.clearSearchResult()
        }

        binding.etSearch.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty() || s.trim().isBlank()) {
                        viewModel.clearSearchResult()
                        binding.imgbtnSearchBack.visibility = View.INVISIBLE
                        binding.rvSearchList.visibility = View.GONE
                        binding.ivNoneData.visibility = View.GONE
                        binding.clSearchHistoryContainer.visibility = View.VISIBLE
                    } else {
                        binding.imgbtnSearchBack.visibility = View.VISIBLE
                        binding.clSearchHistoryContainer.visibility = View.GONE
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            }
        )
    }
}