package com.triply.barrierfreetrip.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.triply.barrierfreetrip.PickListFragment
import com.triply.barrierfreetrip.data.PickType

private const val NUM_PAGES = 6
class PickPageAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): PickListFragment {
        return when (position) {
            0 -> PickListFragment.newInstance(PickType.STAY)
            1 -> PickListFragment.newInstance(PickType.TOUR)
            2 -> PickListFragment.newInstance(PickType.RESTAURANT)
            3 -> PickListFragment.newInstance(PickType.CARE)
            4 -> PickListFragment.newInstance(PickType.CHARGE)
            5 -> PickListFragment.newInstance(PickType.RENTAL)
            else -> throw IllegalStateException("Invalid PickType")
        }
    }
}
