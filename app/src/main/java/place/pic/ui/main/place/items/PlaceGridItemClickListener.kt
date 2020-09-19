package place.pic.ui.main.place.items

import place.pic.data.entity.PlaceGridItem

/**
 * Created By Malibin
 * on 9월 19, 2020
 */

fun interface PlaceGridItemClickListener {

    fun onPlaceGridItemClick(item: PlaceGridItem)
}