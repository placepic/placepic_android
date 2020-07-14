package place.pic.ui.main.mypage.userlist

import place.pic.data.entity.Place
import java.io.Serializable
import java.util.*

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */

data class UserData(
    //val img: String,
    val userName: String,
    val part: String,
    val postCount: Int
) {
    companion object {
        fun empty() = UserData(
            userName = "",
            part = "",
            postCount = 0
        )
    }
}