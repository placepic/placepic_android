package place.pic.data.remote.response

import place.pic.data.entity.PlaceGridItem

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

data class MyWritingsResponse(
    val UserPlace: List<PlaceGridItemResponse>,
    val placeCount: Int
) {
    fun toPlaceGridItems(): List<PlaceGridItem> {
        return UserPlace.map { it.toPlaceGridItem() }
    }
}
