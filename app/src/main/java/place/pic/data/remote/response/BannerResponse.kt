package place.pic.data.remote.response

import place.pic.data.entity.Banner
import java.util.*

/**
 * Created By Malibin
 * on 9월 25, 2020
 */

data class BannerResponse(
    val bannerIdx: Int,
    val bannerTitle: String,
    val bannerBadgeName: String,
    val bannerBadgeColor: String,
    val bannerDescription: String,
    val bannerCreatedAt: Long,
    val bannerImageUrl: String,
    val groupIdx: Int
) {
    fun toBanner() = Banner(
        id = bannerIdx,
        title = bannerTitle,
        badgeName = bannerBadgeName,
        badgeColorCode = bannerBadgeColor,
        description = bannerDescription,
        createdAt = Date(bannerCreatedAt * 1000),
        imageUrl = bannerImageUrl,
    )
}
