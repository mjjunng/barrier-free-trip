package com.triply.barrierfreetrip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.triply.barrierfreetrip.databinding.FragmentStaylistBinding
import com.triply.barrierfreetrip.feature.BaseFragment

class StaylistFragment : BaseFragment<FragmentStaylistBinding>(
    R.layout.fragment_staylist
) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staylist, container, false)
    }

    override fun initInViewCreated() {

    }
}