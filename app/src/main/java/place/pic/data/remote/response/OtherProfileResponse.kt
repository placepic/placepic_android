package place.pic.data.remote.response

import place.pic.data.entity.PlaceGridItem
import place.pic.data.entity.Profile

data class OtherProfileResponse(
    val UserInfo : Profile,
    val UserPlace: List<PlaceGridItemResponse>?,
) {
    fun getPlaceGridItems(): List<PlaceGridItem> {
        return UserPlace!!.map { it.toPlaceGridItem() }
    }
}
