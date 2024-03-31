package com.triply.barrierfreetrip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.triply.barrierfreetrip.api.BFTApi
import com.triply.barrierfreetrip.api.RetroInstance
import com.triply.barrierfreetrip.data.Sido
import com.triply.barrierfreetrip.databinding.FragmentSearchBinding
import retrofit2.Response


class SearchFragment : Fragment(R.layout.fragment_search) {
    var retrofit = RetroInstance.getInstance().create(BFTApi::class.java)
    private var _binding:  FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        _binding = FragmentSearchBinding.inflate(inflater, container, false)


        // call api
        val responseLiveData : LiveData<Response<List<Sido>>> = liveData {
            val response = retrofit.getSidoCode()

            emit(response)
        }
//        responseLiveData.observe(viewLifecycleOwner, Observer {
//            val list = it.body()?.listIterator()
//            if (list != null) {
//                while (list.hasNext()) {
//                    val item = list.next()
//                }
//            } else {
//                Log.d(TAG, "fail call search api")
//            }
//        })

        return binding.root
    }
}