package place.pic.data.remote.response

import place.pic.data.entity.Bookmark
import place.pic.data.entity.Place
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
    val placeIdx: Long,
    val placeName: String,
    val placeCreatedAt: Long,
    val tag: List<TagResponse>,
    val subway: List<SubwayResponse>,
    val user: PlaceUserResponse,
    val imageUrl: List<String>,
    val likeCount: Int
) {
    fun toPlace() = Place(
        id = placeIdx,
        name = placeName,
        imageUrl = imageUrl.getOrElse(0) { "" },
        subways = subway.map { it.toSubway() },
        keywordTags = tag.map { it.toKeywordTag() },
        uploadDate = Date(placeCreatedAt * 1000),
        uploaderName = user.userName,
        uploaderProfileUrl = user.profileURL
    )

    fun toBookmark() = Bookmark(
        imageUrl = imageUrl.getOrElse(0) { "" },
        placeIdx = placeIdx,
        placeName = placeName,
        uploaderName = user.userName,
        likeCount = likeCount,
        firstTag = tag.getOrNull(0)?.tagName,
        secondTag = tag.getOrNull(1)?.tagName
    )
}

data class PlaceUserResponse(
    val userName: String,
    val profileURL: String
)