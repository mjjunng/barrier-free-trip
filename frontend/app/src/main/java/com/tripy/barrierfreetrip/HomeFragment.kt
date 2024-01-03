package com.tripy.barrierfreetrip

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tripy.barrierfreetrip.databinding.FragmentHomeBinding
import com.tripy.barrierfreetrip.feature.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initInViewCreated() {
        Log.d("AAA", "AAA")
    }
}