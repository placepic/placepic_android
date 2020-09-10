package place.pic.data.remote.response

import place.pic.ui.main.userlist.UserData

/**
 * Created By kimdahyee
 * on 07월 15일, 2020
 */
 
data class UserListResponse (
    val userCnt: Int,
    val userList: ArrayList<UserData>
)