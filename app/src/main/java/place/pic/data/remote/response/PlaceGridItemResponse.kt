package place.pic.data.remote.response

import place.pic.data.entity.PlaceGridItem

/**
 * Created By Malibin
 * on 9월 25, 2020
 */

data class PlaceGridItemResponse(
    val placeIdx: Int,
    val placeImageUrl: String,
    val placeName: String,
    val likeCnt: Int,
    val subwayName: List<String>
) {
    fun toPlaceGridItem() = PlaceGridItem(
        imageUrl = placeImageUrl,
        placeIdx = placeIdx,
        placeName = placeName,
        likeCount = likeCnt,
        subwayName = subwayName
    )
}
