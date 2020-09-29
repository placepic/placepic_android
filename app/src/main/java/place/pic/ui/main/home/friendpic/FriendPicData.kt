package place.pic.ui.main.home.friendpic

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * Created By kimdahyee
 * on 09월 05일, 2020
 */

data class FriendPicData (
    val userIdx: Int,
    val placeIdx: Int,
    val groupIdx: Int,
    val userName: String,
    val part: String,
    val profileImageUrl: String,
    val placeName: String,
    val placeReview: String,
    val placeImageUrl: String,
    val placeCreatedAt: String,
    //uploadDate 서버한테 받는건 Int, 출력하는건 String
    val subway: List<String>,
    val tag: List<String>,
    val likeCnt: Int
)