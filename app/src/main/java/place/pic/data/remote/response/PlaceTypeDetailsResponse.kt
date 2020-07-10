package place.pic.data.remote.response

import place.pic.data.entity.Place
import place.pic.ui.main.place.PlaceTypeDetails

/**
 * Created By Malibin
 * on 7월 10, 2020
 */


data class PlaceTypeDetailsResponse(
    val categoryIdx: Int,
    val categoryName: String,
    val keyword: List<TagResponse>,
    val feature: List<TagResponse>
) {
    fun toPlaceTypeDetail() = PlaceTypeDetails(
        id = categoryIdx,
        placeType = Place.Type.findByName(categoryName),
        placeKeywords = keyword.map { it.toKeywordTag() },
        placeFeatures = feature.map { it.toUsefulTag() }
    )

}