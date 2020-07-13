package place.pic.ui.main.place

import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Place
import place.pic.data.entity.UsefulTag

/**.
 *
 * Created By Malibin
 * on 7월 06, 2020
 */

data class PlaceTypeDetails(
    val id: Int,
    val placeType: Place.Type,
    val placeKeywords: List<KeywordTag>,
    val placeFeatures: List<UsefulTag>
)
