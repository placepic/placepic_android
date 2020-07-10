package place.pic.ui.main.place.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Place
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.ui.main.place.items.PlaceItemsFragment

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlacesPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = Place.Type.values()
        .sortedBy { it.position }
        .map { PlaceItemsFragment(it) }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = Place.Type.values().size

    override fun getPageTitle(position: Int): CharSequence? {
        return Place.Type.values()
            .first { it.position == position }
            .value
    }
}