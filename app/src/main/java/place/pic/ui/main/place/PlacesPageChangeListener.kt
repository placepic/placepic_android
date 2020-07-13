package place.pic.ui.main.place

import androidx.viewpager.widget.ViewPager
import place.pic.data.entity.Place

/**
 * Created By Malibin
 * on 7월 05, 2020
 */

class PlacesPageChangeListener(
    private val placesViewModel: PlacesViewModel
) : ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        placesViewModel.clearFiltering()
        val currentPlaceType = Place.Type.findByPosition(position)
        placesViewModel.currentPlaceType.value = currentPlaceType
        placesViewModel.currentPagerPosition = position
    }

}