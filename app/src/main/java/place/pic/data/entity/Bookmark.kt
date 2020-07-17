package place.pic.data.entity

/**
 * Created By Malibin
 * on 7월 17, 2020
 */

data class Bookmark(
    val imageUrl: String,
    val placeIdx: Long,
    val placeName: String,
    val uploaderName: String,
    val likeCount: Int,
    val firstTag: String? = null,
    val secondTag: String? = null
)