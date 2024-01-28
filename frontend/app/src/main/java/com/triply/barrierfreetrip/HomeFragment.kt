package com.triply.barrierfreetrip

import android.util.Log
import com.triply.barrierfreetrip.databinding.FragmentHomeBinding
import com.triply.barrierfreetrip.feature.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initInViewCreated() {
        Log.d("AAA", "AAA")
    }
}