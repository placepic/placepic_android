package place.pic.data.remote.response

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.N)
    fun toPlaceGridItem() = PlaceGridItem(
        imageUrl = placeImageUrl,
        placeIdx = placeIdx,
        placeName = Html.fromHtml(placeName, Html.FROM_HTML_MODE_LEGACY).toString(),
        likeCount = likeCnt,
        subwayName = subwayName
    )
}
