package place.pic.data.remote.response

import place.pic.data.entity.Banner
import place.pic.data.entity.PlaceGridItem

/**
 * Created By Malibin
 * on 9월 25, 2020
 */

data class BannerDetailResponse(
    val banner: BannerResponse,
    val places: List<PlaceGridItemResponse>
) {
    fun getBanner(): Banner = banner.toBanner()

    fun getPlaceGridItems(): List<PlaceGridItem> = places.map { it.toPlaceGridItem() }
}

