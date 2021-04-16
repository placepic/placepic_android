package place.pic.data.remote.response

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
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
    val likeCnt: Int,
    val commentCnt: Int
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun toPlace() = Place(
        id = placeIdx,
        name = Html.fromHtml(placeName, Html.FROM_HTML_MODE_LEGACY).toString(),
        imageUrl = imageUrl.getOrElse(0) { "" },
        subways = subway.map { it.toSubway() },
        keywordTags = tag.map { it.toKeywordTag() },
        uploadDate = Date(placeCreatedAt * 1000),
        uploaderName = user.userName,
        uploaderProfileUrl = user.profileURL,
        likeCnt = likeCnt,
        commentCnt = commentCnt
    )

    @RequiresApi(Build.VERSION_CODES.N)
    fun toPlaceGridItem() = PlaceGridItem(
        imageUrl = imageUrl.getOrElse(0) { "" },
        placeIdx = placeIdx,
        placeName = Html.fromHtml(placeName, Html.FROM_HTML_MODE_LEGACY).toString(),
        likeCount = likeCount,
        subwayName = subway.map { it.subwayName }
    )
}

data class PlaceUserResponse(
    val userName: String,
    val profileURL: String
)