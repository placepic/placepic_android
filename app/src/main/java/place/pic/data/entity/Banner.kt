package place.pic.data.entity

import java.util.*

/**
 * Created By Malibin
 * on 9월 25, 2020
 */

data class Banner(
    val id: Int,
    val title: String,
    val badgeName: String,
    val badgeColorCode: String,
    val description: String,
    val createdAt: Date,
    val imageUrl: String,
)