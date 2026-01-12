package com.triply.barrierfreetrip

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.triply.barrierfreetrip.adapter.PickPageAdapter
import com.triply.barrierfreetrip.databinding.FragmentPickBinding
import com.triply.barrierfreetrip.feature.BaseFragment
import com.triply.barrierfreetrip.model.MainViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PickFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickFragment: BaseFragment<FragmentPickBinding>(R.layout.fragment_pick) {
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var pagerAdapter: PickPageAdapter

    override fun initInViewCreated() {
        pagerAdapter = PickPageAdapter(requireParentFragment())
        binding.vpPickScreenArea.adapter = pagerAdapter

        binding.vpPickScreenArea.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        val listTabName = arrayListOf(
            getString(R.string.all_stay),
            getString(R.string.home_tour),
            getString(R.string.home_restaurant),
            getString(R.string.all_care),
            getString(R.string.home_charge),
            getString(R.string.home_rental)
        )
        TabLayoutMediator(binding.tlPick, binding.vpPickScreenArea) { tab, position ->
            tab.text = listTabName[position]
        }.attach()
    }
}