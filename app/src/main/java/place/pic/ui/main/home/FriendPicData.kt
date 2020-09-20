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
    //태그의 text를 배열로 받아오고
    val uploadDate: String,
    //서버한테 받는건 Int, 출력하는건 String
    val content: String
)