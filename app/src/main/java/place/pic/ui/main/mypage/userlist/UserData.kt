package place.pic.ui.main.mypage.userlist

import place.pic.data.entity.Place
import java.io.Serializable
import java.util.*

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */

data class UserData(
    val userIdx: Int,
    val userName: String,
    //val profileImageUrl: String,
    val state: Int,
    val part: String,
    val postCount: Int,
    val rank: Int
) {
    companion object {
        fun empty() = UserData(
            userIdx = 0,
            userName = "",
            //profileImageUrl = "",
            state = 0,
            part = "",
            postCount = 0,
            rank = 0
        )
    }
}