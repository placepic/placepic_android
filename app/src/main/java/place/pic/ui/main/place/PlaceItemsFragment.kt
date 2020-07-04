package place.pic.ui.main.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import place.pic.databinding.FragmentPlaceItemsBinding

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlaceItemsFragment(private val placeType: Place.Type) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlaceItemsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}