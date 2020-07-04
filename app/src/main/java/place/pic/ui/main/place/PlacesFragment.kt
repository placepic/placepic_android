package place.pic.ui.main.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import place.pic.databinding.FragmentPlacesBinding

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlacesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlacesBinding.inflate(inflater, container, false)
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentPlacesBinding) {
        binding.pagerFilterPlaces.adapter = PlacesPagerAdapter(childFragmentManager)
        binding.tabPlaceCategories.setupWithViewPager(binding.pagerFilterPlaces)
    }

}