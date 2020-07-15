package place.pic.data.entity

import place.pic.ui.main.mypage.userlist.UserData
import java.io.Serializable

/**
 * Created By kimdahyee
 * on 07월 15일, 2020
 */
 
data class UserList (
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