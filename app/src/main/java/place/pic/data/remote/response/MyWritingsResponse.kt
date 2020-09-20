package place.pic.data.remote.response

import place.pic.data.entity.PlaceGridItem

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

data class MyWritingsResponse(
    val UserPlace: List<MyWritingResponse>,
    val placeCount: Int
) {
    fun toPlaceGridItems(): List<PlaceGridItem> {
        return UserPlace.map { it.toPlaceGridItem() }
    }
}

data class MyWritingResponse(
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
