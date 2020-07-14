package place.pic.ui.main.mypage.userlist

import java.io.Serializable

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */

data class UserListData(
    //val img: String,
    val userName: String,
    val part: String,
    val postCount: Int
) : Serializable