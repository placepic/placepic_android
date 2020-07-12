package place.pic.data.remote.response

import place.pic.data.entity.Place
import java.util.*

/**
 * Created By Malibin
 * on 7월 09, 2020
 */

data class PlaceResponse(
    val placeIdx: Long,
    val placeName: String,
    val placeCreatedAt: Long,
    val tag: List<TagResponse>,
    val subway: List<SubwayResponse>,
    val user: PlaceUserResponse,
    val imageUrl: List<String>
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
}

data class PlaceUserResponse(
    val userName: String,
    val profileURL: String
)