package place.pic.ui.main.place

import place.pic.data.entity.Place

/**.
 *
 * Created By Malibin
 * on 7월 06, 2020
 */

data class PlaceTypeDetails(
    val id: Int,
    val placeType: Place.Type,
    val placeKeywords: List<String>,
    val placeFeatures: List<String>
)
