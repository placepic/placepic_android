package place.pic.data.remote.response

import place.pic.data.entity.Place
import place.pic.data.entity.PlaceGridItem
import java.util.*

/**
 * Created By Malibin
 * on 7월 09, 2020
 */

data class PlaceResponse(
    val result: List<PlaceItemResponse>,
    val count: Int
) {
    fun toPlaces() = result.map { it.toPlace() }
}

data class PlaceItemResponse(
    val placeIdx: Int,
    val placeName: String,
    val placeCreatedAt: Long,
    val tag: List<TagResponse>,
    val subway: List<SubwayResponse>,
    val user: PlaceUserResponse,
    val imageUrl: List<String>,
    val likeCount: Int,
    val commentCnt: Int
) {
    fun toPlace() = Place(
        id = placeIdx,
        name = placeName,
        imageUrl = imageUrl.getOrElse(0) { "" },
        subways = subway.map { it.toSubway() },
        keywordTags = tag.map { it.toKeywordTag() },
        uploadDate = Date(placeCreatedAt * 1000),
        uploaderName = user.userName,
        uploaderProfileUrl = user.profileURL,
        commentCnt = commentCnt
    )

    fun toPlaceGridItem() = PlaceGridItem(
        imageUrl = imageUrl.getOrElse(0) { "" },
        placeIdx = placeIdx,
        placeName = placeName,
        likeCount = likeCount,
        subwayName = subway.map { it.subwayName }
    )
}

data class PlaceUserResponse(
    val userName: String,
    val profileURL: String
)