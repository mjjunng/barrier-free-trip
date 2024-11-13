package com.triply.barrierfreetrip

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.triply.barrierfreetrip.adapter.SearchMultiviewAdapter
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.SearchRsltItem
import com.triply.barrierfreetrip.databinding.FragmentSearchBinding
import retrofit2.Response


class SearchFragment : Fragment(R.layout.fragment_search) {
    var retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    private var _binding:  FragmentSearchBinding? = null
    lateinit var searchMultiviewAdapter: SearchMultiviewAdapter
    private val binding get() = _binding!!
    private val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val SearchRsltItemList = ArrayList<SearchRsltItem>()
                if (query != null) {
                    // call api
                    val responseLiveData : LiveData<Response<List<SearchRsltItem>>> = liveData {
                        val response = retrofit.getSearchResult(query.trim())

                        emit(response)
                    }
                    responseLiveData.observe(viewLifecycleOwner, Observer {
                        val list = it.body()?.listIterator()
                        if (list != null) {
                            binding.ivNoneData.isVisible = false

                            if (list.hasNext()) {
                                while (list.hasNext()) {
                                    val item = list.next()
                                    SearchRsltItemList.add(item) }
                            } else {
                                binding.ivNoneData.isVisible = true
                                Log.d(TAG, "no data from search api")
                            }

                        } else {
                            Log.d(TAG, "null from search api")
                        }

                        searchMultiviewAdapter = SearchMultiviewAdapter(SearchRsltItemList)
                        binding.rvList.adapter = searchMultiviewAdapter
                        binding.rvList.layoutManager = LinearLayoutManager(context)

//            searchMultiviewAdapter.setItemClickListener(object : SearchMultiviewAdapter.OnItemClickListener {
//                override fun onClick(view: View, position: Int) {
//                    val item = SearchRsltItemList[position]
//                    val bundle = Bundle()
//                    val stayInfoFragment = StayInfoFragment()
//
//                    bundle.putString("id", item.id)
//                    stayInfoFragment.arguments = bundle
//
//                    requireActivity().supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.main_nav_host_fragment, stayInfoFragment)
//                        .commit()
//                }
//            })
                    })
                };
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return binding.root
    }
}