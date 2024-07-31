package com.triply.barrierfreetrip

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.triply.barrierfreetrip.adapter.PickPageAdapter
import com.triply.barrierfreetrip.databinding.FragmentPickBinding
import com.triply.barrierfreetrip.feature.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [PickFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickFragment :BaseFragment<FragmentPickBinding>(R.layout.fragment_pick) {
    private val pagerAdapter = PickPageAdapter(requireParentFragment())
    private val MAX_TAB = 6

    override fun initInViewCreated() {

        for (i in 0 until MAX_TAB) {
            pagerAdapter.addFragment(PickListFragment())
        }

        binding.vpPickScreenArea.adapter = pagerAdapter
        binding.vpPickScreenArea.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        val listTabName = arrayListOf<String>(
            getString(R.string.all_stay),
            getString(R.string.home_destination),
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