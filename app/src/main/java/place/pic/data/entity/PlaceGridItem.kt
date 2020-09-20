package place.pic.data.entity

/**
 * Created By Malibin
 * on 9월 19, 2020
 */

data class PlaceGridItem(
    val imageUrl: String,
    val placeIdx: Int,
    val placeName: String,
    val likeCount: Int,
    val subwayName: List<String>
)