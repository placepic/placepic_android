package place.pic.data.remote.response

import place.pic.data.entity.PlaceGridItem

/**
 * Created By Malibin
 * on 7월 09, 2020
 */

data class BookmarksResponse(
    val result: List<PlaceItemResponse>,
    val count: Int
) {
    fun toPlaceGridItems(): List<PlaceGridItem> = result.map { it.toPlaceGridItem() }
}
