package place.pic.ui.main.home

import com.google.android.material.chip.ChipGroup
import java.util.*

/**
 * Created By kimdahyee
 * on 09월 05일, 2020
 */

data class FriendPicData (
    val profileImageUrl: String,
    val userName: String,
    val part: String,
    val imageUrl: String,
    val liker: Int,
    val name: String,
    val subways: List<String>,
    val tags: List<String>,
    val uploadDate: Date,
    val content: String
)